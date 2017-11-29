package org.ricone.api.xPress.request.xStaff;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.ricone.api.AbstractDAO;
import org.ricone.api.core.model.*;
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
	public List<Staff> findAll(MetaData metaData) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Staff> select = cb.createQuery(Staff.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>fetch("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>fetch("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>fetch("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>fetch("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where(lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds()));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Staff> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<Staff> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<Staff> findAllByLeaRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Staff> select = cb.createQuery(Staff.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>fetch("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>fetch("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>fetch("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>fetch("lea", JoinType.LEFT);

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

		Query<Staff> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<Staff> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<Staff> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Staff> select = cb.createQuery(Staff.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>fetch("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>fetch("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>fetch("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>fetch("lea", JoinType.LEFT);

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

		Query<Staff> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<Staff> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<Staff> findAllByCourseRefId(MetaData metaData, String refId) throws Exception {
		//TODO - Not working correctly.
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Staff> select = cb.createQuery(Staff.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffCourseSection> staffCourseSections = (SetJoin<Staff, StaffCourseSection>) from.<Staff, StaffCourseSection>fetch("staffCourseSections", JoinType.LEFT);
		final Join<StaffCourseSection, CourseSection> courseSection = (Join<StaffCourseSection, CourseSection>) staffCourseSections.<StaffCourseSection, CourseSection>fetch("courseSection", JoinType.LEFT);
		final Join<CourseSection, Course> course = (Join<CourseSection, Course>) courseSection.<CourseSection, Course>fetch("course", JoinType.LEFT);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>fetch("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>fetch("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>fetch("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>fetch("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				cb.equal(course.get("courseRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Staff> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<Staff> instance = q.getResultList();
		instance.forEach(staff -> {
			Hibernate.initialize(staff.getStaffCourseSections());
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<Staff> findAllByRosterRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Staff> select = cb.createQuery(Staff.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffCourseSection> staffCourseSections = (SetJoin<Staff, StaffCourseSection>) from.<Staff, StaffCourseSection>fetch("staffCourseSections", JoinType.LEFT);
		final Join<StaffCourseSection, CourseSection> courseSection = (Join<StaffCourseSection, CourseSection>) staffCourseSections.<StaffCourseSection, CourseSection>fetch("courseSection", JoinType.LEFT);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>fetch("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>fetch("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>fetch("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>fetch("lea", JoinType.LEFT);

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

		Query<Staff> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<Staff> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<Staff> findAllByStudentRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Staff> select = cb.createQuery(Staff.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffCourseSection> staffCourseSections = (SetJoin<Staff, StaffCourseSection>) from.<Staff, StaffCourseSection>fetch("staffCourseSections", JoinType.LEFT);
		final Join<StaffCourseSection, CourseSection> courseSection = (Join<StaffCourseSection, CourseSection>) staffCourseSections.<StaffCourseSection, CourseSection>fetch("courseSection", JoinType.LEFT);
		final SetJoin<CourseSection, StudentCourseSection> studentCourseSections = (SetJoin<CourseSection, StudentCourseSection>) courseSection.<CourseSection, StudentCourseSection>fetch("studentCourseSections", JoinType.LEFT);
		final Join<StudentCourseSection, Student> student = (Join<StudentCourseSection, Student>) studentCourseSections.<StudentCourseSection, Student>fetch("student", JoinType.LEFT);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>fetch("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>fetch("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>fetch("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>fetch("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				cb.equal(student.get("studentRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Staff> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<Staff> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<Staff> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Staff> select = cb.createQuery(Staff.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>fetch("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>fetch("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>fetch("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>fetch("lea", JoinType.LEFT);

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

		Query<Staff> q = getSession().createQuery(select);
		List<Staff> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public Staff findByRefId(MetaData metaData, String refId) throws NotFoundException
	{
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Staff> select = cb.createQuery(Staff.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>fetch("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>fetch("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>fetch("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>fetch("lea", JoinType.LEFT);

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

		Query<Staff> q = getSession().createQuery(select);
		return q.getSingleResult();
	}

	@Override
	public Staff findByLocalId(MetaData metaData, String localId) throws NotFoundException
	{
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Staff> select = cb.createQuery(Staff.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>fetch("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>fetch("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>fetch("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>fetch("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
					cb.equal(staffIdentifiers.get(ID_KEY), localId),
					cb.equal(staffIdentifiers.get(IDENTIFICATION_SYSTEM_CODE), "District"),
					lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

		Query<Staff> q = getSession().createQuery(select);
		return q.getSingleResult();
	}

	@Override
	public Staff findByStateId(MetaData metaData, String stateId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Staff> select = cb.createQuery(Staff.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>fetch("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>fetch("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>fetch("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>fetch("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				cb.equal(staffIdentifiers.get(ID_KEY), stateId),
				cb.equal(staffIdentifiers.get(IDENTIFICATION_SYSTEM_CODE), "State"),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

		Query<Staff> q = getSession().createQuery(select);
		return q.getSingleResult();
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
