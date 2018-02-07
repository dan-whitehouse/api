package org.ricone.api.xPress.request.xSchool;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.ricone.api.AbstractDAO;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.SchoolWrapper;
import org.ricone.authentication.MetaData;
import org.ricone.error.exception.NoContentException;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Set;

@Repository("schoolDAO")
@SuppressWarnings("unchecked")
public class SchoolDAO extends AbstractDAO<Integer, School> implements ISchoolDAO {
    private final String PRIMARY_KEY = "schoolRefId";
    private final String LOCAL_ID_KEY = "schoolId";
    private final String IDENTIFICATION_SYSTEM_CODE = "identificationSystemCode";

    @Override
    public List<SchoolWrapper> findAll(MetaData metaData) throws Exception {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
        final Root<School> from = select.from(School.class);
        final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join("schoolGrades", JoinType.LEFT);
        final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join("schoolTelephones", JoinType.LEFT);
        final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join("schoolIdentifiers", JoinType.LEFT);
        final Join<School, Lea> lea = (Join<School, Lea>) from.<School, Lea>join("lea", JoinType.LEFT);

        select.distinct(true);
        select.select(cb.construct(SchoolWrapper.class, lea.get("leaId"), from));
        select.where(lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds()));
        select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

        Query<SchoolWrapper> q = getSession().createQuery(select);
        if(metaData.getPaging().isPaged()) {
            q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
            q.setMaxResults(metaData.getPaging().getPageSize());
        }

        List<SchoolWrapper> instance = q.getResultList();
        instance.forEach(wrapper -> {
            Hibernate.initialize(wrapper.getSchool().getSchoolTelephones());
            Hibernate.initialize(wrapper.getSchool().getSchoolGrades());
            Hibernate.initialize(wrapper.getSchool().getSchoolIdentifiers());
        });

        if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
        return instance;
    }

    @Override
    public List<SchoolWrapper> findAllByLeaRefId(MetaData metaData, String refId) throws Exception {
        System.out.println("model");

        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
        final Root<School> from = select.from(School.class);
        final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join("schoolGrades", JoinType.LEFT);
        final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join("schoolTelephones", JoinType.LEFT);
        final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join("schoolIdentifiers", JoinType.LEFT);
        final Join<School, Lea> lea = (Join<School, Lea>) from.<School, Lea>join("lea", JoinType.LEFT);

        select.distinct(true);
        select.select(cb.construct(SchoolWrapper.class, lea.get("leaId"), from));
        select.where(cb.and(cb.equal(lea.get("leaRefId"), refId), lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())));
        select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

        Query<SchoolWrapper> q = getSession().createQuery(select);
        if(metaData.getPaging().isPaged()) {
            q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
            q.setMaxResults(metaData.getPaging().getPageSize());
        }

        List<SchoolWrapper> instance = q.getResultList();
        instance.forEach(wrapper -> {
            Hibernate.initialize(wrapper.getSchool().getSchoolTelephones());
            Hibernate.initialize(wrapper.getSchool().getSchoolGrades());
            Hibernate.initialize(wrapper.getSchool().getSchoolIdentifiers());
        });

        if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
        return instance;
    }

    @Override
    public List<SchoolWrapper> findAllByCalendarRefId(MetaData metaData, String refId) throws Exception {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
        final Root<School> from = select.from(School.class);
        final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join("schoolGrades", JoinType.LEFT);
        final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join("schoolTelephones", JoinType.LEFT);
        final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join("schoolIdentifiers", JoinType.LEFT);
        final SetJoin<School, SchoolCalendar> schoolCalendars = (SetJoin<School, SchoolCalendar>) from.<School, SchoolCalendar>join("schoolCalendars", JoinType.LEFT);
        final Join<School, Lea> lea = (Join<School, Lea>) from.<School, Lea>join("lea", JoinType.LEFT);

        select.distinct(true);
        select.select(cb.construct(SchoolWrapper.class, lea.get("leaId"), from));
        select.where(cb.and(cb.equal(schoolCalendars.get("schoolCalendarRefId"), refId), lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())));
        select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

        Query<SchoolWrapper> q = getSession().createQuery(select);
        if(metaData.getPaging().isPaged()) {
            q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
            q.setMaxResults(metaData.getPaging().getPageSize());
        }

        List<SchoolWrapper> instance = q.getResultList();
        instance.forEach(wrapper -> {
            Hibernate.initialize(wrapper.getSchool().getSchoolTelephones());
            Hibernate.initialize(wrapper.getSchool().getSchoolGrades());
            Hibernate.initialize(wrapper.getSchool().getSchoolIdentifiers());
        });

        if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
        return instance;
    }

    @Override
    public List<SchoolWrapper> findAllByCourseRefId(MetaData metaData, String refId) throws Exception {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
        final Root<School> from = select.from(School.class);
        final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join("schoolGrades", JoinType.LEFT);
        final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join("schoolTelephones", JoinType.LEFT);
        final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join("schoolIdentifiers", JoinType.LEFT);
        final SetJoin<School, Course> courses = (SetJoin<School, Course>) from.<School, Course>join("courses", JoinType.LEFT);
        final Join<School, Lea> lea = (Join<School, Lea>) from.<School, Lea>join("lea", JoinType.LEFT);

        select.distinct(true);
        select.select(cb.construct(SchoolWrapper.class, lea.get("leaId"), from));
        select.where(cb.and(cb.equal(courses.get("courseRefId"), refId), lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())));
        select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

        Query<SchoolWrapper> q = getSession().createQuery(select);
        if(metaData.getPaging().isPaged()) {
            q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
            q.setMaxResults(metaData.getPaging().getPageSize());
        }

        List<SchoolWrapper> instance = q.getResultList();
        instance.forEach(wrapper -> {
            Hibernate.initialize(wrapper.getSchool().getSchoolTelephones());
            Hibernate.initialize(wrapper.getSchool().getSchoolGrades());
            Hibernate.initialize(wrapper.getSchool().getSchoolIdentifiers());
        });

        if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
        return instance;
    }

    @Override
    public List<SchoolWrapper> findAllByRosterRefId(MetaData metaData, String refId) throws Exception {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
        final Root<School> from = select.from(School.class);
        final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join("schoolGrades", JoinType.LEFT);
        final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join("schoolTelephones", JoinType.LEFT);
        final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join("schoolIdentifiers", JoinType.LEFT);
        final SetJoin<School, Course> courses = (SetJoin<School, Course>) from.<School, Course>join("courses", JoinType.LEFT);
        final SetJoin<Course, CourseSection> courseSections = (SetJoin<Course, CourseSection>) courses.<Course, CourseSection>join("courseSections", JoinType.LEFT);
        final Join<School, Lea> lea = (Join<School, Lea>) from.<School, Lea>join("lea", JoinType.LEFT);

        select.distinct(true);
        select.select(cb.construct(SchoolWrapper.class, lea.get("leaId"), from));
        select.where(cb.and(cb.equal(courseSections.get("courseSectionRefId"), refId), lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())));
        select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

        Query<SchoolWrapper> q = getSession().createQuery(select);
        if(metaData.getPaging().isPaged()) {
            q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
            q.setMaxResults(metaData.getPaging().getPageSize());
        }

        List<SchoolWrapper> instance = q.getResultList();
        instance.forEach(wrapper -> {
            Hibernate.initialize(wrapper.getSchool().getSchoolTelephones());
            Hibernate.initialize(wrapper.getSchool().getSchoolGrades());
            Hibernate.initialize(wrapper.getSchool().getSchoolIdentifiers());
        });

        if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
        return instance;
    }

    @Override
    public List<SchoolWrapper> findAllByStaffRefId(MetaData metaData, String refId) throws Exception {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
        final Root<School> from = select.from(School.class);
        final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join("schoolGrades", JoinType.LEFT);
        final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join("schoolTelephones", JoinType.LEFT);
        final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join("schoolIdentifiers", JoinType.LEFT);
        final SetJoin<School, StaffAssignment> staffAssignments = (SetJoin<School, StaffAssignment>) from.<School, StaffAssignment>join("staffAssignments", JoinType.LEFT);
        final Join<StaffAssignment, Staff> staff = (Join<StaffAssignment, Staff>) staffAssignments.<StaffAssignment, Staff>join("staff", JoinType.INNER);
        final Join<School, Lea> lea = (Join<School, Lea>) from.<School, Lea>join("lea", JoinType.LEFT);

        select.distinct(true);
        select.select(cb.construct(SchoolWrapper.class, lea.get("leaId"), from));
        select.where(cb.and(cb.equal(staff.get("staffRefId"), refId), lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())));
        select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

        Query<SchoolWrapper> q = getSession().createQuery(select);
        if(metaData.getPaging().isPaged()) {
            q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
            q.setMaxResults(metaData.getPaging().getPageSize());
        }

        List<SchoolWrapper> instance = q.getResultList();
        instance.forEach(wrapper -> {
            Hibernate.initialize(wrapper.getSchool().getSchoolTelephones());
            Hibernate.initialize(wrapper.getSchool().getSchoolGrades());
            Hibernate.initialize(wrapper.getSchool().getSchoolIdentifiers());
        });

        if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
        return instance;
    }

    @Override
    public List<SchoolWrapper> findAllByStudentRefId(MetaData metaData, String refId) throws Exception {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
        final Root<School> from = select.from(School.class);
        final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join("schoolGrades", JoinType.LEFT);
        final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join("schoolTelephones", JoinType.LEFT);
        final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join("schoolIdentifiers", JoinType.LEFT);
        final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) from.<School, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
        final Join<StudentEnrollment, Student> student = (Join<StudentEnrollment, Student>) studentEnrollments.<StudentEnrollment, Student>join("student", JoinType.INNER);
        final Join<School, Lea> lea = (Join<School, Lea>) from.<School, Lea>join("lea", JoinType.LEFT);

        select.distinct(true);
        select.select(cb.construct(SchoolWrapper.class, lea.get("leaId"), from));
        select.where(cb.and(cb.equal(student.get("studentRefId"), refId), lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())));
        select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

        Query<SchoolWrapper> q = getSession().createQuery(select);
        if(metaData.getPaging().isPaged()) {
            q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
            q.setMaxResults(metaData.getPaging().getPageSize());
        }

        List<SchoolWrapper> instance = q.getResultList();
        instance.forEach(wrapper -> {
            Hibernate.initialize(wrapper.getSchool().getSchoolTelephones());
            Hibernate.initialize(wrapper.getSchool().getSchoolGrades());
            Hibernate.initialize(wrapper.getSchool().getSchoolIdentifiers());
        });

        if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
        return instance;
    }

    @Override
    public List<SchoolWrapper> findAllByContactRefId(MetaData metaData, String refId) throws Exception {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
        final Root<School> from = select.from(School.class);
        final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join("schoolGrades", JoinType.LEFT);
        final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join("schoolTelephones", JoinType.LEFT);
        final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join("schoolIdentifiers", JoinType.LEFT);
        final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) from.<School, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
        final Join<StudentEnrollment, Student> student = (Join<StudentEnrollment, Student>) studentEnrollments.<StudentEnrollment, Student>join("student", JoinType.INNER);
        final SetJoin<Student, StudentContactRelationship> studentContactRelationships = (SetJoin<Student, StudentContactRelationship>) student.<Student, StudentContactRelationship>join("studentContactRelationships", JoinType.INNER);
        final Join<StudentContactRelationship, StudentContact> contact = (Join<StudentContactRelationship, StudentContact>) studentContactRelationships.<StudentContactRelationship, StudentContact>join("studentContact", JoinType.INNER);
        final Join<School, Lea> lea = (Join<School, Lea>) from.<School, Lea>join("lea", JoinType.LEFT);

        select.distinct(true);
        select.select(cb.construct(SchoolWrapper.class, lea.get("leaId"), from));
        select.where(cb.and(cb.equal(contact.get("studentContactRefId"), refId), lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())));
        select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

        Query<SchoolWrapper> q = getSession().createQuery(select);
        if(metaData.getPaging().isPaged()) {
            q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
            q.setMaxResults(metaData.getPaging().getPageSize());
        }

        List<SchoolWrapper> instance = q.getResultList();
        instance.forEach(wrapper -> {
            Hibernate.initialize(wrapper.getSchool().getSchoolTelephones());
            Hibernate.initialize(wrapper.getSchool().getSchoolGrades());
            Hibernate.initialize(wrapper.getSchool().getSchoolIdentifiers());
        });

        if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
        return instance;
    }

    @Override
    public List<SchoolWrapper> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
        final Root<School> from = select.from(School.class);
        final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join("schoolGrades", JoinType.LEFT);
        final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join("schoolTelephones", JoinType.LEFT);
        final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join("schoolIdentifiers", JoinType.LEFT);
        final Join<School, Lea> lea = (Join<School, Lea>) from.<School, Lea>join("lea", JoinType.LEFT);

        select.distinct(true);
        select.select(cb.construct(SchoolWrapper.class, lea.get("leaId"), from));
        select.where(cb.and(from.get(PRIMARY_KEY).in(refIds), lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())));
        select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

        Query<SchoolWrapper> q = getSession().createQuery(select);
        List<SchoolWrapper> instance = q.getResultList();
        instance.forEach(wrapper -> {
            Hibernate.initialize(wrapper.getSchool().getSchoolTelephones());
            Hibernate.initialize(wrapper.getSchool().getSchoolGrades());
            Hibernate.initialize(wrapper.getSchool().getSchoolIdentifiers());
        });

        if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
        return instance;
    }

    @Override
    public SchoolWrapper findByRefId(MetaData metaData, String refId) throws Exception {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
        final Root<School> from = select.from(School.class);
        final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join("schoolGrades", JoinType.LEFT);
        final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join("schoolTelephones", JoinType.LEFT);
        final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join("schoolIdentifiers", JoinType.LEFT);
        final Join<School, Lea> lea = (Join<School, Lea>) from.<School, Lea>join("lea", JoinType.LEFT);

        select.distinct(true);
        select.select(cb.construct(SchoolWrapper.class, lea.get("leaId"), from));
        select.where(cb.and(cb.equal(from.get(PRIMARY_KEY), refId), lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())));

        Query<SchoolWrapper> q = getSession().createQuery(select);

        SchoolWrapper instance = q.getSingleResult();
        Hibernate.initialize(instance.getSchool().getSchoolTelephones());
        Hibernate.initialize(instance.getSchool().getSchoolGrades());
        Hibernate.initialize(instance.getSchool().getSchoolIdentifiers());
        return instance;
    }

    @Override
    public SchoolWrapper findByLocalId(MetaData metaData, String localId) throws Exception {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
        final Root<School> from = select.from(School.class);
        final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join("schoolGrades", JoinType.LEFT);
        final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join("schoolTelephones", JoinType.LEFT);
        final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join("schoolIdentifiers", JoinType.LEFT);
        final Join<School, Lea> lea = (Join<School, Lea>) from.<School, Lea>join("lea", JoinType.LEFT);

        select.distinct(true);
        select.select(cb.construct(SchoolWrapper.class, lea.get("leaId"), from));
        select.where(cb.and(cb.equal(schoolIdentifiers.get(LOCAL_ID_KEY), localId), cb.equal(schoolIdentifiers.get(IDENTIFICATION_SYSTEM_CODE), "LEA"), lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())));

        Query<SchoolWrapper> q = getSession().createQuery(select);

        SchoolWrapper instance = q.getSingleResult();
        Hibernate.initialize(instance.getSchool().getSchoolTelephones());
        Hibernate.initialize(instance.getSchool().getSchoolGrades());
        Hibernate.initialize(instance.getSchool().getSchoolIdentifiers());
        return instance;
    }

    @Override
    public SchoolWrapper findByBEDSId(MetaData metaData, String localId) throws Exception {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
        final Root<School> from = select.from(School.class);
        final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join("schoolGrades", JoinType.LEFT);
        final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join("schoolTelephones", JoinType.LEFT);
        final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join("schoolIdentifiers", JoinType.LEFT);
        final Join<School, Lea> lea = (Join<School, Lea>) from.<School, Lea>join("lea", JoinType.LEFT);

        select.distinct(true);
        select.select(cb.construct(SchoolWrapper.class, lea.get("leaId"), from));
        select.where(cb.and(cb.equal(schoolIdentifiers.get(LOCAL_ID_KEY), localId), cb.equal(schoolIdentifiers.get(IDENTIFICATION_SYSTEM_CODE), "BEDS"), lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())));

        Query<SchoolWrapper> q = getSession().createQuery(select);

        SchoolWrapper instance = q.getSingleResult();
        Hibernate.initialize(instance.getSchool().getSchoolTelephones());
        Hibernate.initialize(instance.getSchool().getSchoolGrades());
        Hibernate.initialize(instance.getSchool().getSchoolIdentifiers());
        return instance;
    }

    @Override
    public void save(School instance) {
        super.persist(instance);
    }

    @Override
    public void update(School instance) {
        super.saveOrUpdate(instance);
    }

    @Override
    public void delete(School instance) {
        super.delete(instance);
    }
}
