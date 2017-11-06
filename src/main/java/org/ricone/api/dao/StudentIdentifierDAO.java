package org.ricone.api.dao;

import org.ricone.api.model.core.StudentIdentifier;
import org.springframework.stereotype.Repository;

@Repository("studentIdentifierDAO")
@SuppressWarnings("unchecked")
public class StudentIdentifierDAO extends AbstractDAO<Integer, StudentIdentifier> implements IStudentIdentifierDAO
{
	private final String IDENTIFICATION_SYSTEM_CODE = "identificationSystemCode";
	private final String LOGIN_ID = "LoginId";

	@Override
	public int countLoginIdsBySchoolRefId(String refId, String staffRefId, String logInId) throws Exception {
		return 0;
	}

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
