package org.ricone.api.dao;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.ricone.api.cache.CacheContainer;
import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.exception.NoContentException;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.*;
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
	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<Student> findAll(MetaData metaData) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Student> select = cb.createQuery(Student.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>fetch("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>fetch("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where(lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds()));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Student> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<Student> instance = q.getResultList();
		instance.forEach(o -> {
			Hibernate.initialize(o.getStudentAcademicRecords());
			Hibernate.initialize(o.getStudentAddresses());
			Hibernate.initialize(o.getStudentEmails());
			Hibernate.initialize(o.getStudentIdentifiers());
			Hibernate.initialize(o.getStudentLanguages());
			Hibernate.initialize(o.getStudentOtherNames());
			Hibernate.initialize(o.getStudentRaces());
			Hibernate.initialize(o.getStudentTelephones());

			Hibernate.initialize(o.getStudentContactRelationships());

			Hibernate.initialize(o.getStudentEnrollments());
			o.getStudentEnrollments().forEach(se ->
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
	public List<Student> findAllByLeaRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Student> select = cb.createQuery(Student.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>fetch("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>fetch("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				cb.equal(lea.get("leaRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Student> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<Student> instance = q.getResultList();
		instance.forEach(o -> {
			Hibernate.initialize(o.getStudentAcademicRecords());
			Hibernate.initialize(o.getStudentAddresses());
			Hibernate.initialize(o.getStudentEmails());
			Hibernate.initialize(o.getStudentIdentifiers());
			Hibernate.initialize(o.getStudentLanguages());
			Hibernate.initialize(o.getStudentOtherNames());
			Hibernate.initialize(o.getStudentRaces());
			Hibernate.initialize(o.getStudentTelephones());

			Hibernate.initialize(o.getStudentContactRelationships());

			Hibernate.initialize(o.getStudentEnrollments());
			o.getStudentEnrollments().forEach(se ->
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
	public List<Student> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Student> select = cb.createQuery(Student.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>fetch("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>fetch("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				cb.equal(school.get("schoolRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Student> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<Student> instance = q.getResultList();
		instance.forEach(o -> {
			Hibernate.initialize(o.getStudentAcademicRecords());
			Hibernate.initialize(o.getStudentAddresses());
			Hibernate.initialize(o.getStudentEmails());
			Hibernate.initialize(o.getStudentIdentifiers());
			Hibernate.initialize(o.getStudentLanguages());
			Hibernate.initialize(o.getStudentOtherNames());
			Hibernate.initialize(o.getStudentRaces());
			Hibernate.initialize(o.getStudentTelephones());

			Hibernate.initialize(o.getStudentContactRelationships());

			Hibernate.initialize(o.getStudentEnrollments());
			o.getStudentEnrollments().forEach(se ->
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
	public List<Student> findAllByRosterRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Student> select = cb.createQuery(Student.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentCourseSection> studentCourseSections = (SetJoin<Student, StudentCourseSection>) from.<Student, StudentCourseSection>fetch("studentCourseSections", JoinType.LEFT);
		final Join<StudentCourseSection, CourseSection> courseSection = (Join<StudentCourseSection, CourseSection>)studentCourseSections.<StudentCourseSection, CourseSection>fetch("courseSection", JoinType.LEFT);

		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>fetch("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>fetch("lea", JoinType.LEFT);


		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				cb.equal(courseSection.get("courseSectionRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Student> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<Student> instance = q.getResultList();
		instance.forEach(o -> {
			Hibernate.initialize(o.getStudentAcademicRecords());
			Hibernate.initialize(o.getStudentAddresses());
			Hibernate.initialize(o.getStudentEmails());
			Hibernate.initialize(o.getStudentIdentifiers());
			Hibernate.initialize(o.getStudentLanguages());
			Hibernate.initialize(o.getStudentOtherNames());
			Hibernate.initialize(o.getStudentRaces());
			Hibernate.initialize(o.getStudentTelephones());

			Hibernate.initialize(o.getStudentContactRelationships());

			Hibernate.initialize(o.getStudentEnrollments());
			o.getStudentEnrollments().forEach(se ->
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
	public List<Student> findAllByStaffRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Student> select = cb.createQuery(Student.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentCourseSection> studentCourseSections = (SetJoin<Student, StudentCourseSection>) from.<Student, StudentCourseSection>fetch("studentCourseSections", JoinType.LEFT);
		final Join<StudentCourseSection, CourseSection> courseSection = (Join<StudentCourseSection, CourseSection>)studentCourseSections.<StudentCourseSection, CourseSection>fetch("courseSection", JoinType.INNER);
		final SetJoin<CourseSection, StaffCourseSection> staffCourseSections = (SetJoin<CourseSection, StaffCourseSection>) courseSection.<CourseSection, StaffCourseSection>fetch("staffCourseSections", JoinType.LEFT);
		final Join<StaffCourseSection, Staff> staff = (Join<StaffCourseSection, Staff>)staffCourseSections.<StaffCourseSection, Staff>fetch("staff", JoinType.LEFT);

		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>fetch("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>fetch("lea", JoinType.LEFT);


		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				cb.equal(staff.get("staffRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Student> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<Student> instance = q.getResultList();
		instance.forEach(o -> {
			Hibernate.initialize(o.getStudentAcademicRecords());
			Hibernate.initialize(o.getStudentAddresses());
			Hibernate.initialize(o.getStudentEmails());
			Hibernate.initialize(o.getStudentIdentifiers());
			Hibernate.initialize(o.getStudentLanguages());
			Hibernate.initialize(o.getStudentOtherNames());
			Hibernate.initialize(o.getStudentRaces());
			Hibernate.initialize(o.getStudentTelephones());

			Hibernate.initialize(o.getStudentContactRelationships());

			Hibernate.initialize(o.getStudentEnrollments());
			o.getStudentEnrollments().forEach(se ->
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
	public List<Student> findAllByContactRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Student> select = cb.createQuery(Student.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentContactRelationship> studentContactRelationships = (SetJoin<Student, StudentContactRelationship>) from.<Student, StudentContactRelationship>fetch("studentContactRelationships", JoinType.LEFT);
		final Join<StudentContactRelationship, StudentContact> studentContact = (Join<StudentContactRelationship, StudentContact>)studentContactRelationships.<StudentContactRelationship, StudentContact>fetch("studentContact", JoinType.LEFT);

		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>fetch("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>fetch("lea", JoinType.LEFT);


		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				cb.equal(studentContact.get("studentContactRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Student> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<Student> instance = q.getResultList();
		instance.forEach(o -> {
			Hibernate.initialize(o.getStudentAcademicRecords());
			Hibernate.initialize(o.getStudentAddresses());
			Hibernate.initialize(o.getStudentEmails());
			Hibernate.initialize(o.getStudentIdentifiers());
			Hibernate.initialize(o.getStudentLanguages());
			Hibernate.initialize(o.getStudentOtherNames());
			Hibernate.initialize(o.getStudentRaces());
			Hibernate.initialize(o.getStudentTelephones());
			Hibernate.initialize(o.getStudentContactRelationships());
			Hibernate.initialize(o.getStudentEnrollments());
			o.getStudentEnrollments().forEach(se ->
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
	public List<Student> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Student> select = cb.createQuery(Student.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>fetch("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>fetch("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				from.get(PRIMARY_KEY).in(refIds),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

		Query<Student> q = getSession().createQuery(select);

		List<Student> instance = q.getResultList();
		instance.forEach(o -> {
			Hibernate.initialize(o.getStudentAcademicRecords());
			Hibernate.initialize(o.getStudentAddresses());
			Hibernate.initialize(o.getStudentEmails());
			Hibernate.initialize(o.getStudentIdentifiers());
			Hibernate.initialize(o.getStudentLanguages());
			Hibernate.initialize(o.getStudentOtherNames());
			Hibernate.initialize(o.getStudentRaces());
			Hibernate.initialize(o.getStudentTelephones());

			Hibernate.initialize(o.getStudentContactRelationships());

			Hibernate.initialize(o.getStudentEnrollments());
			o.getStudentEnrollments().forEach(se ->
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
	public Student findByRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Student> select = cb.createQuery(Student.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>fetch("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>fetch("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
					cb.equal(from.get(PRIMARY_KEY), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

		Query<Student> q = getSession().createQuery(select);
		Student instance = q.getSingleResult();

		Hibernate.initialize(instance.getStudentAcademicRecords());
		Hibernate.initialize(instance.getStudentAddresses());
		Hibernate.initialize(instance.getStudentEmails());
		Hibernate.initialize(instance.getStudentIdentifiers());
		Hibernate.initialize(instance.getStudentLanguages());
		Hibernate.initialize(instance.getStudentOtherNames());
		Hibernate.initialize(instance.getStudentRaces());
		Hibernate.initialize(instance.getStudentTelephones());
		Hibernate.initialize(instance.getStudentContactRelationships());
		Hibernate.initialize(instance.getStudentEnrollments());
		instance.getStudentEnrollments().forEach(se ->
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
	public Student findByLocalId(MetaData metaData, String localId) throws NotFoundException {
		//TODO - StateId doesn't show up when searching by LocalId
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Student> select = cb.createQuery(Student.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentIdentifier> studentIdentifiers = (SetJoin<Student, StudentIdentifier>) from.<Student, StudentIdentifier>fetch("studentIdentifiers", JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>fetch("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>fetch("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				cb.equal(studentIdentifiers.get(ID_KEY), localId),
				cb.equal(studentIdentifiers.get(IDENTIFICATION_SYSTEM_CODE), "District"),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

		Query<Student> q = getSession().createQuery(select);
		Student instance = q.getSingleResult();

		Hibernate.initialize(instance.getStudentAcademicRecords());
		Hibernate.initialize(instance.getStudentAddresses());
		Hibernate.initialize(instance.getStudentEmails());
		Hibernate.initialize(instance.getStudentIdentifiers());
		Hibernate.initialize(instance.getStudentLanguages());
		Hibernate.initialize(instance.getStudentOtherNames());
		Hibernate.initialize(instance.getStudentRaces());
		Hibernate.initialize(instance.getStudentTelephones());
		Hibernate.initialize(instance.getStudentContactRelationships());
		Hibernate.initialize(instance.getStudentEnrollments());
		instance.getStudentEnrollments().forEach(se ->
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
	public Student findByStateId(MetaData metaData, String stateId) throws Exception {
		//TODO - LocalId doesn't show up when searching by StateId
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Student> select = cb.createQuery(Student.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentIdentifier> studentIdentifiers = (SetJoin<Student, StudentIdentifier>) from.<Student, StudentIdentifier>fetch("studentIdentifiers", JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>fetch("studentEnrollments", JoinType.LEFT);
		final Join<StudentEnrollment, School> school = (Join<StudentEnrollment, School>)studentEnrollments.<StudentEnrollment, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>)school.<School, Lea>fetch("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				cb.equal(studentIdentifiers.get(ID_KEY), stateId),
				cb.equal(studentIdentifiers.get(IDENTIFICATION_SYSTEM_CODE), "State"),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

		Query<Student> q = getSession().createQuery(select);
		Student instance = q.getSingleResult();

		Hibernate.initialize(instance.getStudentAcademicRecords());
		Hibernate.initialize(instance.getStudentAddresses());
		Hibernate.initialize(instance.getStudentEmails());
		Hibernate.initialize(instance.getStudentIdentifiers());
		Hibernate.initialize(instance.getStudentLanguages());
		Hibernate.initialize(instance.getStudentOtherNames());
		Hibernate.initialize(instance.getStudentRaces());
		Hibernate.initialize(instance.getStudentTelephones());
		Hibernate.initialize(instance.getStudentContactRelationships());
		Hibernate.initialize(instance.getStudentEnrollments());
		instance.getStudentEnrollments().forEach(se ->
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
