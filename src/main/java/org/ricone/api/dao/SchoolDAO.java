package org.ricone.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.ricone.api.cache.CacheContainer;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Lea;
import org.ricone.api.model.core.School;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("schoolDAO")
public class SchoolDAO extends AbstractDAO<Integer, School> implements ISchoolDAO
{
	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<School> findAll() throws NotFoundException {

		Criteria crit = createEntityCriteria();
		List<School> instance = (List<School>)crit.list();
		if(instance!=null)
		{
			for(School o : instance)
			{
				Hibernate.initialize(o.getLea());
				Hibernate.initialize(o.getSchoolIdentifiers());
				Hibernate.initialize(o.getSchoolCalendars());
				Hibernate.initialize(o.getSchoolGrades());
				Hibernate.initialize(o.getSchoolTelephones());
			}
		}
		return instance;
	}

	@Override
	public School findByRefId(String refId) throws Exception
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("schoolRefId", refId));
		School instance = (School)crit.uniqueResult();
		if(instance!=null)
		{
			Hibernate.initialize(instance.getLea());
			Hibernate.initialize(instance.getSchoolIdentifiers());
			Hibernate.initialize(instance.getSchoolCalendars());
			Hibernate.initialize(instance.getSchoolGrades());
			Hibernate.initialize(instance.getSchoolTelephones());
		}
		return instance;
	}

	@Override
	public School findByLocalId(String localId) throws Exception
	{
		//TODO: This isn't implemented correctly
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("leaId", localId));
		School instance = (School)crit.uniqueResult();
		if(instance!=null)
		{
			Hibernate.initialize(instance.getLea());
			Hibernate.initialize(instance.getSchoolIdentifiers());
			Hibernate.initialize(instance.getSchoolCalendars());
			Hibernate.initialize(instance.getSchoolGrades());
			Hibernate.initialize(instance.getSchoolTelephones());
		}
		return instance;
	}

	@Override
	public void save(School instance) {
		persist(instance);
	}

	@Override
	public void update(School instance) {
		saveOrUpdate(instance);
	}

	@Override
	public void delete(School instance) {
		delete(instance);
	}

	@Override
	public void deleteByRefId(String refId)
	{
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("leaRefId", refId));
		School instance = (School)crit.uniqueResult();
		delete(instance);
	}
}


