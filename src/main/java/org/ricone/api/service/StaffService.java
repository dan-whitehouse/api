package org.ricone.api.service;

import org.ricone.api.dao.StaffDAO;
import org.ricone.api.model.core.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
	public List<Staff> findAll(Pageable paging) throws Exception {
		return dao.findAll(paging);
	}

	@Override
	public List<Staff> findAllByLea(Pageable paging, String refId) throws Exception {
		return dao.findAllByLea(paging, refId);
	}

	@Override
	public List<Staff> findAllBySchool(Pageable paging, String refId) throws Exception {
		return dao.findAllBySchool(paging, refId);
	}

	@Override
	public List<Staff> findAllByCourse(Pageable paging, String refId) throws Exception {
		return dao.findAllByCourse(paging, refId);
	}

	@Override
	public List<Staff> findAllByRoster(Pageable paging, String refId) throws Exception {
		return dao.findAllByRoster(paging, refId);
	}

	@Override
	public List<Staff> findAllByStudent(Pageable paging, String refId) throws Exception {
		return dao.findAllByStudent(paging, refId);
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
