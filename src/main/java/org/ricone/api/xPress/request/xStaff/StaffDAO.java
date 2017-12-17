package org.ricone.api.xPress.request.xStaff;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.ricone.api.AbstractDAO;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.StaffWrapper;
import org.ricone.authentication.MetaData;
import org.ricone.error.exception.NoContentException;
import org.ricone.error.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Set;

@Repository("StaffDAO")
@SuppressWarnings("unchecked")
public class StaffDAO extends AbstractDAO<Integer, Staff> implements IStaffDAO
{
	private final String PRIMARY_KEY = "staffRefId";
	private final String ID_KEY = "staffId";
	private final String IDENTIFICATION_SYSTEM_CODE = "identificationSystemCode";

	@Override
	public List<StaffWrapper> findAll(MetaData metaData) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StaffWrapper> select = cb.createQuery(StaffWrapper.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>join("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>join("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>join("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StaffWrapper.class, lea.get("leaId"), from));
		select.where(lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds()));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<StaffWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<StaffWrapper> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<StaffWrapper> findAllByLeaRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StaffWrapper> select = cb.createQuery(StaffWrapper.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>join("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>join("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>join("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StaffWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(lea.get("leaRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<StaffWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<StaffWrapper> instance = q.getResultList();
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStaff().getStaffAssignments());
			Hibernate.initialize(wrapper.getStaff().getStaffEmails());
			Hibernate.initialize(wrapper.getStaff().getStaffIdentifiers());
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<StaffWrapper> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StaffWrapper> select = cb.createQuery(StaffWrapper.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>join("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>join("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>join("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StaffWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(school.get("schoolRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<StaffWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<StaffWrapper> instance = q.getResultList();
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStaff().getStaffAssignments());
			Hibernate.initialize(wrapper.getStaff().getStaffEmails());
			Hibernate.initialize(wrapper.getStaff().getStaffIdentifiers());
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<StaffWrapper> findAllByCourseRefId(MetaData metaData, String refId) throws Exception {
		//TODO - Not working correctly.
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StaffWrapper> select = cb.createQuery(StaffWrapper.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffCourseSection> staffCourseSections = (SetJoin<Staff, StaffCourseSection>) from.<Staff, StaffCourseSection>join("staffCourseSections", JoinType.LEFT);
		final Join<StaffCourseSection, CourseSection> courseSection = (Join<StaffCourseSection, CourseSection>) staffCourseSections.<StaffCourseSection, CourseSection>join("courseSection", JoinType.LEFT);
		final Join<CourseSection, Course> course = (Join<CourseSection, Course>) courseSection.<CourseSection, Course>join("course", JoinType.LEFT);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>join("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>join("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>join("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StaffWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(course.get("courseRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<StaffWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<StaffWrapper> instance = q.getResultList();
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStaff().getStaffAssignments());
			Hibernate.initialize(wrapper.getStaff().getStaffEmails());
			Hibernate.initialize(wrapper.getStaff().getStaffIdentifiers());
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<StaffWrapper> findAllByRosterRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StaffWrapper> select = cb.createQuery(StaffWrapper.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffCourseSection> staffCourseSections = (SetJoin<Staff, StaffCourseSection>) from.<Staff, StaffCourseSection>join("staffCourseSections", JoinType.LEFT);
		final Join<StaffCourseSection, CourseSection> courseSection = (Join<StaffCourseSection, CourseSection>) staffCourseSections.<StaffCourseSection, CourseSection>join("courseSection", JoinType.LEFT);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>join("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>join("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>join("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StaffWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(courseSection.get("courseSectionRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<StaffWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<StaffWrapper> instance = q.getResultList();
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStaff().getStaffAssignments());
			Hibernate.initialize(wrapper.getStaff().getStaffEmails());
			Hibernate.initialize(wrapper.getStaff().getStaffIdentifiers());
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<StaffWrapper> findAllByStudentRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StaffWrapper> select = cb.createQuery(StaffWrapper.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffCourseSection> staffCourseSections = (SetJoin<Staff, StaffCourseSection>) from.<Staff, StaffCourseSection>join("staffCourseSections", JoinType.LEFT);
		final Join<StaffCourseSection, CourseSection> courseSection = (Join<StaffCourseSection, CourseSection>) staffCourseSections.<StaffCourseSection, CourseSection>join("courseSection", JoinType.LEFT);
		final SetJoin<CourseSection, StudentCourseSection> studentCourseSections = (SetJoin<CourseSection, StudentCourseSection>) courseSection.<CourseSection, StudentCourseSection>join("studentCourseSections", JoinType.LEFT);
		final Join<StudentCourseSection, Student> student = (Join<StudentCourseSection, Student>) studentCourseSections.<StudentCourseSection, Student>join("student", JoinType.LEFT);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>join("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>join("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>join("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StaffWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(student.get("studentRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<StaffWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<StaffWrapper> instance = q.getResultList();
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStaff().getStaffAssignments());
			Hibernate.initialize(wrapper.getStaff().getStaffEmails());
			Hibernate.initialize(wrapper.getStaff().getStaffIdentifiers());
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<StaffWrapper> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StaffWrapper> select = cb.createQuery(StaffWrapper.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>join("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>join("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>join("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StaffWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
					from.get(PRIMARY_KEY).in(refIds),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		Query<StaffWrapper> q = getSession().createQuery(select);

		List<StaffWrapper> instance = q.getResultList();
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStaff().getStaffAssignments());
			Hibernate.initialize(wrapper.getStaff().getStaffEmails());
			Hibernate.initialize(wrapper.getStaff().getStaffIdentifiers());
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public StaffWrapper findByRefId(MetaData metaData, String refId) throws NotFoundException
	{
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StaffWrapper> select = cb.createQuery(StaffWrapper.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>join("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>join("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>join("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StaffWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(from.get(PRIMARY_KEY), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

		Query<StaffWrapper> q = getSession().createQuery(select);
		StaffWrapper instance = q.getSingleResult();
		Hibernate.initialize(instance.getStaff().getStaffAssignments());
		Hibernate.initialize(instance.getStaff().getStaffEmails());
		Hibernate.initialize(instance.getStaff().getStaffIdentifiers());
		return instance;
	}

	@Override
	public StaffWrapper findByLocalId(MetaData metaData, String localId) throws NotFoundException
	{
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StaffWrapper> select = cb.createQuery(StaffWrapper.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>join("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>join("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>join("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StaffWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
					cb.equal(staffIdentifiers.get(ID_KEY), localId),
					cb.equal(staffIdentifiers.get(IDENTIFICATION_SYSTEM_CODE), "District"),
					lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

		Query<StaffWrapper> q = getSession().createQuery(select);
		StaffWrapper instance = q.getSingleResult();
		Hibernate.initialize(instance.getStaff().getStaffAssignments());
		Hibernate.initialize(instance.getStaff().getStaffEmails());
		Hibernate.initialize(instance.getStaff().getStaffIdentifiers());
		return instance;
	}

	@Override
	public StaffWrapper findByStateId(MetaData metaData, String stateId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<StaffWrapper> select = cb.createQuery(StaffWrapper.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>join("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>join("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>join("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StaffWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(staffIdentifiers.get(ID_KEY), stateId),
				cb.equal(staffIdentifiers.get(IDENTIFICATION_SYSTEM_CODE), "State"),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

		Query<StaffWrapper> q = getSession().createQuery(select);
		StaffWrapper instance = q.getSingleResult();
		Hibernate.initialize(instance.getStaff().getStaffAssignments());
		Hibernate.initialize(instance.getStaff().getStaffEmails());
		Hibernate.initialize(instance.getStaff().getStaffIdentifiers());
		return instance;
	}

	@Override
	public void save(Staff instance) {
		super.persist(instance);
	}

	@Override
	public void update(Staff instance) {
		super.saveOrUpdate(instance);
	}

	@Override
	public void delete(Staff instance) {
		super.delete(instance);
	}

}
