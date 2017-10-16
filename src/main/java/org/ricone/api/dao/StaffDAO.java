package org.ricone.api.dao;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.ricone.api.cache.CacheContainer;
import org.ricone.api.exception.NoContentException;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;

@Repository("StaffDAO")
@SuppressWarnings("unchecked")
public class StaffDAO extends AbstractDAO<Integer, Staff> implements IStaffDAO
{
	private final String PRIMARY_KEY = "staffRefId";
	private final String LOCAL_ID_KEY = "staffId";
	private final String IDENTIFICATION_SYSTEM_CODE = "identificationSystemCode";
	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<Staff> findAll(Pageable pageRequest) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Staff> select = cb.createQuery(Staff.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffCourseSection> staffCourseSection = (SetJoin<Staff, StaffCourseSection>) from.<Staff, StaffCourseSection>fetch("staffCourseSection", JoinType.LEFT);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>fetch("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>fetch("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>fetch("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>fetch("school", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Staff> q = getSession().createQuery(select);
		q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
		q.setMaxResults(pageRequest.getPageSize());
		List<Staff> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<Staff> findAllByLea(Pageable pageRequest, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Staff> select = cb.createQuery(Staff.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffCourseSection> staffCourseSection = (SetJoin<Staff, StaffCourseSection>) from.<Staff, StaffCourseSection>fetch("staffCourseSection", JoinType.LEFT);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>fetch("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>fetch("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>fetch("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>fetch("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(lea.get("leaRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Staff> q = getSession().createQuery(select);
		q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
		q.setMaxResults(pageRequest.getPageSize());
		List<Staff> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<Staff> findAllBySchool(Pageable pageRequest, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Staff> select = cb.createQuery(Staff.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffCourseSection> staffCourseSection = (SetJoin<Staff, StaffCourseSection>) from.<Staff, StaffCourseSection>fetch("staffCourseSection", JoinType.LEFT);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>fetch("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>fetch("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>fetch("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>fetch("school", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(school.get("schoolRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Staff> q = getSession().createQuery(select);
		q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
		q.setMaxResults(pageRequest.getPageSize());
		List<Staff> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<Staff> findAllByCourse(Pageable pageRequest, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Staff> select = cb.createQuery(Staff.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffCourseSection> staffCourseSection = (SetJoin<Staff, StaffCourseSection>) from.<Staff, StaffCourseSection>fetch("staffCourseSection", JoinType.LEFT);
		final Join<StaffCourseSection, CourseSection> courseSection = (Join<StaffCourseSection, CourseSection>) staffCourseSection.<StaffCourseSection, CourseSection>fetch("courseSection", JoinType.LEFT);
		final Join<CourseSection, Course> course = (Join<CourseSection, Course>) courseSection.<CourseSection, Course>fetch("course", JoinType.LEFT);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>fetch("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>fetch("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>fetch("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>fetch("school", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(course.get("courseRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Staff> q = getSession().createQuery(select);
		q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
		q.setMaxResults(pageRequest.getPageSize());
		List<Staff> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<Staff> findAllByRoster(Pageable pageRequest, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Staff> select = cb.createQuery(Staff.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffCourseSection> staffCourseSection = (SetJoin<Staff, StaffCourseSection>) from.<Staff, StaffCourseSection>fetch("staffCourseSection", JoinType.LEFT);
		final Join<StaffCourseSection, CourseSection> courseSection = (Join<StaffCourseSection, CourseSection>) staffCourseSection.<StaffCourseSection, CourseSection>fetch("courseSection", JoinType.LEFT);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>fetch("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>fetch("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>fetch("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>fetch("school", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(courseSection.get("courseSectionRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Staff> q = getSession().createQuery(select);
		q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
		q.setMaxResults(pageRequest.getPageSize());
		List<Staff> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<Staff> findAllByStudent(Pageable pageRequest, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Staff> select = cb.createQuery(Staff.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffCourseSection> staffCourseSection = (SetJoin<Staff, StaffCourseSection>) from.<Staff, StaffCourseSection>fetch("staffCourseSection", JoinType.LEFT);
		final Join<StaffCourseSection, CourseSection> courseSection = (Join<StaffCourseSection, CourseSection>) staffCourseSection.<StaffCourseSection, CourseSection>fetch("courseSection", JoinType.LEFT);
		final SetJoin<CourseSection, StudentCourseSection> studentCourseSections = (SetJoin<CourseSection, StudentCourseSection>) courseSection.<CourseSection, StudentCourseSection>fetch("studentCourseSections", JoinType.LEFT);
		final Join<StudentCourseSection, Student> student = (Join<StudentCourseSection, Student>) studentCourseSections.<StudentCourseSection, Student>fetch("student", JoinType.LEFT);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>fetch("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>fetch("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>fetch("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>fetch("school", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(student.get("studentRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Staff> q = getSession().createQuery(select);
		q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
		q.setMaxResults(pageRequest.getPageSize());
		List<Staff> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public Staff findByRefId(String refId) throws NotFoundException
	{
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Staff> select = cb.createQuery(Staff.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffCourseSection> staffCourseSection = (SetJoin<Staff, StaffCourseSection>) from.<Staff, StaffCourseSection>fetch("staffCourseSection", JoinType.LEFT);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>fetch("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>fetch("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>fetch("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>fetch("school", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(from.get(PRIMARY_KEY), refId));

		Query<Staff> q = getSession().createQuery(select);
		return q.getSingleResult();
	}

	@Override
	public Staff findByLocalId(String localId) throws NotFoundException
	{
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Staff> select = cb.createQuery(Staff.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffCourseSection> staffCourseSection = (SetJoin<Staff, StaffCourseSection>) from.<Staff, StaffCourseSection>fetch("staffCourseSection", JoinType.LEFT);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>fetch("staffIdentifiers", JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>fetch("staffEmails", JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>fetch("staffAssignments", JoinType.LEFT);
		final Join<StaffAssignment, School> school = (Join<StaffAssignment, School>) staffAssignments.<StaffAssignment, School>fetch("school", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where(cb.and(cb.equal(staffIdentifiers.get(LOCAL_ID_KEY), localId), cb.equal(staffIdentifiers.get(IDENTIFICATION_SYSTEM_CODE), "District")));

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

	@Override
	public void deleteByRefId(String refId)
	{
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(PRIMARY_KEY, refId));
		Staff instance = (Staff)criteria.uniqueResult();
		super.delete(instance);
	}
}
