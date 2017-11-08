package org.ricone.api.service.xPress;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.dao.xPress.LeaDAO;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Lea;
import org.ricone.api.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("leaService")
@Transactional
public class LeaService implements ILeaService
{
	@Autowired
	private LeaDAO dao;

	@Override
	public List<Lea> findAll(MetaData metaData) throws Exception {
		return dao.findAll(metaData);
	}

	@Override
	public List<Lea> findAllBySchool(MetaData metaData, String refId) throws Exception {
		return dao.findAllBySchoolRefId(metaData, refId);
	}

	@Override
	public List<Lea> findAllByCalendar(MetaData metaData, String refId) throws Exception {
		return dao.findAllByCalendarRefId(metaData, refId);
	}

	@Override
	public List<Lea> findAllByCourse(MetaData metaData, String refId) throws Exception {
		return dao.findAllByCourseRefId(metaData, refId);
	}

	@Override
	public List<Lea> findAllByRoster(MetaData metaData, String refId) throws Exception {
		return dao.findAllByRosterRefId(metaData, refId);
	}

	@Override
	public List<Lea> findAllByStaff(MetaData metaData, String refId) throws Exception {
		return dao.findAllByStaffRefId(metaData, refId);
	}

	@Override
	public List<Lea> findAllByStudent(MetaData metaData, String refId) throws Exception {
		return dao.findAllByStudentRefId(metaData, refId);
	}

	@Override
	public List<Lea> findAllByContact(MetaData metaData, String refId) throws Exception {
		return dao.findAllByContactRefId(metaData, refId);
	}

	@Override
	public Lea findById(MetaData metaData, String id) throws Exception
	{
		if (Util.isRefId(id)) {
			return dao.findByRefId(metaData, id);
		}
		else if(StringUtils.equalsIgnoreCase(metaData.getHeader("IdType"), "local")) {
			return dao.findByLocalId(metaData, id);
		}
		else {
			throw new NotFoundException("Id: " + id + " is not a valid refId. You may be missing the 'IdType' header.");
		}
	}

	@Override
	public void save(Lea instance) {
		dao.save(instance);
	}

	@Override
	public void update(Lea instance) {
		dao.update(instance);
	}

	@Override
	public void delete(Lea instance) {
		dao.delete(instance);
	}
}
