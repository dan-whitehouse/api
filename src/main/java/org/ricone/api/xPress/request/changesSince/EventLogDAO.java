package org.ricone.api.xPress.request.changesSince;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.query.Query;
import org.ricone.api.AbstractDAO;
import org.ricone.api.core.model.EventLog;
import org.ricone.api.core.model.EventObject;
import org.ricone.api.core.model.Lea;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.authentication.MetaData;
import org.ricone.error.exception.NoContentException;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;

@Repository("changesSinceDAO")
@SuppressWarnings("unchecked")
public class EventLogDAO extends AbstractDAO<Integer, EventLog> implements IEventLogDAO
{
	private final String OBJECT = "object";
	private final String EVENT_TIME_STAMP = "eventTimestamp";

	@Override
	public List<EventLogWrapper> findAllByLea(MetaData metaData, LocalDateTime iso8601) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<EventLog> from = select.from(EventLog.class);
		final Join<EventLog, Lea> lea = (Join<EventLog, Lea>) from.<EventLog, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(from.get(OBJECT), EventObject.LEA),
				cb.greaterThanOrEqualTo(from.get(EVENT_TIME_STAMP), iso8601),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIME_STAMP)));

		Query<EventLogWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<EventLogWrapper> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<EventLogWrapper> findAllBySchool(MetaData metaData, LocalDateTime iso8601) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<EventLog> from = select.from(EventLog.class);
		final Join<EventLog, Lea> lea = (Join<EventLog, Lea>) from.<EventLog, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(from.get(OBJECT), EventObject.SCHOOL),
				cb.greaterThanOrEqualTo(from.get(EVENT_TIME_STAMP), iso8601),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIME_STAMP)));

		Query<EventLogWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<EventLogWrapper> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<EventLogWrapper> findAllByCalendar(MetaData metaData, LocalDateTime iso8601) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<EventLog> from = select.from(EventLog.class);
		final Join<EventLog, Lea> lea = (Join<EventLog, Lea>) from.<EventLog, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(from.get(OBJECT), EventObject.CALENDAR),
				cb.greaterThanOrEqualTo(from.get(EVENT_TIME_STAMP), iso8601),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIME_STAMP)));

		Query<EventLogWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<EventLogWrapper> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<EventLogWrapper> findAllByCourse(MetaData metaData, LocalDateTime iso8601) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<EventLog> from = select.from(EventLog.class);
		final Join<EventLog, Lea> lea = (Join<EventLog, Lea>) from.<EventLog, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(from.get(OBJECT), EventObject.COURSE),
				cb.greaterThanOrEqualTo(from.get(EVENT_TIME_STAMP), iso8601),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIME_STAMP)));

		Query<EventLogWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<EventLogWrapper> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}



	@Override
	public List<EventLogWrapper> findAllByRoster(MetaData metaData, LocalDateTime iso8601) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<EventLog> from = select.from(EventLog.class);
		final Join<EventLog, Lea> lea = (Join<EventLog, Lea>) from.<EventLog, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(from.get(OBJECT), EventObject.ROSTER),
				cb.greaterThanOrEqualTo(from.get(EVENT_TIME_STAMP), iso8601),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIME_STAMP)));

		Query<EventLogWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<EventLogWrapper> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<EventLogWrapper> findAllByStaff(MetaData metaData, LocalDateTime iso8601) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<EventLog> from = select.from(EventLog.class);
		final Join<EventLog, Lea> lea = (Join<EventLog, Lea>) from.<EventLog, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(from.get(OBJECT), EventObject.STAFF),
				cb.greaterThanOrEqualTo(from.get(EVENT_TIME_STAMP), iso8601),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIME_STAMP)));

		Query<EventLogWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<EventLogWrapper> instance = q.getResultList();
		
		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<EventLogWrapper> findAllByStudent(MetaData metaData, LocalDateTime iso8601) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<EventLog> from = select.from(EventLog.class);
		final Join<EventLog, Lea> lea = (Join<EventLog, Lea>) from.<EventLog, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(from.get(OBJECT), EventObject.STUDENT),
				cb.greaterThanOrEqualTo(from.get(EVENT_TIME_STAMP), iso8601),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIME_STAMP)));

		Query<EventLogWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<EventLogWrapper> instance = q.getResultList();
		
		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<EventLogWrapper> findAllByContact(MetaData metaData, LocalDateTime iso8601) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<EventLog> from = select.from(EventLog.class);
		final Join<EventLog, Lea> lea = (Join<EventLog, Lea>) from.<EventLog, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(from.get(OBJECT), EventObject.CONTACT),
				cb.greaterThanOrEqualTo(from.get(EVENT_TIME_STAMP), iso8601),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIME_STAMP)));

		Query<EventLogWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<EventLogWrapper> instance = q.getResultList();

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}
}
