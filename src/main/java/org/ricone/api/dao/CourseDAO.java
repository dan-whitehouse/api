package org.ricone.api.dao;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.query.Query;
import org.ricone.api.cache.CacheContainer;
import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.exception.NoContentException;
import org.ricone.api.model.core.*;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Set;

@Repository("courseDAO")
public class CourseDAO extends AbstractDAO<Integer, Course> implements ICourseDAO
{
	private final String PRIMARY_KEY = "courseRefId";
	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<Course> findAll(MetaData metaData) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Course> select = cb.createQuery(Course.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join("school", JoinType.LEFT);
		final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>fetch("courseIdentifiers", JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>fetch("courseGrades", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where(lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds()));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Course> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<Course> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<Course> findAllByLeaRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Course> select = cb.createQuery(Course.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join("school", JoinType.LEFT);
		final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>fetch("courseIdentifiers", JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>fetch("courseGrades", JoinType.LEFT);

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

		Query<Course> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<Course> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<Course> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Course> select = cb.createQuery(Course.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join("school", JoinType.LEFT);
		final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>fetch("courseIdentifiers", JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>fetch("courseGrades", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(school.get("schoolRefId"), refId));
		select.where
		(
			cb.and
			(
				cb.equal(lea.get("leaRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Course> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<Course> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<Course> findAllByRosterRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Course> select = cb.createQuery(Course.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join("school", JoinType.LEFT);
		final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>fetch("courseIdentifiers", JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>fetch("courseGrades", JoinType.LEFT);
		final SetJoin<Course, CourseSection> courseSections = (SetJoin<Course, CourseSection>) from.<Course, CourseSection>join("courseSections", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				cb.equal(courseSections.get("courseSectionRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Course> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<Course> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<Course> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Course> select = cb.createQuery(Course.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join("school", JoinType.LEFT);
		final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>fetch("courseIdentifiers", JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>fetch("courseGrades", JoinType.LEFT);

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

		Query<Course> q = getSession().createQuery(select);
		List<Course> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public Course findByRefId(MetaData metaData, String refId) throws Exception
	{
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Course> select = cb.createQuery(Course.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join("school", JoinType.LEFT);
		final Join<School, Lea> lea = school.join("lea", JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>fetch("courseIdentifiers", JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>fetch("courseGrades", JoinType.LEFT);

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
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<Course> q = getSession().createQuery(select);
		return q.getSingleResult();
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
}


