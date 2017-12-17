package org.ricone.api.xPress.request.xStudent;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.ricone.api.AbstractDAO;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.authentication.MetaData;
import org.ricone.error.exception.NoContentException;
import org.ricone.error.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Set;

@Repository("StudentDAO")
@SuppressWarnings("unchecked")
public class StudentDAO extends AbstractDAO<Integer, Student> implements IStudentDAO
{
	private final String PRIMARY_KEY = "studentRefId";
	private final String ID_KEY = "studentId";
	private final String IDENTIFICATION_SYSTEM_CODE = "identificationSystemCode";

	@Override
	public List<StudentWrapper> findAll(MetaData metaData) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StudentWrapper> select = cb.createQuery(StudentWrapper.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentWrapper.class, lea.get("leaId"), from));
		select.where(lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds()));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<StudentWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<StudentWrapper> instance = q.getResultList();
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStudent().getStudentAcademicRecords());
			Hibernate.initialize(wrapper.getStudent().getStudentAddresses());
			Hibernate.initialize(wrapper.getStudent().getStudentEmails());
			Hibernate.initialize(wrapper.getStudent().getStudentIdentifiers());
			Hibernate.initialize(wrapper.getStudent().getStudentLanguages());
			Hibernate.initialize(wrapper.getStudent().getStudentOtherNames());
			Hibernate.initialize(wrapper.getStudent().getStudentRaces());
			Hibernate.initialize(wrapper.getStudent().getStudentTelephones());
			Hibernate.initialize(wrapper.getStudent().getStudentContactRelationships());

			Hibernate.initialize(wrapper.getStudent().getStudentEnrollments());
			wrapper.getStudent().getStudentEnrollments().forEach(se ->
			{
				if(se.getCounselor() != null) {
					Hibernate.initialize(se.getCounselor());
					se.getCounselor().getStaffIdentifiers().forEach(Hibernate::initialize);
				}

				if(se.getTeacher() != null) {
					Hibernate.initialize(se.getTeacher());
					se.getTeacher().getStaffIdentifiers().forEach(Hibernate::initialize);
				}

				Hibernate.initialize(se.getEntryExitCodes());
				Hibernate.initialize(se.getSchool());
			});
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<StudentWrapper> findAllByLeaRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StudentWrapper> select = cb.createQuery(StudentWrapper.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(lea.get("leaRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<StudentWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<StudentWrapper> instance = q.getResultList();
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStudent().getStudentAcademicRecords());
			Hibernate.initialize(wrapper.getStudent().getStudentAddresses());
			Hibernate.initialize(wrapper.getStudent().getStudentEmails());
			Hibernate.initialize(wrapper.getStudent().getStudentIdentifiers());
			Hibernate.initialize(wrapper.getStudent().getStudentLanguages());
			Hibernate.initialize(wrapper.getStudent().getStudentOtherNames());
			Hibernate.initialize(wrapper.getStudent().getStudentRaces());
			Hibernate.initialize(wrapper.getStudent().getStudentTelephones());
			Hibernate.initialize(wrapper.getStudent().getStudentContactRelationships());

			Hibernate.initialize(wrapper.getStudent().getStudentEnrollments());
			wrapper.getStudent().getStudentEnrollments().forEach(se ->
			{
				if(se.getCounselor() != null) {
					Hibernate.initialize(se.getCounselor());
					se.getCounselor().getStaffIdentifiers().forEach(Hibernate::initialize);
				}

				if(se.getTeacher() != null) {
					Hibernate.initialize(se.getTeacher());
					se.getTeacher().getStaffIdentifiers().forEach(Hibernate::initialize);
				}

				Hibernate.initialize(se.getEntryExitCodes());
				Hibernate.initialize(se.getSchool());
			});
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<StudentWrapper> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StudentWrapper> select = cb.createQuery(StudentWrapper.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(school.get("schoolRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<StudentWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<StudentWrapper> instance = q.getResultList();
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStudent().getStudentAcademicRecords());
			Hibernate.initialize(wrapper.getStudent().getStudentAddresses());
			Hibernate.initialize(wrapper.getStudent().getStudentEmails());
			Hibernate.initialize(wrapper.getStudent().getStudentIdentifiers());
			Hibernate.initialize(wrapper.getStudent().getStudentLanguages());
			Hibernate.initialize(wrapper.getStudent().getStudentOtherNames());
			Hibernate.initialize(wrapper.getStudent().getStudentRaces());
			Hibernate.initialize(wrapper.getStudent().getStudentTelephones());
			Hibernate.initialize(wrapper.getStudent().getStudentContactRelationships());

			Hibernate.initialize(wrapper.getStudent().getStudentEnrollments());
			wrapper.getStudent().getStudentEnrollments().forEach(se ->
			{
				if(se.getCounselor() != null) {
					Hibernate.initialize(se.getCounselor());
					se.getCounselor().getStaffIdentifiers().forEach(Hibernate::initialize);
				}

				if(se.getTeacher() != null) {
					Hibernate.initialize(se.getTeacher());
					se.getTeacher().getStaffIdentifiers().forEach(Hibernate::initialize);
				}

				Hibernate.initialize(se.getEntryExitCodes());
				Hibernate.initialize(se.getSchool());
			});
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<StudentWrapper> findAllByRosterRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StudentWrapper> select = cb.createQuery(StudentWrapper.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentCourseSection> studentCourseSections = (SetJoin<Student, StudentCourseSection>) from.<Student, StudentCourseSection>join("studentCourseSections", JoinType.LEFT);
		final Join<StudentCourseSection, CourseSection> courseSection = (Join<StudentCourseSection, CourseSection>)studentCourseSections.<StudentCourseSection, CourseSection>join("courseSection", JoinType.LEFT);

		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>join("lea", JoinType.LEFT);


		select.distinct(true);
		select.select(cb.construct(StudentWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(courseSection.get("courseSectionRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<StudentWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<StudentWrapper> instance = q.getResultList();
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStudent().getStudentAcademicRecords());
			Hibernate.initialize(wrapper.getStudent().getStudentAddresses());
			Hibernate.initialize(wrapper.getStudent().getStudentEmails());
			Hibernate.initialize(wrapper.getStudent().getStudentIdentifiers());
			Hibernate.initialize(wrapper.getStudent().getStudentLanguages());
			Hibernate.initialize(wrapper.getStudent().getStudentOtherNames());
			Hibernate.initialize(wrapper.getStudent().getStudentRaces());
			Hibernate.initialize(wrapper.getStudent().getStudentTelephones());
			Hibernate.initialize(wrapper.getStudent().getStudentContactRelationships());

			Hibernate.initialize(wrapper.getStudent().getStudentEnrollments());
			wrapper.getStudent().getStudentEnrollments().forEach(se ->
			{
				if(se.getCounselor() != null) {
					Hibernate.initialize(se.getCounselor());
					se.getCounselor().getStaffIdentifiers().forEach(Hibernate::initialize);
				}

				if(se.getTeacher() != null) {
					Hibernate.initialize(se.getTeacher());
					se.getTeacher().getStaffIdentifiers().forEach(Hibernate::initialize);
				}

				Hibernate.initialize(se.getEntryExitCodes());
				Hibernate.initialize(se.getSchool());
			});
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<StudentWrapper> findAllByStaffRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StudentWrapper> select = cb.createQuery(StudentWrapper.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentCourseSection> studentCourseSections = (SetJoin<Student, StudentCourseSection>) from.<Student, StudentCourseSection>join("studentCourseSections", JoinType.LEFT);
		final Join<StudentCourseSection, CourseSection> courseSection = (Join<StudentCourseSection, CourseSection>)studentCourseSections.<StudentCourseSection, CourseSection>join("courseSection", JoinType.INNER);
		final SetJoin<CourseSection, StaffCourseSection> staffCourseSections = (SetJoin<CourseSection, StaffCourseSection>) courseSection.<CourseSection, StaffCourseSection>join("staffCourseSections", JoinType.LEFT);
		final Join<StaffCourseSection, Staff> staff = (Join<StaffCourseSection, Staff>)staffCourseSections.<StaffCourseSection, Staff>join("staff", JoinType.LEFT);

		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>join("lea", JoinType.LEFT);


		select.distinct(true);
		select.select(cb.construct(StudentWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(staff.get("staffRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<StudentWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<StudentWrapper> instance = q.getResultList();
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStudent().getStudentAcademicRecords());
			Hibernate.initialize(wrapper.getStudent().getStudentAddresses());
			Hibernate.initialize(wrapper.getStudent().getStudentEmails());
			Hibernate.initialize(wrapper.getStudent().getStudentIdentifiers());
			Hibernate.initialize(wrapper.getStudent().getStudentLanguages());
			Hibernate.initialize(wrapper.getStudent().getStudentOtherNames());
			Hibernate.initialize(wrapper.getStudent().getStudentRaces());
			Hibernate.initialize(wrapper.getStudent().getStudentTelephones());
			Hibernate.initialize(wrapper.getStudent().getStudentContactRelationships());

			Hibernate.initialize(wrapper.getStudent().getStudentEnrollments());
			wrapper.getStudent().getStudentEnrollments().forEach(se ->
			{
				if(se.getCounselor() != null) {
					Hibernate.initialize(se.getCounselor());
					se.getCounselor().getStaffIdentifiers().forEach(Hibernate::initialize);
				}

				if(se.getTeacher() != null) {
					Hibernate.initialize(se.getTeacher());
					se.getTeacher().getStaffIdentifiers().forEach(Hibernate::initialize);
				}

				Hibernate.initialize(se.getEntryExitCodes());
				Hibernate.initialize(se.getSchool());
			});
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<StudentWrapper> findAllByContactRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StudentWrapper> select = cb.createQuery(StudentWrapper.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentContactRelationship> studentContactRelationships = (SetJoin<Student, StudentContactRelationship>) from.<Student, StudentContactRelationship>join("studentContactRelationships", JoinType.LEFT);
		final Join<StudentContactRelationship, StudentContact> studentContact = (Join<StudentContactRelationship, StudentContact>)studentContactRelationships.<StudentContactRelationship, StudentContact>join("studentContact", JoinType.LEFT);

		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>join("lea", JoinType.LEFT);


		select.distinct(true);
		select.select(cb.construct(StudentWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(studentContact.get("studentContactRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<StudentWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<StudentWrapper> instance = q.getResultList();
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStudent().getStudentAcademicRecords());
			Hibernate.initialize(wrapper.getStudent().getStudentAddresses());
			Hibernate.initialize(wrapper.getStudent().getStudentEmails());
			Hibernate.initialize(wrapper.getStudent().getStudentIdentifiers());
			Hibernate.initialize(wrapper.getStudent().getStudentLanguages());
			Hibernate.initialize(wrapper.getStudent().getStudentOtherNames());
			Hibernate.initialize(wrapper.getStudent().getStudentRaces());
			Hibernate.initialize(wrapper.getStudent().getStudentTelephones());
			Hibernate.initialize(wrapper.getStudent().getStudentContactRelationships());

			Hibernate.initialize(wrapper.getStudent().getStudentEnrollments());
			wrapper.getStudent().getStudentEnrollments().forEach(se ->
			{
				if(se.getCounselor() != null) {
					Hibernate.initialize(se.getCounselor());
					se.getCounselor().getStaffIdentifiers().forEach(Hibernate::initialize);
				}

				if(se.getTeacher() != null) {
					Hibernate.initialize(se.getTeacher());
					se.getTeacher().getStaffIdentifiers().forEach(Hibernate::initialize);
				}

				Hibernate.initialize(se.getEntryExitCodes());
				Hibernate.initialize(se.getSchool());
			});
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<StudentWrapper> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StudentWrapper> select = cb.createQuery(StudentWrapper.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				from.get(PRIMARY_KEY).in(refIds),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

		Query<StudentWrapper> q = getSession().createQuery(select);

		List<StudentWrapper> instance = q.getResultList();
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStudent().getStudentAcademicRecords());
			Hibernate.initialize(wrapper.getStudent().getStudentAddresses());
			Hibernate.initialize(wrapper.getStudent().getStudentEmails());
			Hibernate.initialize(wrapper.getStudent().getStudentIdentifiers());
			Hibernate.initialize(wrapper.getStudent().getStudentLanguages());
			Hibernate.initialize(wrapper.getStudent().getStudentOtherNames());
			Hibernate.initialize(wrapper.getStudent().getStudentRaces());
			Hibernate.initialize(wrapper.getStudent().getStudentTelephones());
			Hibernate.initialize(wrapper.getStudent().getStudentContactRelationships());

			Hibernate.initialize(wrapper.getStudent().getStudentEnrollments());
			wrapper.getStudent().getStudentEnrollments().forEach(se ->
			{
				if(se.getCounselor() != null) {
					Hibernate.initialize(se.getCounselor());
					se.getCounselor().getStaffIdentifiers().forEach(Hibernate::initialize);
				}

				if(se.getTeacher() != null) {
					Hibernate.initialize(se.getTeacher());
					se.getTeacher().getStaffIdentifiers().forEach(Hibernate::initialize);
				}

				Hibernate.initialize(se.getEntryExitCodes());
				Hibernate.initialize(se.getSchool());
			});
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public StudentWrapper findByRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StudentWrapper> select = cb.createQuery(StudentWrapper.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
					cb.equal(from.get(PRIMARY_KEY), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

		Query<StudentWrapper> q = getSession().createQuery(select);
		
		StudentWrapper instance = q.getSingleResult();
		Hibernate.initialize(instance.getStudent().getStudentAcademicRecords());
		Hibernate.initialize(instance.getStudent().getStudentAddresses());
		Hibernate.initialize(instance.getStudent().getStudentEmails());
		Hibernate.initialize(instance.getStudent().getStudentIdentifiers());
		Hibernate.initialize(instance.getStudent().getStudentLanguages());
		Hibernate.initialize(instance.getStudent().getStudentOtherNames());
		Hibernate.initialize(instance.getStudent().getStudentRaces());
		Hibernate.initialize(instance.getStudent().getStudentTelephones());
		Hibernate.initialize(instance.getStudent().getStudentContactRelationships());

		Hibernate.initialize(instance.getStudent().getStudentEnrollments());
		instance.getStudent().getStudentEnrollments().forEach(se ->
		{
			if(se.getCounselor() != null) {
				Hibernate.initialize(se.getCounselor());
				se.getCounselor().getStaffIdentifiers().forEach(Hibernate::initialize);
			}

			if(se.getTeacher() != null) {
				Hibernate.initialize(se.getTeacher());
				se.getTeacher().getStaffIdentifiers().forEach(Hibernate::initialize);
			}

			Hibernate.initialize(se.getEntryExitCodes());
			Hibernate.initialize(se.getSchool());
		});
		

		return instance;
	}

	@Override
	public StudentWrapper findByLocalId(MetaData metaData, String localId) throws NotFoundException {
		//TODO - StateId doesn't show up when searching by LocalId
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StudentWrapper> select = cb.createQuery(StudentWrapper.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentIdentifier> studentIdentifiers = (SetJoin<Student, StudentIdentifier>) from.<Student, StudentIdentifier>join("studentIdentifiers", JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(studentIdentifiers.get(ID_KEY), localId),
				cb.equal(studentIdentifiers.get(IDENTIFICATION_SYSTEM_CODE), "District"),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

		Query<StudentWrapper> q = getSession().createQuery(select);
		
		StudentWrapper instance = q.getSingleResult();
		Hibernate.initialize(instance.getStudent().getStudentAcademicRecords());
		Hibernate.initialize(instance.getStudent().getStudentAddresses());
		Hibernate.initialize(instance.getStudent().getStudentEmails());
		Hibernate.initialize(instance.getStudent().getStudentIdentifiers());
		Hibernate.initialize(instance.getStudent().getStudentLanguages());
		Hibernate.initialize(instance.getStudent().getStudentOtherNames());
		Hibernate.initialize(instance.getStudent().getStudentRaces());
		Hibernate.initialize(instance.getStudent().getStudentTelephones());
		Hibernate.initialize(instance.getStudent().getStudentContactRelationships());

		Hibernate.initialize(instance.getStudent().getStudentEnrollments());
		instance.getStudent().getStudentEnrollments().forEach(se ->
		{
			if(se.getCounselor() != null) {
				Hibernate.initialize(se.getCounselor());
				se.getCounselor().getStaffIdentifiers().forEach(Hibernate::initialize);
			}

			if(se.getTeacher() != null) {
				Hibernate.initialize(se.getTeacher());
				se.getTeacher().getStaffIdentifiers().forEach(Hibernate::initialize);
			}

			Hibernate.initialize(se.getEntryExitCodes());
			Hibernate.initialize(se.getSchool());
		});

		return instance;
	}

	@Override
	public StudentWrapper findByStateId(MetaData metaData, String stateId) throws Exception {
		//TODO - LocalId doesn't show up when searching by StateId
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StudentWrapper> select = cb.createQuery(StudentWrapper.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentIdentifier> studentIdentifiers = (SetJoin<Student, StudentIdentifier>) from.<Student, StudentIdentifier>join("studentIdentifiers", JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(studentIdentifiers.get(ID_KEY), stateId),
				cb.equal(studentIdentifiers.get(IDENTIFICATION_SYSTEM_CODE), "State"),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

		Query<StudentWrapper> q = getSession().createQuery(select);
		
		StudentWrapper instance = q.getSingleResult();
		Hibernate.initialize(instance.getStudent().getStudentAcademicRecords());
		Hibernate.initialize(instance.getStudent().getStudentAddresses());
		Hibernate.initialize(instance.getStudent().getStudentEmails());
		Hibernate.initialize(instance.getStudent().getStudentIdentifiers());
		Hibernate.initialize(instance.getStudent().getStudentLanguages());
		Hibernate.initialize(instance.getStudent().getStudentOtherNames());
		Hibernate.initialize(instance.getStudent().getStudentRaces());
		Hibernate.initialize(instance.getStudent().getStudentTelephones());
		Hibernate.initialize(instance.getStudent().getStudentContactRelationships());

		Hibernate.initialize(instance.getStudent().getStudentEnrollments());
		instance.getStudent().getStudentEnrollments().forEach(se ->
		{
			if(se.getCounselor() != null) {
				Hibernate.initialize(se.getCounselor());
				se.getCounselor().getStaffIdentifiers().forEach(Hibernate::initialize);
			}

			if(se.getTeacher() != null) {
				Hibernate.initialize(se.getTeacher());
				se.getTeacher().getStaffIdentifiers().forEach(Hibernate::initialize);
			}

			Hibernate.initialize(se.getEntryExitCodes());
			Hibernate.initialize(se.getSchool());
		});
		return instance;
	}

	@Override
	public void save(Student instance) {
		super.persist(instance);
	}

	@Override
	public void update(Student instance) {
		super.saveOrUpdate(instance);
	}

	@Override
	public void delete(Student instance) {
		super.delete(instance);
	}
}
