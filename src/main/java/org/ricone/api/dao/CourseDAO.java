package org.ricone.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.ricone.api.cache.CacheContainer;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Course;
import org.ricone.api.model.core.School;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("courseDAO")
public class CourseDAO extends AbstractDAO<Integer, Course> implements ICourseDAO
{
	private final String PRIMARY_KEY = "courseRefId";
	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<Course> findAll() throws NotFoundException {

		Criteria criteria = createEntityCriteria();
		List<Course> instance = (List<Course>)criteria.list();
		if(instance != null)
		{
			for(Course o : instance)
			{
				Hibernate.initialize(o.getSchool());
				Hibernate.initialize(o.getCourseIdentifiers());
				Hibernate.initialize(o.getCourseGrades());
			}
		}
		return instance;
	}

	@Override
	public Course findByRefId(String refId) throws NotFoundException
	{
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(PRIMARY_KEY, refId));
		Course instance = (Course)criteria.uniqueResult();
		if(instance != null)
		{
			Hibernate.initialize(instance.getSchool());
			Hibernate.initialize(instance.getCourseIdentifiers());
			Hibernate.initialize(instance.getCourseGrades());
			return instance;
		}
		throw new NotFoundException("No record found with refId: " + refId);
	}


	@Override
	public void save(Course instance) {
		persist(instance);
	}

	@Override
	public void update(Course instance) {
		saveOrUpdate(instance);
	}

	@Override
	public void delete(Course instance) {
		delete(instance);
	}

	@Override
	public void deleteByRefId(String refId)
	{
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(PRIMARY_KEY, refId));
		Course instance = (Course)criteria.uniqueResult();
		delete(instance);
	}
}


