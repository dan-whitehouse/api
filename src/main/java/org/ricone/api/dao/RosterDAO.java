package org.ricone.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.ricone.api.cache.CacheContainer;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.*;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository("rosterDAO")
public class RosterDAO extends AbstractDAO<Integer, CourseSection> implements IRosterDAO
{
	private final String PRIMARY_KEY = "courseSectionRefId";
	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<CourseSection> findAll() throws NotFoundException
	{
		// #0
		/*CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
		Root<CourseSection> root = criteria.from( CourseSection.class );
		*//*Root<Course> courseRoot = criteria.from(Course.class);
		Root<SchoolCalendarSession> schoolCalendarSessionRoot = criteria.from(SchoolCalendarSession.class);
		Root<CourseSectionSchedule> courseSectionScheduleRoot = criteria.from(CourseSectionSchedule.class);
		Root<StaffCourseSection> staffCourseSectionRoot = criteria.from(StaffCourseSection.class);
		Root<StudentCourseSection> studentCourseSectionRoot = criteria.from(StudentCourseSection.class);*//*

			Join<CourseSection, SchoolCalendarSession> schoolCalendarSessionJoin = root.join("schoolCalendarSession", JoinType.LEFT);
			Join<CourseSection, Course> courseJoin = root.join("course", JoinType.LEFT);
				Join<Course, School> schoolJoin = courseJoin.join("school", JoinType.LEFT);
			Join<CourseSection, CourseSectionSchedule> courseSectionSchedulesJoin = root.join( "courseSectionSchedules" , JoinType.LEFT);
			Join<CourseSection, StaffCourseSection> staffCourseSectionsJoin = root.join( "staffCourseSections" , JoinType.LEFT);
			Join<CourseSection, StudentCourseSection> studentCourseSectionsJoin = root.join( "studentCourseSections" , JoinType.LEFT);

		criteria.distinct(true);
		criteria.multiselect(root, schoolCalendarSessionJoin, courseJoin, schoolJoin, courseSectionSchedulesJoin, staffCourseSectionsJoin, studentCourseSectionsJoin );
		//criteriaQuery.where(builder.equal(courseSectionRoot.get("course"), courseRoot.get("courseRefId")));
		//criteriaQuery.where(builder.equal(courseSectionRoot.get("schoolCalendarSession"), schoolCalendarSessionRoot.get("schoolRefId")));

		Query<Object[]> query = getSession().createQuery(criteria);
		List<Object[]> list = query.getResultList();

		List<CourseSection> instance = new ArrayList<>();
		for (Object[] objects : list)
		{
			System.out.println(objects[0].toString());
			CourseSection courseSection = (CourseSection)objects[0];
			Course course = (Course)objects[1];
			School school = (School)objects[2];
			//SchoolCalendarSession schoolCalendarSession = (SchoolCalendarSession)objects[3];

			//courseSection.setSchoolCalendarSession(schoolCalendarSession);

			course.setSchool(school);
			courseSection.setCourse(course);
			instance.add(courseSection);
		}
		return instance;*/

		// #1
		/*CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<CourseSection> criteria = builder.createQuery(CourseSection.class );
		Root<CourseSection> root = criteria.from( CourseSection.class );
			Join<CourseSection, SchoolCalendarSession> schoolCalendarSessionJoin = root.join("schoolCalendarSession", JoinType.LEFT);
			Join<CourseSection, Course> courseJoin = root.join("course", JoinType.LEFT);
				Join<Course, School> schoolJoin = courseJoin.join("school", JoinType.LEFT);
			Join<CourseSection, CourseSectionSchedule> courseSectionSchedulesJoin = root.join( "courseSectionSchedules" , JoinType.LEFT);
			Join<CourseSection, StaffCourseSection> staffCourseSectionsJoin = root.join( "staffCourseSections" , JoinType.LEFT);
			Join<CourseSection, StudentCourseSection> studentCourseSectionsJoin = root.join( "studentCourseSections" , JoinType.LEFT);

		criteria.distinct(true);
		//criteria.select(root);
		criteria.multiselect(root);
		List<CourseSection> instance = getSession().createQuery( criteria ).getResultList();
		for (CourseSection cs : instance)
		{
			cs.getStudentCourseSections();
			cs.getSchoolCalendarSession();
			cs.getStaffCourseSections();
			cs.getCourseSectionSchedules();
			cs.getCourse();
			cs.getCourse().getSchool();
		}
		return instance;*/

		// #2
		/*CriteriaBuilder builder = getSession().getCriteriaBuilder();
		// Using FROM and JOIN
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
		Root<CourseSection> courseSectionRoot = criteriaQuery.from(CourseSection.class);
		Root<Course> courseRoot = criteriaQuery.from(Course.class);
		Root<SchoolCalendarSession> schoolCalendarSessionRoot = criteriaQuery.from(SchoolCalendarSession.class);

		criteriaQuery.multiselect(courseSectionRoot, courseRoot, schoolCalendarSessionRoot);
		//criteriaQuery.where(builder.equal(courseSectionRoot.get("course"), courseRoot.get("courseRefId")));
		//criteriaQuery.where(builder.equal(courseSectionRoot.get("schoolCalendarSession"), schoolCalendarSessionRoot.get("schoolRefId")));

		Query<Object[]> query = getSession().createQuery(criteriaQuery);
		List<Object[]> list = query.getResultList();

		List<CourseSection> instance = new ArrayList<>();
		for (Object[] objects : list)
		{
			CourseSection courseSection = (CourseSection)objects[0];
			Course course = (Course)objects[1];
			SchoolCalendarSession schoolCalendarSession = (SchoolCalendarSession)objects[2];

			courseSection.setSchoolCalendarSession(schoolCalendarSession);
			courseSection.setCourse(course);
			instance.add(courseSection);
		}
		return instance;*/

		// #1
		Criteria criteria = createEntityCriteria();
		List<CourseSection> instance = (List<CourseSection>)criteria.list();
		if(instance != null)
		{
			for(CourseSection o : instance)
			{
				Hibernate.initialize(o.getCourse());
				Hibernate.initialize(o.getCourse().getSchool());
				Hibernate.initialize(o.getCourseSectionSchedules());
				Hibernate.initialize(o.getSchoolCalendarSession());
				Hibernate.initialize(o.getSchoolCalendarSession().getSchoolCalendar());

				Hibernate.initialize(o.getStaffCourseSections());
				for(StaffCourseSection staffCourseSection : o.getStaffCourseSections())
				{
					Hibernate.initialize(staffCourseSection.getStaff());
					for(StaffIdentifier id : staffCourseSection.getStaff().getStaffIdentifiers())
					{
						Hibernate.initialize(id);
					}
				}

				Hibernate.initialize(o.getStudentCourseSections());
				for(StudentCourseSection studentCourseSection : o.getStudentCourseSections())
				{
					Hibernate.initialize(studentCourseSection.getStudent());
					for(StudentIdentifier id : studentCourseSection.getStudent().getStudentIdentifiers())
					{
						Hibernate.initialize(id);
					}
				}
			}
		}
		return instance;
	}

	@Override
	public CourseSection findByRefId(String refId) throws Exception
	{
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(PRIMARY_KEY, refId));
		CourseSection instance = (CourseSection)criteria.uniqueResult();
		if(instance != null)
		{
			Hibernate.initialize(instance.getCourse());
			Hibernate.initialize(instance.getCourse().getSchool());
			Hibernate.initialize(instance.getCourseSectionSchedules());
			Hibernate.initialize(instance.getSchoolCalendarSession());
			Hibernate.initialize(instance.getSchoolCalendarSession().getSchoolCalendar());

			Hibernate.initialize(instance.getStaffCourseSections());
			for(StaffCourseSection staffCourseSection : instance.getStaffCourseSections())
			{
				Hibernate.initialize(staffCourseSection.getStaff());
				for(StaffIdentifier id : staffCourseSection.getStaff().getStaffIdentifiers())
				{
					Hibernate.initialize(id);
				}
			}

			Hibernate.initialize(instance.getStudentCourseSections());
			for(StudentCourseSection studentCourseSection : instance.getStudentCourseSections())
			{
				Hibernate.initialize(studentCourseSection.getStudent());
				for(StudentIdentifier id : studentCourseSection.getStudent().getStudentIdentifiers())
				{
					Hibernate.initialize(id);
				}
			}
		}
		return instance;
	}

	@Override
	public void save(CourseSection instance) {
		persist(instance);
	}

	@Override
	public void update(CourseSection instance) {
		saveOrUpdate(instance);
	}

	@Override
	public void delete(CourseSection instance) {
		delete(instance);
	}

	@Override
	public void deleteByRefId(String refId)
	{
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(PRIMARY_KEY, refId));
		CourseSection instance = (CourseSection)criteria.uniqueResult();
		delete(instance);
	}
}