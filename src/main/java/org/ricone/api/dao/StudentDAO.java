package org.ricone.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.ricone.api.cache.CacheContainer;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("StudentDAO")
public class StudentDAO extends AbstractDAO<Integer, Student> implements IStudentDAO
{
	private final String PRIMARY_KEY = "studentRefId";
	private final CacheContainer cacheContainer = new CacheContainer();

	@Override
	public List<Student> findAll() throws NotFoundException {

		Criteria criteria = createEntityCriteria();
		List<Student> instance = (List<Student>)criteria.list();
		if(instance != null)
		{
			for(Student o : instance)
			{
				Hibernate.initialize(o.getStudentAcademicRecords());
				Hibernate.initialize(o.getStudentAddresses());
				Hibernate.initialize(o.getStudentEmails());
				Hibernate.initialize(o.getStudentIdentifiers());
				Hibernate.initialize(o.getStudentLanguages());
				Hibernate.initialize(o.getStudentOtherNames());
				Hibernate.initialize(o.getStudentRaces());
				Hibernate.initialize(o.getStudentTelephones());

				Hibernate.initialize(o.getStudentContactRelationships());
				o.getStudentContactRelationships().forEach(cr -> {
					Hibernate.initialize(cr.getStudentContact());
				});

				Hibernate.initialize(o.getStudentEnrollments());
				o.getStudentEnrollments().forEach(se ->
				{
					if(se.getCounselor() != null) {
						Hibernate.initialize(se.getCounselor());
						se.getCounselor().getStaffIdentifiers().forEach(Hibernate::initialize);
					}

					if(se.getTeacher() != null) {
						Hibernate.initialize(se.getTeacher());
						se.getTeacher().getStaffIdentifiers().forEach(Hibernate::initialize);
					}

					Hibernate.initialize(se.getEntryExitCodes());
					Hibernate.initialize(se.getSchool());
					//Hibernate.initialize(se.getSchool().getLea());
				});
			}
		}
		return instance;
	}

	@Override
	public Student findByRefId(String refId) throws NotFoundException
	{
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(PRIMARY_KEY, refId));
		Student instance = (Student)criteria.uniqueResult();
		if(instance != null)
		{
			Hibernate.initialize(instance.getStudentAcademicRecords());
			Hibernate.initialize(instance.getStudentAddresses());
			Hibernate.initialize(instance.getStudentEmails());
			Hibernate.initialize(instance.getStudentIdentifiers());
			Hibernate.initialize(instance.getStudentLanguages());
			Hibernate.initialize(instance.getStudentOtherNames());
			Hibernate.initialize(instance.getStudentRaces());
			Hibernate.initialize(instance.getStudentTelephones());

			Hibernate.initialize(instance.getStudentContactRelationships());
			instance.getStudentContactRelationships().forEach(cr -> {
				Hibernate.initialize(cr.getStudentContact());
			});

			Hibernate.initialize(instance.getStudentEnrollments());
			instance.getStudentEnrollments().forEach(se ->
			{
				if(se.getCounselor() != null) {
					Hibernate.initialize(se.getCounselor());
					se.getCounselor().getStaffIdentifiers().forEach(Hibernate::initialize);
				}

				if(se.getTeacher() != null) {
					Hibernate.initialize(se.getTeacher());
					se.getTeacher().getStaffIdentifiers().forEach(Hibernate::initialize);
				}

				Hibernate.initialize(se.getEntryExitCodes());
				Hibernate.initialize(se.getSchool());
				//Hibernate.initialize(se.getSchool().getLea());
			});
			return instance;
		}
		throw new NotFoundException("No record found with refId: " + refId);
	}

	@Override
	public Student findByLocalId(String localId) throws NotFoundException
	{
		//TODO: This isn't implemented
		return null;
	}

	@Override
	public void save(Student instance) {
		persist(instance);
	}

	@Override
	public void update(Student instance) {
		saveOrUpdate(instance);
	}

	@Override
	public void delete(Student instance) {
		delete(instance);
	}

	@Override
	public void deleteByRefId(String refId)
	{
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(PRIMARY_KEY, refId));
		Student instance = (Student)criteria.uniqueResult();
		delete(instance);
	}
}
