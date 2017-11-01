package org.ricone.api.service;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.dao.StaffDAO;
import org.ricone.api.model.core.Staff;
import org.ricone.api.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@Service("StaffService")
@Transactional
public class StaffService implements IStaffService
{
	@Autowired
	StaffDAO dao;

	@Override
	public List<Staff> findAll(MetaData metaData) throws Exception {
		return dao.findAll(metaData);
	}

	@Override
	public List<Staff> findAllByLea(MetaData metaData, String refId) throws Exception {
		return dao.findAllByLeaRefId(metaData, refId);
	}

	@Override
	public List<Staff> findAllBySchool(MetaData metaData, String refId) throws Exception {
		return dao.findAllBySchoolRefId(metaData, refId);
	}

	@Override
	public List<Staff> findAllByCourse(MetaData metaData, String refId) throws Exception {
		return dao.findAllByCourseRefId(metaData, refId);
	}

	@Override
	public List<Staff> findAllByRoster(MetaData metaData, String refId) throws Exception {
		return dao.findAllByRosterRefId(metaData, refId);
	}

	@Override
	public List<Staff> findAllByStudent(MetaData metaData, String refId) throws Exception {
		return dao.findAllByStudentRefId(metaData, refId);
	}

	@Override
	public Staff findById(MetaData metaData, String id) throws Exception {
		if (Util.isRefId(id)) {
			return dao.findByRefId(metaData, id);
		}
		else if(StringUtils.equalsIgnoreCase(metaData.getHeaders().get("IdType"), "local")) {
			return dao.findByLocalId(metaData, id);
		}
		else if(StringUtils.equalsIgnoreCase(metaData.getHeaders().get("IdType"), "state")) {
			return dao.findByStateId(metaData, id);
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
}
