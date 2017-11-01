package org.ricone.api.dao;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.query.Query;
import org.ricone.api.cache.CacheContainer;
import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.exception.NoContentException;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Lea;
import org.ricone.api.model.core.School;
import org.ricone.api.model.core.SchoolCalendar;
import org.ricone.api.model.core.SchoolCalendarSession;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Set;

@Repository("calendarDAO")
@SuppressWarnings("unchecked")
public class CalendarDAO extends AbstractDAO<Integer, SchoolCalendar> implements ICalendarDAO
{
	private final String PRIMARY_KEY = "schoolCalendarRefId";
	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<SchoolCalendar> findAll(MetaData metaData) throws Exception {

		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<SchoolCalendar> select = cb.createQuery(SchoolCalendar.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final Join<SchoolCalendar, School> school = (Join<SchoolCalendar, School>) from.<SchoolCalendar, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>fetch("lea", JoinType.LEFT);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>fetch("schoolCalendarSessions", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where(lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds()));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<SchoolCalendar> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<SchoolCalendar> instance = q.getResultList();

		instance.forEach(sc -> {
			sc.getSchoolCalendarSessions().forEach(scs -> scs.getSchoolCalendar());
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<SchoolCalendar> findAllByLeaRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<SchoolCalendar> select = cb.createQuery(SchoolCalendar.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>fetch("schoolCalendarSessions", JoinType.LEFT);
		final Join<SchoolCalendar, School> school = (Join<SchoolCalendar, School>) from.<SchoolCalendar, School>fetch("school", JoinType.LEFT);
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

		Query<SchoolCalendar> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<SchoolCalendar> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<SchoolCalendar> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<SchoolCalendar> select = cb.createQuery(SchoolCalendar.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>fetch("schoolCalendarSessions", JoinType.LEFT);
		final Join<SchoolCalendar, School> school = (Join<SchoolCalendar, School>) from.<SchoolCalendar, School>fetch("school", JoinType.LEFT);
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

		Query<SchoolCalendar> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<SchoolCalendar> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<SchoolCalendar> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<SchoolCalendar> select = cb.createQuery(SchoolCalendar.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final Join<SchoolCalendar, School> school = (Join<SchoolCalendar, School>) from.<SchoolCalendar, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>fetch("lea", JoinType.LEFT);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>fetch("schoolCalendarSessions", JoinType.LEFT);

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

		Query<SchoolCalendar> q = getSession().createQuery(select);
		List<SchoolCalendar> instance = q.getResultList();

		instance.forEach(sc -> {
			sc.getSchoolCalendarSessions().forEach(scs -> scs.getSchoolCalendar());
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public SchoolCalendar findByRefId(MetaData metaData, String refId) throws NotFoundException
	{
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<SchoolCalendar> select = cb.createQuery(SchoolCalendar.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final Join<SchoolCalendar, School> school = (Join<SchoolCalendar, School>) from.<SchoolCalendar, School>fetch("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>fetch("lea", JoinType.LEFT);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>fetch("schoolCalendarSessions", JoinType.LEFT);

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
}


