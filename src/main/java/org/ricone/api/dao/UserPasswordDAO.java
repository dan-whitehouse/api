package org.ricone.api.dao;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.exception.NoContentException;
import org.ricone.api.model.core.*;
import org.ricone.api.model.core.extension.userpassword.EntityType;
import org.ricone.api.util.AES;
import org.ricone.api.util.UserPasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository("userPasswordDAO")
@SuppressWarnings("unchecked")
public class UserPasswordDAO extends AbstractDAO<Integer, UserPassword> implements IUserPasswordDAO {

    private final String APP_ID = "appId";
    private final String ENTITY_TYPE = "entityType";
    private final String ENTITY_REF_ID = "entityRefId";
    private final String TEMP_PASSWORD = "tempPassword";
    private final String EXPIRY_DATE = "expiryDate";
    private final String LAST_RETRIEVED = "lastRetrieved";
    private final String GEN_DATE = "generationDate";

    private final String STAFF_REF_ID = "staffRefId";
    private final String STUDENT_REF_ID = "studentRefId";

    @Autowired
    private UserPasswordGenerator generator;

    @Autowired
    private AES security;

    public boolean provisionStaffsBySchool(MetaData metaData, HashMap<String, String> kv, List<Staff> staffs) throws Exception {
        staffs.forEach(staff -> {
            Date now = new Date();
            long ttl = (long)(Double.parseDouble(kv.get("api.userpass.password.ttl")) * 86400000);
            String password = generator.getPassword(kv, staff, metaData.getApp().getId());
            String key = security.getRefToKey(staff.getStaffRefId());

            //TODO - EntityType doesn't insert correctly. Hibernate Bug: https://hibernate.atlassian.net/browse/HHH-10594
            UserPassword userPassword = new UserPassword();
            userPassword.setEntityRefId(staff.getStaffRefId());
            userPassword.setEntityType(EntityType.STAFF);
            userPassword.setAppId(metaData.getApp().getId());
            userPassword.setTempPassword(security.encrypt(password, key));

            userPassword.setGenerationDate(now);
            userPassword.setExpiryDate(new Date(ttl + now.getTime()));
            userPassword.setLastRetrieved(null);
            update(userPassword);
        });

        return false;
    }

    public boolean deleteStaffsBySchool(MetaData metaData, String refId) throws Exception {
        return false;
    }

    public List<UserPassword> findStaffsBySchool(MetaData metaData, String refId) throws Exception {
        //https://docs.jboss.org/hibernate/entitymanager/3.6/reference/en/html_single/#querycriteria-typedquery-construct
        //https://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#criteria-typedquery-wrapper

        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<UserPassword> select = cb.createQuery(UserPassword.class);
        final Root<UserPassword> from = select.from(UserPassword.class);

        final Root<Staff> staff = select.from(Staff.class);
        final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) staff.<Staff, StaffAssignment>join("staffAssignments", JoinType.LEFT);
        final Join<StaffAssignment, School> school = staffAssignments.join("school", JoinType.LEFT);
        final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);

        select.distinct(true);
        select.select
        (
            cb.construct
            (
                UserPassword.class,
                from.get( ENTITY_REF_ID ),
                from.get( ENTITY_TYPE ),
                from.get( APP_ID ),
                from.get( TEMP_PASSWORD ),
                from.get( EXPIRY_DATE ),
                from.get( GEN_DATE ),
                from.get( LAST_RETRIEVED ),
                staff
            )
        );
        select.where
        (
            cb.and
            (
                cb.equal(from.get(APP_ID), metaData.getApp().getId()),
                cb.equal(from.get(ENTITY_TYPE), EntityType.STAFF),
                cb.equal(from.get(ENTITY_REF_ID), staff.get(STAFF_REF_ID)),
                lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
            )
        );

        Query<UserPassword> q = getSession().createQuery(select);
        if(metaData.getPaging().isPaged()){
            q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
            q.setMaxResults(metaData.getPaging().getPageSize());
        }
        List<UserPassword> instance = q.getResultList();
        instance.forEach(userPassword -> {
            Hibernate.initialize(userPassword.getStaff().getStaffIdentifiers());
        });

        if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
        return instance;
    }

    public boolean provisionStudentsBySchool(MetaData metaData, HashMap<String, String> kv, List<Student> students) throws Exception {
        students.forEach(student -> {
            Date now = new Date();
            long ttl = (long)(Double.parseDouble(kv.get("api.userpass.password.ttl")) * 86400000);
            String password = generator.getPassword(kv, student, metaData.getApp().getId());
            String key = security.getRefToKey(student.getStudentRefId());

            //TODO - EntityType doesn't insert correctly. Hibernate Bug: https://hibernate.atlassian.net/browse/HHH-10594
            UserPassword userPassword = new UserPassword();
            userPassword.setEntityRefId(student.getStudentRefId());
            userPassword.setEntityType(EntityType.STUDENT);
            userPassword.setAppId(metaData.getApp().getId());
            userPassword.setTempPassword(security.encrypt(password, key));

            userPassword.setGenerationDate(now);
            userPassword.setExpiryDate(new Date(ttl + now.getTime()));
            userPassword.setLastRetrieved(null);
            update(userPassword);
        });

        return false;
    }

    public boolean deleteStudentsBySchool(MetaData metaData, String refId) throws Exception {
        return false;
    }

    public List<UserPassword> findStudentsBySchool(MetaData metaData, String refId) throws Exception {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<UserPassword> select = cb.createQuery(UserPassword.class);
        final Root<UserPassword> from = select.from(UserPassword.class);

        final Root<Student> student = select.from(Student.class);
        final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
        final Join<StudentEnrollment, School> school = studentEnrollments.join("school", JoinType.LEFT);
        final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);

        select.distinct(true);
        select.select
        (
            cb.construct
            (
                UserPassword.class,
                from.get( ENTITY_REF_ID ),
                from.get( ENTITY_TYPE ),
                from.get( APP_ID ),
                from.get( TEMP_PASSWORD ),
                from.get( EXPIRY_DATE ),
                from.get( GEN_DATE ),
                from.get( LAST_RETRIEVED ),
                student
            )
        );
        select.where
        (
            cb.and
            (
                cb.equal(from.get(APP_ID), metaData.getApp().getId()),
                cb.equal(from.get(ENTITY_TYPE), EntityType.STAFF),
                cb.equal(from.get(ENTITY_REF_ID), student.get(STUDENT_REF_ID)),
                lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
            )
        );

        Query<UserPassword> q = getSession().createQuery(select);
        if(metaData.getPaging().isPaged()){
            q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
            q.setMaxResults(metaData.getPaging().getPageSize());
        }
        List<UserPassword> instance = q.getResultList();
        instance.forEach(userPassword -> {
            Hibernate.initialize(userPassword.getStudent().getStudentIdentifiers());
        });

        if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
        return instance;
    }

    @Override
    public void save(UserPassword instance) {
        super.persist(instance);
    }

    @Override
    public void update(UserPassword instance) {
        super.saveOrUpdate(instance);
    }

    @Override
    public void delete(UserPassword instance) {
        super.delete(instance);
    }
}
