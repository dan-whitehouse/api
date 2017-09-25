package org.ricone.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.ricone.api.cache.CacheContainer;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.StudentContact;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ContactDAO")
public class ContactDAO extends AbstractDAO<Integer, StudentContact> implements IContactDAO
{
	private final String PRIMARY_KEY = "studentContactRefId";
	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<StudentContact> findAll() throws NotFoundException {

		Criteria criteria = createEntityCriteria();
		List<StudentContact> instance = (List<StudentContact>)criteria.list();
		if(instance != null)
		{
			for(StudentContact o : instance)
			{
				Hibernate.initialize(o.getStudentContactAddresses());
				Hibernate.initialize(o.getStudentContactEmails());
				Hibernate.initialize(o.getStudentContactIdentifiers());
				Hibernate.initialize(o.getStudentContactOtherNames());
				Hibernate.initialize(o.getStudentContactTelephones());

				Hibernate.initialize(o.getStudentContactRelationships());
				o.getStudentContactRelationships().forEach(cr -> Hibernate.initialize(cr.getStudent()));
			}
		}
		return instance;
	}

	@Override
	public StudentContact findByRefId(String refId) throws NotFoundException
	{
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(PRIMARY_KEY, refId));
		StudentContact instance = (StudentContact)criteria.uniqueResult();
		if(instance != null)
		{
			Hibernate.initialize(instance.getStudentContactAddresses());
			Hibernate.initialize(instance.getStudentContactEmails());
			Hibernate.initialize(instance.getStudentContactIdentifiers());
			Hibernate.initialize(instance.getStudentContactOtherNames());
			Hibernate.initialize(instance.getStudentContactTelephones());

			Hibernate.initialize(instance.getStudentContactRelationships());
			instance.getStudentContactRelationships().forEach(cr -> Hibernate.initialize(cr.getStudent()));
			return instance;
		}
		throw new NotFoundException("No record found with refId: " + refId);
	}


	@Override
	public void save(StudentContact instance) {
		persist(instance);
	}

	@Override
	public void update(StudentContact instance) {
		saveOrUpdate(instance);
	}

	@Override
	public void delete(StudentContact instance) {
		delete(instance);
	}

	@Override
	public void deleteByRefId(String refId)
	{
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(PRIMARY_KEY, refId));
		StudentContact instance = (StudentContact)criteria.uniqueResult();
		delete(instance);
	}
}
