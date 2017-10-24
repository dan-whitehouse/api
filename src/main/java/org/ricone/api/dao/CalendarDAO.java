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

@Repository("calendarDAO")
@SuppressWarnings("unchecked")
public class CalendarDAO extends AbstractDAO<Integer, SchoolCalendar> implements ICalendarDAO
{
	private final String PRIMARY_KEY = "schoolCalendarRefId";
	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<SchoolCalendar> findAll(Pageable pageRequest) throws Exception {

		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<SchoolCalendar> select = cb.createQuery(SchoolCalendar.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final Join<SchoolCalendar, School> school = (Join<SchoolCalendar, School>) from.<SchoolCalendar, School>fetch("school", JoinType.LEFT);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>fetch("schoolCalendarSessions", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<SchoolCalendar> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}
		List<SchoolCalendar> instance = q.getResultList();

		instance.forEach(sc -> {
			sc.getSchoolCalendarSessions().forEach(scs -> scs.getSchoolCalendar());
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<SchoolCalendar> findAllByLeaRefId(Pageable pageRequest, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<SchoolCalendar> select = cb.createQuery(SchoolCalendar.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>fetch("schoolCalendarSessions", JoinType.LEFT);
		final Join<SchoolCalendar, School> school = (Join<SchoolCalendar, School>) from.<SchoolCalendar, School>fetch("school", JoinType.INNER);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>fetch("lea", JoinType.INNER);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(lea.get("leaRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<SchoolCalendar> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}
		List<SchoolCalendar> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<SchoolCalendar> findAllBySchoolRefId(Pageable pageRequest, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<SchoolCalendar> select = cb.createQuery(SchoolCalendar.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>fetch("schoolCalendarSessions", JoinType.LEFT);
		final Join<SchoolCalendar, School> school = (Join<SchoolCalendar, School>) from.<SchoolCalendar, School>fetch("school", JoinType.INNER);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(school.get("schoolRefId"), refId));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<SchoolCalendar> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}
		List<SchoolCalendar> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public SchoolCalendar findByRefId(String refId) throws NotFoundException
	{
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<SchoolCalendar> select = cb.createQuery(SchoolCalendar.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final Join<SchoolCalendar, School> school = (Join<SchoolCalendar, School>) from.<SchoolCalendar, School>fetch("school", JoinType.LEFT);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>fetch("schoolCalendarSessions", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where( cb.equal(from.get(PRIMARY_KEY), refId));

		Query<SchoolCalendar> q = getSession().createQuery(select);
		return q.getSingleResult();
	}

	@Override
	public void save(SchoolCalendar instance) {
		super.persist(instance);
	}

	@Override
	public void update(SchoolCalendar instance) {
		super.saveOrUpdate(instance);
	}

	@Override
	public void delete(SchoolCalendar instance) {
		super.delete(instance);
	}

	@Override
	public void deleteByRefId(String refId)
	{
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(PRIMARY_KEY, refId));
		SchoolCalendar instance = (SchoolCalendar)criteria.uniqueResult();
		super.delete(instance);
	}
}


