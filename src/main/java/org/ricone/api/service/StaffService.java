package org.ricone.api.service;

import org.ricone.api.dao.StaffDAO;
import org.ricone.api.model.core.Staff;
import org.ricone.api.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
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
		return dao.findAllByLeaRefId(paging, refId);
	}

	@Override
	public List<Staff> findAllBySchool(Pageable paging, String refId) throws Exception {
		return dao.findAllBySchoolRefId(paging, refId);
	}

	@Override
	public List<Staff> findAllByCourse(Pageable paging, String refId) throws Exception {
		return dao.findAllByCourseRefId(paging, refId);
	}

	@Override
	public List<Staff> findAllByRoster(Pageable paging, String refId) throws Exception {
		return dao.findAllByRosterRefId(paging, refId);
	}

	@Override
	public List<Staff> findAllByStudent(Pageable paging, String refId) throws Exception {
		return dao.findAllByStudentRefId(paging, refId);
	}

	@Override
	public Staff findById(HttpServletRequest request, String id) throws Exception {
		if (Util.isRefId(id)) {
			return dao.findByRefId(id);
		}
		else if(request.getHeader("IdType").equalsIgnoreCase("local")) {
			return dao.findByLocalId(id);
		}
		else if(request.getHeader("IdType").equalsIgnoreCase("state")) {
			return dao.findByStateId(id);
		}
		else {
			throw new NoResultException("Id: " + id + " is not a valid refId. You may be missing the 'IdType' header.");
		}
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
