package org.ricone.api.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.ricone.api.cache.AppCache;
import org.ricone.api.cache.CacheContainer;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Lea;
import org.ricone.api.model.core.LeaTelephone;
import org.ricone.api.model.core.School;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;

@Repository("leaDAO")
@SuppressWarnings("unchecked")
public class LeaDAO extends AbstractDAO<Integer, Lea> implements ILeaDAO
{
	private final String PRIMARY_KEY = "leaRefId";
	private final String LOCAL_ID_KEY = "leaId";

	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<Lea> findAll() throws NotFoundException
	{
		List<String> appLeaRefIds = new ArrayList<>();
		appLeaRefIds.add("1098EFC6-5374-4D7D-AEAE-58E021CCB146");

		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Lea> select = cb.createQuery(Lea.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>fetch("leaTelephones", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		//select.where(from.get(PRIMARY_KEY).in(appLeaRefIds));

		Query<Lea> q = getSession().createQuery(select);
		List<Lea> instance = q.getResultList();
		return instance;
	}

	@Override
	public Lea findByRefId(String refId) throws NotFoundException
	{
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Lea> select = cb.createQuery(Lea.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>fetch("leaTelephones", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where( cb.equal(from.get(PRIMARY_KEY), refId));

		Query<Lea> q = getSession().createQuery(select);
		Lea instance = q.getSingleResult();
		if (instance != null) {
			return instance;
		}
		throw new NotFoundException("No record found with refId: " + refId);
	}

	@Override
	public Lea findByLocalId(String localId) throws NotFoundException
	{
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<Lea> select = cb.createQuery(Lea.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>fetch("leaTelephones", JoinType.LEFT);

		select.distinct(true);
		select.select(from);
		select.where(cb.equal(from.get(LOCAL_ID_KEY), localId));

		Query<Lea> q = getSession().createQuery(select);
		Lea instance = q.getSingleResult();
		if (instance != null) {
			return instance;
		}
		throw new NotFoundException("No record found with localId: " + localId);
	}

	@Override
	public void save(Lea instance) {
		persist(instance);
	}

	@Override
	public void update(Lea instance) {
		saveOrUpdate(instance);
	}

	@Override
	public void delete(Lea instance) {
		delete(instance);
	}

	@Override
	public void deleteByRefId(String refId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(PRIMARY_KEY, refId));
		Lea instance = (Lea) criteria.uniqueResult();
		delete(instance);
	}
}
