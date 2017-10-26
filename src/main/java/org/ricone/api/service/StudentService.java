package org.ricone.api.service;

import org.ricone.api.dao.StudentDAO;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Student;
import org.ricone.api.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@Service("StudentService")
@Transactional
public class StudentService implements IStudentService
{
	@Autowired
	StudentDAO dao;

	@Override
	public List<Student> findAll(Pageable pageRequest) throws Exception {
		return dao.findAll(pageRequest);
	}

	@Override
	public List<Student> findAllByLea(Pageable paging, String refId) throws Exception {
		return dao.findAllByLeaRefId(paging, refId);
	}

	@Override
	public List<Student> findAllBySchool(Pageable paging, String refId) throws Exception {
		return dao.findAllBySchoolRefId(paging, refId);
	}

	@Override
	public List<Student> findAllByRoster(Pageable paging, String refId) throws Exception {
		return dao.findAllByRosterRefId(paging, refId);
	}

	@Override
	public List<Student> findAllByStaff(Pageable paging, String refId) throws Exception {
		return dao.findAllByStaffRefId(paging, refId);
	}

	@Override
	public List<Student> findAllByContact(Pageable paging, String refId) throws Exception {
		return dao.findAllByContactRefId(paging, refId);
	}

	@Override
	public Student findById(HttpServletRequest request, String id) throws Exception {
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
	public void save(Student instance) {
		dao.save(instance);
	}

	@Override
	public void update(Student instance) {
		dao.update(instance);
	}

	@Override
	public void delete(Student instance) {
		dao.delete(instance);
	}

	@Override
	public void deleteByRefId(String refId) {
		dao.deleteByRefId(refId);
	}
}
