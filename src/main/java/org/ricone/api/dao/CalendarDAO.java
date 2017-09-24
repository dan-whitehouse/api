package org.ricone.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.ricone.api.cache.CacheContainer;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.School;
import org.ricone.api.model.core.SchoolCalendar;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("calendarDAO")
public class CalendarDAO extends AbstractDAO<Integer, SchoolCalendar> implements ICalendarDAO
{
	private final String PRIMARY_KEY = "schoolCalendarRefId";
	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<SchoolCalendar> findAll() throws NotFoundException {

		Criteria criteria = createEntityCriteria();
		List<SchoolCalendar> instance = (List<SchoolCalendar>)criteria.list();
		if(instance != null)
		{
			for(SchoolCalendar o : instance)
			{
				Hibernate.initialize(o.getSchool());
				Hibernate.initialize(o.getSchoolCalendarSessions());
			}
		}
		return instance;
	}

	@Override
	public SchoolCalendar findByRefId(String refId) throws Exception
	{
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(PRIMARY_KEY, refId));
		SchoolCalendar instance = (SchoolCalendar)criteria.uniqueResult();
		if(instance != null)
		{
			Hibernate.initialize(instance.getSchool());
			Hibernate.initialize(instance.getSchoolCalendarSessions());
		}
		return instance;
	}

	@Override
	public void save(SchoolCalendar instance) {
		persist(instance);
	}

	@Override
	public void update(SchoolCalendar instance) {
		saveOrUpdate(instance);
	}

	@Override
	public void delete(SchoolCalendar instance) {
		delete(instance);
	}

	@Override
	public void deleteByRefId(String refId)
	{
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(PRIMARY_KEY, refId));
		SchoolCalendar instance = (SchoolCalendar)criteria.uniqueResult();
		delete(instance);
	}
}


