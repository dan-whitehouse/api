package org.ricone.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.ricone.api.cache.CacheContainer;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.School;
import org.ricone.api.model.core.Staff;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("StaffDAO")
public class StaffDAO extends AbstractDAO<Integer, Staff> implements IStaffDAO
{
	private final String PRIMARY_KEY = "staffRefId";
	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<Staff> findAll() throws NotFoundException {

		Criteria criteria = createEntityCriteria();
		List<Staff> instance = (List<Staff>)criteria.list();
		if(instance!=null)
		{
			for(Staff o : instance)
			{
				Hibernate.initialize(o.getStaffAssignments());
				Hibernate.initialize(o.getStaffCourseSections());
				Hibernate.initialize(o.getStaffEmails());
				Hibernate.initialize(o.getStaffIdentifiers());
			}
		}
		return instance;
	}

	@Override
	public Staff findByRefId(String refId) throws NotFoundException
	{
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(PRIMARY_KEY, refId));
		Staff instance = (Staff)criteria.uniqueResult();
		if(instance!=null)
		{
			Hibernate.initialize(instance.getStaffAssignments());
			Hibernate.initialize(instance.getStaffCourseSections());
			Hibernate.initialize(instance.getStaffEmails());
			Hibernate.initialize(instance.getStaffIdentifiers());
		}
		return instance;
	}

	@Override
	public Staff findByLocalId(String localId) throws NotFoundException
	{
		//TODO: This isn't implemented
		return null;
	}

	@Override
	public void save(Staff instance) {
		persist(instance);
	}

	@Override
	public void update(Staff instance) {
		saveOrUpdate(instance);
	}

	@Override
	public void delete(Staff instance) {
		delete(instance);
	}

	@Override
	public void deleteByRefId(String refId)
	{
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(PRIMARY_KEY, refId));
		Staff instance = (Staff)criteria.uniqueResult();
		delete(instance);
	}
}
