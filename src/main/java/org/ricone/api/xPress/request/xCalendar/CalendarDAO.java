package org.ricone.api.xPress.request.xCalendar;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.ricone.api.AbstractDAO;
import org.ricone.api.core.model.Lea;
import org.ricone.api.core.model.School;
import org.ricone.api.core.model.SchoolCalendar;
import org.ricone.api.core.model.SchoolCalendarSession;
import org.ricone.api.core.model.wrapper.SchoolCalendarWrapper;
import org.ricone.authentication.MetaData;
import org.ricone.error.exception.NoContentException;
import org.ricone.error.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Set;

@Repository("calendarDAO")
@SuppressWarnings("unchecked")
public class CalendarDAO extends AbstractDAO<Integer, SchoolCalendar> implements ICalendarDAO
{
	private final String PRIMARY_KEY = "schoolCalendarRefId";

	@Override
	public List<SchoolCalendarWrapper> findAll(MetaData metaData) throws Exception {

		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<SchoolCalendarWrapper> select = cb.createQuery(SchoolCalendarWrapper.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final Join<SchoolCalendar, School> school = (Join<SchoolCalendar, School>) from.<SchoolCalendar, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>join("lea", JoinType.LEFT);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>join("schoolCalendarSessions", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(SchoolCalendarWrapper.class, lea.get("leaId"), from));
		select.where(lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds()));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<SchoolCalendarWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}
		List<SchoolCalendarWrapper> instance = q.getResultList();
		instance.forEach(wrapper -> {
			wrapper.getSchoolCalendar().getSchoolCalendarSessions().forEach(scs -> Hibernate.initialize(scs.getSchoolCalendar()));
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<SchoolCalendarWrapper> findAllByLeaRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<SchoolCalendarWrapper> select = cb.createQuery(SchoolCalendarWrapper.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>join("schoolCalendarSessions", JoinType.LEFT);
		final Join<SchoolCalendar, School> school = (Join<SchoolCalendar, School>) from.<SchoolCalendar, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(SchoolCalendarWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(lea.get("leaRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<SchoolCalendarWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<SchoolCalendarWrapper> instance = q.getResultList();
		instance.forEach(wrapper -> {
			wrapper.getSchoolCalendar().getSchoolCalendarSessions().forEach(scs -> Hibernate.initialize(scs.getSchoolCalendar()));
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<SchoolCalendarWrapper> findAllBySchoolRefId(MetaData metaData, String refId) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<SchoolCalendarWrapper> select = cb.createQuery(SchoolCalendarWrapper.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>join("schoolCalendarSessions", JoinType.LEFT);
		final Join<SchoolCalendar, School> school = (Join<SchoolCalendar, School>) from.<SchoolCalendar, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>join("lea", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(SchoolCalendarWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(school.get("schoolRefId"), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query<SchoolCalendarWrapper> q = getSession().createQuery(select);
		if(metaData.getPaging().isPaged()){
			q.setFirstResult(metaData.getPaging().getPageNumber() * metaData.getPaging().getPageSize());
			q.setMaxResults(metaData.getPaging().getPageSize());
		}

		List<SchoolCalendarWrapper> instance = q.getResultList();
		instance.forEach(wrapper -> {
			wrapper.getSchoolCalendar().getSchoolCalendarSessions().forEach(scs -> Hibernate.initialize(scs.getSchoolCalendar()));
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public List<SchoolCalendarWrapper> findByRefIds(MetaData metaData, Set<String> refIds) throws Exception {
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<SchoolCalendarWrapper> select = cb.createQuery(SchoolCalendarWrapper.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final Join<SchoolCalendar, School> school = (Join<SchoolCalendar, School>) from.<SchoolCalendar, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>join("lea", JoinType.LEFT);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>join("schoolCalendarSessions", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(SchoolCalendarWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				from.get(PRIMARY_KEY).in(refIds),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

		Query<SchoolCalendarWrapper> q = getSession().createQuery(select);

		List<SchoolCalendarWrapper> instance = q.getResultList();
		instance.forEach(wrapper -> {
			wrapper.getSchoolCalendar().getSchoolCalendarSessions().forEach(scs -> Hibernate.initialize(scs.getSchoolCalendar()));
		});

		if(CollectionUtils.isEmpty(instance)) throw new NoContentException();
		return instance;
	}

	@Override
	public SchoolCalendarWrapper findByRefId(MetaData metaData, String refId) throws NotFoundException
	{
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<SchoolCalendarWrapper> select = cb.createQuery(SchoolCalendarWrapper.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final Join<SchoolCalendar, School> school = (Join<SchoolCalendar, School>) from.<SchoolCalendar, School>join("school", JoinType.LEFT);
		final Join<School, Lea> lea = (Join<School, Lea>) school.<School, Lea>join("lea", JoinType.LEFT);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>join("schoolCalendarSessions", JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(SchoolCalendarWrapper.class, lea.get("leaId"), from));
		select.where
		(
			cb.and
			(
				cb.equal(from.get(PRIMARY_KEY), refId),
				lea.get(MetaData.LEA_LOCAL_ID_KEY).in(metaData.getApp().getDistrictLocalIds())
			)
		);

		Query<SchoolCalendarWrapper> q = getSession().createQuery(select);

		SchoolCalendarWrapper instance = q.getSingleResult();
		instance.getSchoolCalendar().getSchoolCalendarSessions().forEach(scs -> Hibernate.initialize(scs.getSchoolCalendar()));
		return instance;
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


