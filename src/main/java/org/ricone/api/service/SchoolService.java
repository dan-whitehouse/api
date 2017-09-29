package org.ricone.api.service;

import org.ricone.api.dao.LeaDAO;
import org.ricone.api.dao.SchoolDAO;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Lea;
import org.ricone.api.model.core.School;
import org.ricone.api.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;

@Service("schoolService")
@Transactional
public class SchoolService implements ISchoolService
{
	@Autowired
	SchoolDAO dao;

	@Override
	public List<School> findAll(Pageable pageRequest) throws Exception {
		return dao.findAll(pageRequest);
	}

	@Override
	public List<School> findAllByLea(Pageable pageRequest, String refId) throws Exception {
		return dao.findAllByLeaRefId(pageRequest, refId);
	}

	@Override
	public List<School> findAllByCalendar(Pageable pageRequest, String refId) throws Exception {
		return dao.findAllByCalendarRefId(pageRequest, refId);
	}

	@Override
	public List<School> findAllByCourse(Pageable pageRequest, String refId) throws Exception {
		return dao.findAllByCourseRefId(pageRequest, refId);
	}

	@Override
	public List<School> findAllByRoster(Pageable pageRequest, String refId) throws Exception {
		return dao.findAllByRosterRefId(pageRequest, refId);
	}

	@Override
	public List<School> findAllByStaff(Pageable pageRequest, String refId) throws Exception {
		return dao.findAllByStaffRefId(pageRequest, refId);
	}

	@Override
	public List<School> findAllByStudent(Pageable pageRequest, String refId) throws Exception {
		return dao.findAllByStudentRefId(pageRequest, refId);
	}

	@Override
	public List<School> findAllByContact(Pageable pageRequest, String refId) throws Exception {
		return dao.findAllByContactRefId(pageRequest, refId);
	}

	@Override
	public School findById(HttpServletRequest request, String id) throws Exception {
		if (Util.isRefId(id)) {
			return dao.findByRefId(id);
		}
		else if(request.getHeader("IdType").equalsIgnoreCase("local"))
		{
			return dao.findByLocalId(id);
		}
		else if(request.getHeader("IdType").equalsIgnoreCase("BEDS"))
		{
			return dao.findByBEDSId(id);
		}
		else
		{
			throw new NoResultException("Id: " + id + " is not a valid refId. You may be missing the 'IdType' header.");
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

	@Override
	public void deleteByRefId(String refId) {
		dao.deleteByRefId(refId);
	}
}
