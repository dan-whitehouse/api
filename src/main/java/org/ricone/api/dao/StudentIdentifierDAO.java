package org.ricone.api.dao;

import org.ricone.api.model.core.StudentIdentifier;
import org.springframework.stereotype.Repository;

@Repository("studentIdentifierDAO")
@SuppressWarnings("unchecked")
public class StudentIdentifierDAO extends AbstractDAO<Integer, StudentIdentifier> implements IStudentIdentifierDAO
{
	@Override
	public void save(StudentIdentifier instance) {
		super.persist(instance);
	}

	@Override
	public void update(StudentIdentifier instance){
		super.saveOrUpdate(instance);
	}

	@Override
	public void delete(StudentIdentifier instance){
		super.delete(instance);
	}
}
