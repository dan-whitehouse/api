package org.ricone.api.service;

import java.util.List;
import javax.transaction.Transactional;
import org.ricone.api.dao.LeaDAO;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Lea;
import org.ricone.api.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("leaService")
@Transactional
public class LeaService implements ILeaService
{
	@Autowired
	LeaDAO dao;

	@Override
	public List<Lea> findAll(Pageable pageRequest) throws Exception {
		return dao.findAll(pageRequest);
	}

	@Override
	public List<Lea> findAllBySchool(Pageable pageRequest, String refId) throws Exception {
		return dao.findAllBySchoolRefId(pageRequest, refId);
	}

	@Override
	public List<Lea> findAllByCalendar(Pageable pageRequest, String refId) throws Exception {
		return dao.findAllByCalendarRefId(pageRequest, refId);
	}

	@Override
	public List<Lea> findAllByCourse(Pageable pageRequest, String refId) throws Exception {
		return dao.findAllByCourseRefId(pageRequest, refId);
	}

	@Override
	public List<Lea> findAllByRoster(Pageable pageRequest, String refId) throws Exception {
		return dao.findAllByRosterRefId(pageRequest, refId);
	}

	@Override
	public List<Lea> findAllByStaff(Pageable pageRequest, String refId) throws Exception {
		return dao.findAllByStaffRefId(pageRequest, refId);
	}

	@Override
	public List<Lea> findAllByStudent(Pageable pageRequest, String refId) throws Exception {
		return dao.findAllByStudentRefId(pageRequest, refId);
	}

	@Override
	public List<Lea> findAllByContact(Pageable pageRequest, String refId) throws Exception {
		return dao.findAllByContactRefId(pageRequest, refId);
	}

	@Override
	public Lea findById(String id) throws Exception
	{
		if (Util.isRefId(id)) {
			return dao.findByRefId(id);
		}
		else {
			return dao.findByLocalId(id);
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

	@Override
	public void deleteByRefId(String refId) {
		dao.deleteByRefId(refId);
	}


}
