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
import javax.persistence.metamodel.MapAttribute;
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
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<CourseSection> select = cb.createQuery(CourseSection.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = from.join("course", JoinType.LEFT);
		final Join<Course, School> school = course.join("school", JoinType.LEFT);

		final Join<CourseSection, SchoolCalendarSession> schoolCalendarSession = from.join("schoolCalendarSession", JoinType.LEFT);
		final Join<SchoolCalendarSession, SchoolCalendar> schoolCalendar = schoolCalendarSession.join("schoolCalendar", JoinType.LEFT);
		final SetJoin<CourseSection, CourseSectionSchedule> courseSectionSchedules = (SetJoin<CourseSection, CourseSectionSchedule>) from.<CourseSection, CourseSectionSchedule>join("courseSectionSchedules", JoinType.LEFT);

		final SetJoin<CourseSection, StaffCourseSection> staffCourseSections = (SetJoin<CourseSection, StaffCourseSection>) from.<CourseSection, StaffCourseSection>join("staffCourseSections", JoinType.LEFT);
		final Join<StaffCourseSection, Staff> staff = staffCourseSections.join("staff", JoinType.LEFT);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) staff.<Staff, StaffIdentifier>join("staffIdentifiers", JoinType.LEFT);

		final SetJoin<CourseSection, StudentCourseSection> studentCourseSections = (SetJoin<CourseSection, StudentCourseSection>) from.<CourseSection, StudentCourseSection>join("studentCourseSections", JoinType.LEFT);
		final Join<StudentEnrollment, Student> student = studentCourseSections.join("student", JoinType.LEFT);
		final SetJoin<Student, StudentIdentifier> studentIdentifiers = (SetJoin<Student, StudentIdentifier>) student.<Student, StudentIdentifier>join("studentIdentifiers", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<CourseSection> q = getSession().createQuery(select);
		//TODO - Implement this isPaged check on all other DAO methods for each class
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}
		List<CourseSection> instance = q.getResultList();

		instance.forEach(sc -> {
			Hibernate.initialize(sc.getCourse());
			Hibernate.initialize(sc.getCourse().getSchool());
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

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public CourseSection findByRefId(String refId) throws Exception
	{
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<CourseSection> select = cb.createQuery(CourseSection.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = from.join("course", JoinType.LEFT);
		final Join<Course, School> school = course.join("school", JoinType.LEFT);

		final Join<CourseSection, SchoolCalendarSession> schoolCalendarSession = from.join("schoolCalendarSession", JoinType.LEFT);
		final Join<SchoolCalendarSession, SchoolCalendar> schoolCalendar = schoolCalendarSession.join("schoolCalendar", JoinType.LEFT);
		final SetJoin<CourseSection, CourseSectionSchedule> courseSectionSchedules = (SetJoin<CourseSection, CourseSectionSchedule>) from.<CourseSection, CourseSectionSchedule>join("courseSectionSchedules", JoinType.LEFT);

		final SetJoin<CourseSection, StaffCourseSection> staffCourseSections = (SetJoin<CourseSection, StaffCourseSection>) from.<CourseSection, StaffCourseSection>join("staffCourseSections", JoinType.LEFT);
		final Join<StaffCourseSection, Staff> staff = staffCourseSections.join("staff", JoinType.LEFT);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) staff.<Staff, StaffIdentifier>join("staffIdentifiers", JoinType.LEFT);

		final SetJoin<CourseSection, StudentCourseSection> studentCourseSections = (SetJoin<CourseSection, StudentCourseSection>) from.<CourseSection, StudentCourseSection>join("studentCourseSections", JoinType.LEFT);
		final Join<StudentEnrollment, Student> student = studentCourseSections.join("student", JoinType.LEFT);
		final SetJoin<Student, StudentIdentifier> studentIdentifiers = (SetJoin<Student, StudentIdentifier>) student.<Student, StudentIdentifier>join("studentIdentifiers", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where( cb.equal(from.get(PRIMARY_KEY), refId));
		Query<CourseSection> q = getSession().createQuery(select);
		CourseSection instance = q.getSingleResult();

		Hibernate.initialize(instance.getCourse());
		Hibernate.initialize(instance.getCourse().getSchool());
		Hibernate.initialize(instance.getSchoolCalendarSession());
		Hibernate.initialize(instance.getSchoolCalendarSession().getSchoolCalendar());
		Hibernate.initialize(instance.getCourseSectionSchedules());
		Hibernate.initialize(instance.getStaffCourseSections());
		instance.getStaffCourseSections().forEach(tcs -> {
			Hibernate.initialize(tcs.getStaff());
			Hibernate.initialize(tcs.getStaff().getStaffIdentifiers());
		});
		Hibernate.initialize(instance.getStudentCourseSections());
		instance.getStudentCourseSections().forEach(scs -> {
			Hibernate.initialize(scs.getStudent());
			Hibernate.initialize(scs.getStudent().getStudentIdentifiers());
		});

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