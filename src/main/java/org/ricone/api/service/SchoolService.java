package org.ricone.api.service;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.dao.SchoolDAO;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.School;
import org.ricone.api.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("schoolService")
@Transactional
public class SchoolService implements ISchoolService
{
	@Autowired
	SchoolDAO dao;

	@Override
	public List<School> findAll(MetaData metaData) throws Exception {
		return dao.findAll(metaData);
	}

	@Override
	public List<School> findAllByLea(MetaData metaData, String refId) throws Exception {
		return dao.findAllByLeaRefId(metaData, refId);
	}

	@Override
	public List<School> findAllByCalendar(MetaData metaData, String refId) throws Exception {
		return dao.findAllByCalendarRefId(metaData, refId);
	}

	@Override
	public List<School> findAllByCourse(MetaData metaData, String refId) throws Exception {
		return dao.findAllByCourseRefId(metaData, refId);
	}

	@Override
	public List<School> findAllByRoster(MetaData metaData, String refId) throws Exception {
		return dao.findAllByRosterRefId(metaData, refId);
	}

	@Override
	public List<School> findAllByStaff(MetaData metaData, String refId) throws Exception {
		return dao.findAllByStaffRefId(metaData, refId);
	}

	@Override
	public List<School> findAllByStudent(MetaData metaData, String refId) throws Exception {
		return dao.findAllByStudentRefId(metaData, refId);
	}

	@Override
	public List<School> findAllByContact(MetaData metaData, String refId) throws Exception {
		return dao.findAllByContactRefId(metaData, refId);
	}

	@Override
	public School findById(MetaData metaData, String id) throws Exception {
		if (Util.isRefId(id)) {
			return dao.findByRefId(metaData, id);
		}
		else if(StringUtils.equalsIgnoreCase(metaData.getHeader("IdType"), "local"))
		{
			return dao.findByLocalId(metaData, id);
		}
		else if(StringUtils.equalsIgnoreCase(metaData.getHeader("IdType"), "BEDS"))
		{
			return dao.findByBEDSId(metaData, id);
		}
		else
		{
			throw new NotFoundException("Id: " + id + " is not a valid refId. You may be missing the 'IdType' header.");
		}
	}

	@Override
	public void save(School instance) {
		dao.save(instance);
	}

	@Override
	public void update(School instance) {
		dao.update(instance);
	}

	@Override
	public void delete(School instance) {
		dao.delete(instance);
	}
}
