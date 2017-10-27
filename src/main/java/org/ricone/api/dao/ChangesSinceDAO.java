package org.ricone.api.dao;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.query.Query;
import org.ricone.api.cache.CacheContainer;
import org.ricone.api.exception.NoContentException;
import org.ricone.api.model.core.EventLog;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Repository("changesSinceDAO")
public class ChangesSinceDAO extends AbstractDAO<Integer, EventLog> implements IChangesSinceDAO
{
	private final String OBJECT = "object";
	private final String EVENT_TIME_STAMP = "eventTimestamp";

	private final List<String> LEA_TABLES = Arrays.asList("Lea", "LeaTelephone");
	private final List<String> SCHOOL_TABLES = Arrays.asList("School", "SchoolTelephone");
	private final List<String> CALENDAR_TABLES = Arrays.asList("SchoolCalendar");
	private final List<String> COURSE_TABLES = Arrays.asList("Course");
	private final List<String> ROSTER_TABLES = Arrays.asList("CourseSection");
	private final List<String> STAFF_TABLES = Arrays.asList("Staff");
	private final List<String> STUDENT_TABLES = Arrays.asList("Student");
	private final List<String> CONTACT_TABLES = Arrays.asList("StudentContact");

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
					from.get(OBJECT).in(LEA_TABLES),
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
				from.get(OBJECT).in(SCHOOL_TABLES),
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
				from.get(OBJECT).in(CALENDAR_TABLES),
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
				from.get(OBJECT).in(COURSE_TABLES),
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
				from.get(OBJECT).in(ROSTER_TABLES),
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
				from.get(OBJECT).in(STAFF_TABLES),
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
				from.get(OBJECT).in(STUDENT_TABLES),
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
				from.get(OBJECT).in(CONTACT_TABLES),
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
