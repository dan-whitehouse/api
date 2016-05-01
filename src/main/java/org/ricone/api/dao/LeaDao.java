package org.ricone.api.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.Lea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LeaDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<Lea> getLeas()
	{
		return getCurrentSession().createCriteria(Lea.class).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
	}
	
	public Lea findByRefId(String refId) throws NotFoundException
	{	
		Lea instance = (Lea) getCurrentSession().get(Lea.class, refId);
		
		if(instance == null)
		{
			throw new NotFoundException("Could not find Lea with RefId: " + refId);
		}
		return instance;
	}
	
	public Lea findByLocalId(String localId)
	{	
		Lea instance = (Lea) getCurrentSession().createCriteria(Lea.class).add(Restrictions.gt("leaid", localId)).list().get(0);
		return instance;
	}
	
	
}


