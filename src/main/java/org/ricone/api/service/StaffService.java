package org.ricone.api.service;

import org.ricone.api.dao.StaffDAO;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("StaffService")
@Transactional
public class StaffService implements IStaffService
{
	@Autowired
	StaffDAO dao;

	@Override
	public List<Staff> findAll() throws Exception {
		return dao.findAll();
	}

	@Override
	public Staff findByRefId(String refId) throws Exception {
		return dao.findByRefId(refId);
	}

	@Override
	public Staff findByLocalId(String localId) throws Exception {
		return dao.findByLocalId(localId);
	}

	@Override
	public void save(Staff instance) {
		dao.save(instance);
	}

	@Override
	public void update(Staff instance) {
		dao.update(instance);
	}

	@Override
	public void delete(Staff instance) {
		dao.delete(instance);
	}

	@Override
	public void deleteByRefId(String refId) {
		dao.deleteByRefId(refId);
	}
}
