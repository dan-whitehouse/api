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
import java.util.ArrayList;
import java.util.List;

@Repository("rosterDAO")
@SuppressWarnings("unchecked")
public class RosterDAO extends AbstractDAO<Integer, CourseSection> implements IRosterDAO
{
	private final String PRIMARY_KEY = "courseSectionRefId";
	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<CourseSection> findAll(Pageable pageRequest) throws Exception
	{
		System.err.println("Building Query");
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<CourseSection> select = cb.createQuery(CourseSection.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = (Join<CourseSection, Course>) from.<CourseSection, Course>fetch("course", JoinType.INNER);
		final Join<Course, School> school = (Join<Course, School>) course.<Course, School>fetch("school", JoinType.LEFT);

		final Join<CourseSection, SchoolCalendarSession> schoolCalendarSession = (Join<CourseSection, SchoolCalendarSession>) from.<CourseSection, SchoolCalendarSession>fetch("schoolCalendarSession", JoinType.LEFT);
		final Join<SchoolCalendarSession, SchoolCalendar> schoolCalendar = (Join<SchoolCalendarSession, SchoolCalendar>) schoolCalendarSession.<SchoolCalendarSession, SchoolCalendar>fetch("schoolCalendar", JoinType.INNER);

		final SetJoin<CourseSection, CourseSectionSchedule> courseSectionSchedules = (SetJoin<CourseSection, CourseSectionSchedule>) from.<CourseSection, CourseSectionSchedule>fetch("courseSectionSchedules", JoinType.LEFT);

		final SetJoin<CourseSection, StaffCourseSection> staffCourseSections = (SetJoin<CourseSection, StaffCourseSection>) from.<CourseSection, StaffCourseSection>fetch("staffCourseSections", JoinType.LEFT);
		final Join<StaffCourseSection, Staff> staff = (Join<StaffCourseSection, Staff>) staffCourseSections.<StaffCourseSection, Staff>fetch("staff", JoinType.INNER);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) staff.<Staff, StaffIdentifier>fetch("staffIdentifiers", JoinType.LEFT);

		final SetJoin<CourseSection, StudentCourseSection> studentCourseSections = (SetJoin<CourseSection, StudentCourseSection>) from.<CourseSection, StudentCourseSection>fetch("studentCourseSections", JoinType.LEFT);
		final Join<StudentEnrollment, Student> student = (Join<StudentEnrollment, Student>) studentCourseSections.<StudentEnrollment, Student>fetch("student", JoinType.INNER);
		final SetJoin<Student, StudentIdentifier> studentIdentifiers = (SetJoin<Student, StudentIdentifier>) student.<Student, StudentIdentifier>fetch("studentIdentifiers", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<CourseSection> q = getSession().createQuery(select);
		q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
		q.setMaxResults(pageRequest.getPageSize());
		System.err.println("Getting Data");
		List<CourseSection> instance = q.getResultList();
		System.err.println("Initializing School Calendar Session");

		instance.forEach(sc -> {
			Hibernate.initialize(sc.getSchoolCalendarSession());
			Hibernate.initialize(sc.getSchoolCalendarSession().getSchoolCalendar());
			Hibernate.initialize(sc.getCourseSectionSchedules());
			Hibernate.initialize(sc.getStaffCourseSections());
			sc.getStaffCourseSections().forEach(tcs -> {
				Hibernate.initialize(tcs.getStaff());
				Hibernate.initialize(tcs.getStaff().getStaffIdentifiers());
			});
			Hibernate.initialize(sc.getStudentCourseSections());
			sc.getStudentCourseSections().forEach(scs -> {
				Hibernate.initialize(scs.getStudent());
				Hibernate.initialize(scs.getStudent().getStudentIdentifiers());
			});
		});


		System.err.println("Ending");
		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public CourseSection findByRefId(String refId) throws Exception
	{
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<CourseSection> select = cb.createQuery(CourseSection.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = (Join<CourseSection, Course>) from.<CourseSection, Course>fetch("course", JoinType.INNER);
		final Join<Course, School> school = (Join<Course, School>) course.<Course, School>fetch("school", JoinType.LEFT);

		final Join<CourseSection, SchoolCalendarSession> schoolCalendarSession = (Join<CourseSection, SchoolCalendarSession>) from.<CourseSection, SchoolCalendarSession>fetch("schoolCalendarSession", JoinType.LEFT);
		final Join<SchoolCalendarSession, SchoolCalendar> schoolCalendar = (Join<SchoolCalendarSession, SchoolCalendar>) schoolCalendarSession.<SchoolCalendarSession, SchoolCalendar>fetch("schoolCalendar", JoinType.INNER);

		final SetJoin<CourseSection, CourseSectionSchedule> courseSectionSchedules = (SetJoin<CourseSection, CourseSectionSchedule>) from.<CourseSection, CourseSectionSchedule>fetch("courseSectionSchedules", JoinType.LEFT);

		final SetJoin<CourseSection, StaffCourseSection> staffCourseSections = (SetJoin<CourseSection, StaffCourseSection>) from.<CourseSection, StaffCourseSection>fetch("staffCourseSections", JoinType.LEFT);
		final Join<StaffCourseSection, Staff> staff = (Join<StaffCourseSection, Staff>) staffCourseSections.<StaffCourseSection, Staff>fetch("staff", JoinType.INNER);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) staff.<Staff, StaffIdentifier>fetch("staffIdentifiers", JoinType.LEFT);

		final SetJoin<CourseSection, StudentCourseSection> studentCourseSections = (SetJoin<CourseSection, StudentCourseSection>) from.<CourseSection, StudentCourseSection>fetch("studentCourseSections", JoinType.LEFT);
		final Join<StudentEnrollment, Student> student = (Join<StudentEnrollment, Student>) studentCourseSections.<StudentEnrollment, Student>fetch("student", JoinType.INNER);
		final SetJoin<Student, StudentIdentifier> studentIdentifiers = (SetJoin<Student, StudentIdentifier>) student.<Student, StudentIdentifier>fetch("studentIdentifiers", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where( cb.equal(from.get(PRIMARY_KEY), refId));
		Query<CourseSection> q = getSession().createQuery(select);
		CourseSection instance = q.getSingleResult();

		Hibernate.initialize(instance.getSchoolCalendarSession());
		Hibernate.initialize(instance.getSchoolCalendarSession().getSchoolCalendar());
		Hibernate.initialize(instance.getCourseSectionSchedules());
		return instance;
	}

	@Override
	public void save(CourseSection instance) {
		super.persist(instance);
	}

	@Override
	public void update(CourseSection instance) {
		super.saveOrUpdate(instance);
	}

	@Override
	public void delete(CourseSection instance) {
		super.delete(instance);
	}

	@Override
	public void deleteByRefId(String refId)
	{
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(PRIMARY_KEY, refId));
		CourseSection instance = (CourseSection)criteria.uniqueResult();
		super.delete(instance);
	}
}