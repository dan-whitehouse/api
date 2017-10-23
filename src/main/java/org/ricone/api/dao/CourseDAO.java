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

@Repository("courseDAO")
public class CourseDAO extends AbstractDAO<Integer, Course> implements ICourseDAO
{
	private final String PRIMARY_KEY = "courseRefId";
	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<Course> findAll(Pageable pageRequest) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Course> select = cb.createQuery(Course.class);
		final Root<Course> from = select.from(Course.class);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>fetch("courseIdentifiers", JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>fetch("courseGrades", JoinType.LEFT);


		select.distinct(true);
		select.select(from);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Course> q = getSession().createQuery(select);
		q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
		q.setMaxResults(pageRequest.getPageSize());
		List<Course> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
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
		super.persist(instance);
	}

	@Override
	public void update(Course instance) {
		super.saveOrUpdate(instance);
	}

	@Override
	public void delete(Course instance) {
		super.delete(instance);
	}

	@Override
	public void deleteByRefId(String refId)
	{
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(PRIMARY_KEY, refId));
		Course instance = (Course)criteria.uniqueResult();
		super.delete(instance);
	}
}


