package org.ricone.api.core.request.validation;

import org.ricone.api.AbstractDAO;
import org.ricone.api.core.model.*;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;

@Repository("validationDAO")
@SuppressWarnings("unchecked")
public class ValidationDAO extends AbstractDAO<Integer, Object> implements IValidationDAO {
    private final String LEA_REF_ID = "leaRefId";
    private final String SCHOOL_REF_ID = "schoolRefId";
    private final String EMAIL_TYPE = "emailTypeCode";
    private final String MEMBERSHIP_TYPE_CODE = "membershipTypeCode";
    private final String IDENTIFICATION_SYSTEM_CODE = "identificationSystemCode";
    private final String ORGANIZATIONAL = "organizational";


    @Override
    public int countStaffByLeaRefId(String refId) {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<Long> select = cb.createQuery(Long.class);
        final Root<Staff> from = select.from(Staff.class);
        final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>join("staffAssignments", JoinType.LEFT);
        final Join<StaffAssignment, School> school = staffAssignments.join("school", JoinType.LEFT);
        final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);

        select.select(cb.countDistinct(from));
        select.where(cb.equal(lea.get(LEA_REF_ID), refId));
        return getSession().createQuery(select).getSingleResult().intValue();
    }

    @Override
    public int countStaffPrimaryAssignmentsBySchoolRefId(String refId) {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<Long> select = cb.createQuery(Long.class);
        final Root<StaffAssignment> from = select.from(StaffAssignment.class);
        final Join<StaffAssignment, School> school = from.join("school", JoinType.LEFT);

        select.select(cb.countDistinct(from));
        select.where(cb.and(cb.equal(from.get("primaryAssignment"), true), cb.equal(school.get(SCHOOL_REF_ID), refId)));
        return getSession().createQuery(select).getSingleResult().intValue();
    }

    @Override
    public int countStaffPrimaryEmailsByLeaRefId(String refId) {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<Long> select = cb.createQuery(Long.class);
        final Root<StaffEmail> from = select.from(StaffEmail.class);
        final Join<StaffEmail, Staff> staff = from.join("staff", JoinType.LEFT);
        final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) staff.<Staff, StaffAssignment>join("staffAssignments", JoinType.LEFT);
        final Join<StaffAssignment, School> school = staffAssignments.join("school", JoinType.LEFT);
        final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);

        select.select(cb.countDistinct(from));
        select.where(cb.and(cb.equal(from.get(EMAIL_TYPE), ORGANIZATIONAL), cb.equal(lea.get(LEA_REF_ID), refId)));
        return getSession().createQuery(select).getSingleResult().intValue();
    }

    @Override
    public int countStaffLocalIdsByLeaRefId(String refId) {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<Long> select = cb.createQuery(Long.class);
        final Root<StaffIdentifier> from = select.from(StaffIdentifier.class);
        final Join<StaffEmail, Staff> staff = from.join("staff", JoinType.LEFT);
        final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) staff.<Staff, StaffAssignment>join("staffAssignments", JoinType.LEFT);
        final Join<StaffAssignment, School> school = staffAssignments.join("school", JoinType.LEFT);
        final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);

        select.select(cb.countDistinct(from));
        select.where(cb.and(cb.equal(from.get(IDENTIFICATION_SYSTEM_CODE), "district"), cb.equal(lea.get(LEA_REF_ID), refId)));
        return getSession().createQuery(select).getSingleResult().intValue();
    }

    @Override
    public int countStudentsByLeaRefId(String refId) {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<Long> select = cb.createQuery(Long.class);
        final Root<Student> from = select.from(Student.class);
        final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
        final Join<StudentEnrollment, School> school = studentEnrollments.join("school", JoinType.LEFT);
        final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);

        select.select(cb.countDistinct(from));
        select.where(cb.equal(lea.get(LEA_REF_ID), refId));
        return getSession().createQuery(select).getSingleResult().intValue();
    }

    @Override
    public int countStudentPrimaryEnrollmentsBySchoolRefId(String refId) {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<Long> select = cb.createQuery(Long.class);
        final Root<StudentEnrollment> from = select.from(StudentEnrollment.class);
        final Join<StudentEnrollment, School> school = from.join("school", JoinType.LEFT);

        select.select(cb.countDistinct(from));
        select.where(cb.and(cb.equal(from.get(MEMBERSHIP_TYPE_CODE), "Home"), cb.equal(school.get(SCHOOL_REF_ID), refId)));
        return getSession().createQuery(select).getSingleResult().intValue();
    }

    @Override
    public int countStudentPrimaryEmailsByLeaRefId(String refId) {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<Long> select = cb.createQuery(Long.class);
        final Root<StudentEmail> from = select.from(StudentEmail.class);
        final Join<StudentEmail, Student> student = from.join("student", JoinType.LEFT);
        final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
        final Join<StudentEnrollment, School> school = studentEnrollments.join("school", JoinType.LEFT);
        final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);

        select.select(cb.countDistinct(from));
        select.where(cb.and(cb.equal(from.get(EMAIL_TYPE), ORGANIZATIONAL), cb.equal(lea.get(LEA_REF_ID), refId)));
        return getSession().createQuery(select).getSingleResult().intValue();
    }

    @Override
    public int countStudentLocalIdsByLeaRefId(String refId) {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<Long> select = cb.createQuery(Long.class);
        final Root<StudentIdentifier> from = select.from(StudentIdentifier.class);
        final Join<StudentEmail, Student> student = from.join("student", JoinType.LEFT);
        final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
        final Join<StudentEnrollment, School> school = studentEnrollments.join("school", JoinType.LEFT);
        final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);

        select.select(cb.countDistinct(from));
        select.where(cb.and(cb.equal(from.get(IDENTIFICATION_SYSTEM_CODE), "district"), cb.equal(lea.get(LEA_REF_ID), refId)));
        return getSession().createQuery(select).getSingleResult().intValue();
    }

    @Override
    public int countCoursesBySchoolRefId(String refId) {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<Long> select = cb.createQuery(Long.class);
        final Root<Course> from = select.from(Course.class);
        final Join<Course, School> school = from.join("school", JoinType.LEFT);

        select.select(cb.countDistinct(from));
        select.where(cb.equal(school.get(SCHOOL_REF_ID), refId));

        return getSession().createQuery(select).getSingleResult().intValue();
    }

    @Override
    public int countRostersBySchoolRefId(String refId) {
        final CriteriaBuilder cb = getSession().getCriteriaBuilder();
        final CriteriaQuery<Long> select = cb.createQuery(Long.class);
        final Root<CourseSection> from = select.from(CourseSection.class);
        final Join<CourseSection, Course> course = from.join("course", JoinType.LEFT);
        final Join<Course, School> school = course.join("school", JoinType.LEFT);

        select.select(cb.countDistinct(from));
        select.where(cb.equal(school.get(SCHOOL_REF_ID), refId));

        return getSession().createQuery(select).getSingleResult().intValue();
    }
}
