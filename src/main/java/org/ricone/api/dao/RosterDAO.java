package org.ricone.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.ricone.api.cache.CacheContainer;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("rosterDAO")
public class RosterDAO extends AbstractDAO<Integer, CourseSection> implements IRosterDAO
{
	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<CourseSection> findAll() throws NotFoundException {

		Criteria criteria = createEntityCriteria();
		List<CourseSection> instance = (List<CourseSection>)criteria.list();
		if(instance!=null)
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
		criteria.add(Restrictions.eq("courseSectionRefId", refId));
		CourseSection instance = (CourseSection)criteria.uniqueResult();
		if(instance!=null)
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
		criteria.add(Restrictions.eq("courseSectionRefId", refId));
		CourseSection instance = (CourseSection)criteria.uniqueResult();
		delete(instance);
	}
}