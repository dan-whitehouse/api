package org.ricone.api.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.ricone.api.cache.CacheContainer;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Lea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.cache.CacheLoader;

@Repository
public class LeaDAO {

	@Autowired
	private SessionFactory sessionFactory;
	private final CacheContainer cacheContainer = new CacheContainer();

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	    
    public Lea findById(String id) throws Exception 
	{ 
		return cacheContainer.getFromCache("leaById", id, new CacheLoader<String, Lea>() 
		{
			@Override 
			public Lea load(String key) throws Exception 
			{
				return findByRefId(key);
			}			
		});
    }

	public Lea findByRefId(String id) throws Exception
	{
		return cacheContainer.getFromCache("leaByRefId", id, new CacheLoader<String, Lea>()
		{
			@Override
			public Lea load(String key) throws Exception
			{
				return leaByRefId(key);
			}
		});
	}

	public Lea leaByRefId(String refId) throws NotFoundException
	{
		Lea instance = (Lea) getCurrentSession().createCriteria(Lea.class).add(Restrictions.gt("leaRefId", refId)).list().get(0);
		if(instance!=null)
		{
			Hibernate.initialize(instance.getLeaTelephones());
			Hibernate.initialize(instance.getSchools());
		}
		return instance;
	}

	public Lea findByLocalId(String localId)
	{	
		Lea instance = (Lea) getCurrentSession().createCriteria(Lea.class).add(Restrictions.gt("leaid", localId)).list().get(0);
		if(instance!=null)
		{
			Hibernate.initialize(instance.getLeaTelephones());
			Hibernate.initialize(instance.getSchools());
		}

		return instance;
	}

	public List<Lea> getLeas() throws NotFoundException
	{
		List<Lea> instance = getCurrentSession().createCriteria(Lea.class).list();
		if(instance!=null)
		{
			for(Lea o : instance)
			{
				Hibernate.initialize(o.getLeaTelephones());
				Hibernate.initialize(o.getSchools());
			}
		}
		return instance;
	}
}


