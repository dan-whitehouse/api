package org.ricone.api.dao;

import org.ricone.api.model.core.StaffIdentifier;
import org.springframework.stereotype.Repository;

@Repository("staffIdentifierDAO")
@SuppressWarnings("unchecked")
public class StaffIdentifierDAO extends AbstractDAO<Integer, StaffIdentifier> implements IStaffIdentifierDAO
{
	@Override
	public void save(StaffIdentifier instance) {
		super.persist(instance);
	}

	@Override
	public void update(StaffIdentifier instance){
		super.saveOrUpdate(instance);
	}

	@Override
	public void delete(StaffIdentifier instance){
		super.delete(instance);
	}
}
