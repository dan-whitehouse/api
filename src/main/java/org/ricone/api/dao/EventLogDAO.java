package org.ricone.api.dao;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.query.Query;
import org.ricone.api.cache.CacheContainer;
import org.ricone.api.exception.NoContentException;
import org.ricone.api.model.core.EventLog;
import org.ricone.api.model.core.extension.event.EventObject;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

@Repository("changesSinceDAO")
public class EventLogDAO extends AbstractDAO<Integer, EventLog> implements IEventLogDAO
{
	private final String OBJECT = "object";
	private final String EVENT_TIME_STAMP = "eventTimestamp";

	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<EventLog> findAllByLea(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<EventLog> select = cb.createQuery(EventLog.class);
		final Root<EventLog> from = select.from(EventLog.class);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
					cb.equal(from.get(OBJECT), EventObject.LEA),
					cb.greaterThanOrEqualTo(from.get(EVENT_TIME_STAMP), iso8601)
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIME_STAMP)));

		Query<EventLog> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}
		List<EventLog> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<EventLog> findAllBySchool(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<EventLog> select = cb.createQuery(EventLog.class);
		final Root<EventLog> from = select.from(EventLog.class);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				cb.equal(from.get(OBJECT), EventObject.SCHOOL),
				cb.greaterThanOrEqualTo(from.get(EVENT_TIME_STAMP), iso8601)
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIME_STAMP)));

		Query<EventLog> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}
		List<EventLog> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<EventLog> findAllByCalendar(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<EventLog> select = cb.createQuery(EventLog.class);
		final Root<EventLog> from = select.from(EventLog.class);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				cb.equal(from.get(OBJECT), EventObject.CALENDAR),
				cb.greaterThanOrEqualTo(from.get(EVENT_TIME_STAMP), iso8601)
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIME_STAMP)));

		Query<EventLog> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}
		List<EventLog> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<EventLog> findAllByCourse(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<EventLog> select = cb.createQuery(EventLog.class);
		final Root<EventLog> from = select.from(EventLog.class);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				cb.equal(from.get(OBJECT), EventObject.COURSE),
				cb.greaterThanOrEqualTo(from.get(EVENT_TIME_STAMP), iso8601)
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIME_STAMP)));

		Query<EventLog> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}
		List<EventLog> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}



	@Override
	public List<EventLog> findAllByRoster(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<EventLog> select = cb.createQuery(EventLog.class);
		final Root<EventLog> from = select.from(EventLog.class);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				cb.equal(from.get(OBJECT), EventObject.ROSTER),
				cb.greaterThanOrEqualTo(from.get(EVENT_TIME_STAMP), iso8601)
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIME_STAMP)));

		Query<EventLog> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}
		List<EventLog> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<EventLog> findAllByStaff(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<EventLog> select = cb.createQuery(EventLog.class);
		final Root<EventLog> from = select.from(EventLog.class);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				cb.equal(from.get(OBJECT), EventObject.STAFF),
				cb.greaterThanOrEqualTo(from.get(EVENT_TIME_STAMP), iso8601)
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIME_STAMP)));

		Query<EventLog> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}
		List<EventLog> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<EventLog> findAllByStudent(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<EventLog> select = cb.createQuery(EventLog.class);
		final Root<EventLog> from = select.from(EventLog.class);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				cb.equal(from.get(OBJECT), EventObject.STUDENT),
				cb.greaterThanOrEqualTo(from.get(EVENT_TIME_STAMP), iso8601)
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIME_STAMP)));

		Query<EventLog> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}
		List<EventLog> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<EventLog> findAllByContact(Pageable pageRequest, LocalDateTime iso8601) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<EventLog> select = cb.createQuery(EventLog.class);
		final Root<EventLog> from = select.from(EventLog.class);

		select.distinct(true);
		select.select(from);
		select.where
		(
			cb.and
			(
				cb.equal(from.get(OBJECT), EventObject.CONTACT),
				cb.greaterThanOrEqualTo(from.get(EVENT_TIME_STAMP), iso8601)
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIME_STAMP)));

		Query<EventLog> q = getSession().createQuery(select);
		if(pageRequest.isPaged()){
			q.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
			q.setMaxResults(pageRequest.getPageSize());
		}
		List<EventLog> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}
}
