package org.ricone.api.xPress.request.aupp;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.ricone.api.AbstractDAO;
import org.ricone.api.core.model.*;
import org.ricone.api.util.AES;
import org.ricone.authentication.MetaData;
import org.ricone.error.exception.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository("userPasswordDAO")
@SuppressWarnings("unchecked")
public class UserPasswordDAO extends AbstractDAO<Integer, UserPassword> implements IUserPasswordDAO {

    @Autowired
    private UserPasswordGenerator generator;

    @Autowired
    private AES security;

    @Override
    public void provisionStaffsBySchool(MetaData metaData, HashMap<String, String> kv, List<Staff> staffs) throws Exception {
        staffs.forEach(staff -> {
            Date now = new Date();
            long ttl = (long) (Double.parseDouble(kv.get("api.userpass.password.ttl")) * 86400000);
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
    }

    @Override
    public void provisionStudentsBySchool(MetaData metaData, HashMap<String, String> kv, List<Student> students) throws Exception {
        students.forEach(student -> {
            Date now = new Date();
            long ttl = (long) (Double.parseDouble(kv.get("api.userpass.password.ttl")) * 86400000);
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
    }

    @Override
    public void updateStaffsLastRetrievedBySchool(MetaData metaData, String refId) throws Exception {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaUpdate<UserPassword> update = cb.createCriteriaUpdate(UserPassword.class);
        final Root<UserPassword> from = update.from(UserPassword.class);

        //Sub Query
        Subquery<Staff> subquery = update.subquery(Staff.class);
        final Root<Staff> staff = subquery.from(Staff.class);
        final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) staff.<Staff, StaffAssignment>join("staffAssignments", JoinType.LEFT);
        final Join<StaffAssignment, School> school = staffAssignments.join("school", JoinType.LEFT);
        final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);
        subquery.select(staff.get(STAFF_REF_ID));
        subquery.where(cb.and(cb.equal(school.get(SCHOOL_REF_ID), refId), lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())));

        //Update
        update.set(LAST_RETRIEVED, new Date());
        update.where(cb.and(cb.in(from.get(ENTITY_REF_ID)).value(subquery), cb.equal(from.get(APP_ID), metaData.getApp().getId()), cb.equal(from.get(ENTITY_TYPE), EntityType.STAFF), cb.greaterThanOrEqualTo(from.get(EXPIRY_DATE), new Date())));

        Query<UserPassword> q = getSession().createQuery(update);
        if(metaData.getPaging().isPaged()) {
            q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
            q.setMaxResults(metaData.getPaging().getPageSize());
        }
        q.executeUpdate();
    }

    @Override
    public void updateStudentsLastRetrievedBySchool(MetaData metaData, String refId) throws Exception {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaUpdate<UserPassword> update = cb.createCriteriaUpdate(UserPassword.class);
        final Root<UserPassword> from = update.from(UserPassword.class);

        //Sub Query
        Subquery<Student> subquery = update.subquery(Student.class);
        final Root<Student> student = subquery.from(Student.class);
        final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
        final Join<StudentEnrollment, School> school = studentEnrollments.join("school", JoinType.LEFT);
        final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);

        subquery.select(student.get(STUDENT_REF_ID));
        subquery.where(cb.and(cb.equal(school.get(SCHOOL_REF_ID), refId), lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())));

        //Update
        update.set(LAST_RETRIEVED, new Date());
        update.where(cb.and(cb.in(from.get(ENTITY_REF_ID)).value(subquery), cb.equal(from.get(APP_ID), metaData.getApp().getId()), cb.equal(from.get(ENTITY_TYPE), EntityType.STUDENT), cb.greaterThanOrEqualTo(from.get(EXPIRY_DATE), new Date())));

        Query<UserPassword> q = getSession().createQuery(update);
        if(metaData.getPaging().isPaged()) {
            q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
            q.setMaxResults(metaData.getPaging().getPageSize());
        }
        q.executeUpdate();
    }

    @Override
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
        select.select(cb.construct(UserPassword.class, from.get(ENTITY_REF_ID), from.get(ENTITY_TYPE), from.get(APP_ID), from.get(TEMP_PASSWORD), from.get(EXPIRY_DATE), from.get(GEN_DATE), from.get(LAST_RETRIEVED), staff));
        select.where(cb.and(isOTA(metaData, cb, from, refId), cb.equal(from.get(APP_ID), metaData.getApp().getId()), cb.equal(from.get(ENTITY_TYPE), EntityType.STAFF), cb.greaterThanOrEqualTo(from.get(EXPIRY_DATE), new Date()), cb.equal(from.get(ENTITY_REF_ID), staff.get(STAFF_REF_ID)), cb.equal(school.get(SCHOOL_REF_ID), refId), lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())));

        Query<UserPassword> q = getSession().createQuery(select);
        if(metaData.getPaging().isPaged()) {
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

    @Override
    public List<UserPassword> findStudentsBySchool(MetaData metaData, String refId) throws Exception {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<UserPassword> select = cb.createQuery(UserPassword.class);
        final Root<UserPassword> from = select.from(UserPassword.class);

        final Root<Student> student = select.from(Student.class);
        final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
        final Join<StudentEnrollment, School> school = studentEnrollments.join("school", JoinType.LEFT);
        final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);

        select.distinct(true);
        select.select(cb.construct(UserPassword.class, from.get(ENTITY_REF_ID), from.get(ENTITY_TYPE), from.get(APP_ID), from.get(TEMP_PASSWORD), from.get(EXPIRY_DATE), from.get(GEN_DATE), from.get(LAST_RETRIEVED), student));
        select.where(cb.and(isOTA(metaData, cb, from, refId), cb.equal(from.get(APP_ID), metaData.getApp().getId()), cb.equal(from.get(ENTITY_TYPE), EntityType.STUDENT), cb.greaterThanOrEqualTo(from.get(EXPIRY_DATE), new Date()), cb.equal(from.get(ENTITY_REF_ID), student.get(STUDENT_REF_ID)), cb.equal(school.get("schoolRefId"), refId), lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())));

        Query<UserPassword> q = getSession().createQuery(select);
        if(metaData.getPaging().isPaged()) {
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
    public void deleteStaffsBySchool(MetaData metaData, String refId) throws Exception {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaDelete<UserPassword> delete = cb.createCriteriaDelete(UserPassword.class);
        final Root<UserPassword> from = delete.from(UserPassword.class);

        //Sub Query
        Subquery<Staff> subquery = delete.subquery(Staff.class);
        final Root<Staff> staff = subquery.from(Staff.class);
        final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) staff.<Staff, StaffAssignment>join("staffAssignments", JoinType.LEFT);
        final Join<StaffAssignment, School> school = staffAssignments.join("school", JoinType.LEFT);
        final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);
        subquery.select(staff.get(STAFF_REF_ID));
        subquery.where(cb.and(cb.equal(school.get(SCHOOL_REF_ID), refId), lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())));

        //Update
        delete.where(cb.and(cb.in(from.get(ENTITY_REF_ID)).value(subquery), cb.equal(from.get(APP_ID), metaData.getApp().getId()), cb.equal(from.get(ENTITY_TYPE), EntityType.STAFF), cb.greaterThanOrEqualTo(from.get(EXPIRY_DATE), new Date())));

        Query<UserPassword> q = getSession().createQuery(delete);
        if(metaData.getPaging().isPaged()) {
            q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
            q.setMaxResults(metaData.getPaging().getPageSize());
        }
        q.executeUpdate();
    }

    @Override
    public void deleteStudentsBySchool(MetaData metaData, String refId) throws Exception {

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


    private Predicate isOTA(MetaData metaData, CriteriaBuilder cb, Root<UserPassword> from, String refId) {
        HashMap<String, String> kv = metaData.getApp().getDistrictKVsBySchool(refId);
        if(kv != null) {
            boolean isOTA = BooleanUtils.toBoolean(kv.get("api.userpass.ota"));
            if(isOTA) {
                return cb.isNull(from.get(LAST_RETRIEVED));
            }
        }
        return cb.isNotNull(from.get(LAST_RETRIEVED));
    }

    private final String APP_ID = "appId";
    private final String ENTITY_TYPE = "entityType";
    private final String ENTITY_REF_ID = "entityRefId";
    private final String TEMP_PASSWORD = "tempPassword";
    private final String EXPIRY_DATE = "expiryDate";
    private final String LAST_RETRIEVED = "lastRetrieved";
    private final String GEN_DATE = "generationDate";
    private final String SCHOOL_REF_ID = "schoolRefId";
    private final String STAFF_REF_ID = "staffRefId";
    private final String STUDENT_REF_ID = "studentRefId";
}
