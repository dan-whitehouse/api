package org.ricone.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.ricone.api.cache.CacheContainer;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Lea;
import org.ricone.api.model.core.School;
import org.springframework.stereotype.Repository;

@Repository("leaDAO")
public class LeaDAO extends AbstractDAO<Integer, Lea> implements ILeaDAO {
	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<Lea> findAll() throws NotFoundException {

		Criteria crit = createEntityCriteria();
		List<Lea> instance = (List<Lea>) crit.list();
		if (instance != null) {
			for (Lea o : instance) {
				Hibernate.initialize(o.getLeaTelephones());
				Hibernate.initialize(o.getSchools());
			}
		}
		return instance;
	}

	@Override
	public Lea findByRefId(String refId) throws Exception {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("leaRefId", refId));
		Lea instance = (Lea) crit.uniqueResult();
		if (instance != null) {
			Hibernate.initialize(instance.getLeaTelephones());
			Hibernate.initialize(instance.getSchools());
		}
		return instance;
	}

	@Override
	public Lea findByLocalId(String localId) throws Exception {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("leaId", localId));
		Lea instance = (Lea) crit.uniqueResult();
		if (instance != null) {
			Hibernate.initialize(instance.getLeaTelephones());
			Hibernate.initialize(instance.getSchools());
		}
		return instance;
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
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("leaRefId", refId));
		Lea instance = (Lea) crit.uniqueResult();
		delete(instance);
	}
}
