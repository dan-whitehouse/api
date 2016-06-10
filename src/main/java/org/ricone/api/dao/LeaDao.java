package org.ricone.api.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.ricone.api.engine.QLea;
import org.ricone.api.engine.QLeatelephone;
import org.ricone.api.engine.converter.TLea;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.Lea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.hibernate.HibernateQuery;

@Repository
public class LeaDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public List<Lea> getLeas() throws NotFoundException
	{
		//return getCurrentSession().createCriteria(Lea.class).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
		
		HibernateQuery<?> query = new HibernateQuery<Void>(getCurrentSession());
		QLea qlea = QLea.lea;
		QLeatelephone qleatelephone = QLeatelephone.leatelephone;
		List<Tuple> result = query	.select(qlea.learefId, qlea.leaname, qlea.leaid, qleatelephone.leatelephoneRefId)
									.from(qlea)
									.leftJoin(qlea.leatelephones, qleatelephone)
									.groupBy(qlea.learefId)
									//.transform(GroupBy(qlea.learefId))
									.fetch();	
		
		List<Lea> instance = TLea.convertToMulti(result, qlea, qleatelephone);
		if(instance == null)
		{
			throw new NotFoundException("Could not find Leas");
		}
		return instance;
	}
	
	public Lea findByRefId(String refId) throws NotFoundException
	{	
		HibernateQuery<?> query = new HibernateQuery<Void>(getCurrentSession());
		QLea qlea = QLea.lea;
		QLeatelephone qleatelephone = QLeatelephone.leatelephone;
		List<Tuple> result = query	.select(qlea.learefId, qlea.leaname, qlea.leaid, qleatelephone.leatelephoneRefId)
									.from(qlea)
									.leftJoin(qlea.leatelephones, qleatelephone)
									.where(qlea.learefId.eq(refId))
									.fetch();	
		
		Lea instance = TLea.convertToSingle(result, qlea, qleatelephone);
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


