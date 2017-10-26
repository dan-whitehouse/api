package org.ricone.api.service;

import org.ricone.api.dao.ContactDAO;
import org.ricone.api.model.core.StudentContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("ContactService")
@Transactional
public class ContactService implements IContactService
{
	@Autowired
	ContactDAO dao;

	@Override
	public List<StudentContact> findAll(Pageable pageRequest) throws Exception {
		return dao.findAll(pageRequest);
	}

	@Override
	public List<StudentContact> findAllByLea(Pageable pageRequest, String refId) throws Exception {
		return dao.findAllByLeaRefId(pageRequest, refId);
	}

	@Override
	public List<StudentContact> findAllBySchool(Pageable pageRequest, String refId) throws Exception {
		return dao.findAllBySchoolRefId(pageRequest, refId);
	}

	@Override
	public List<StudentContact> findAllByStudent(Pageable pageRequest, String refId) throws Exception {
		return dao.findAllByStudentRefId(pageRequest, refId);
	}

	@Override
	public StudentContact findByRefId(String refId) throws Exception {
		return dao.findByRefId(refId);
	}

	@Override
	public void save(StudentContact instance) {
		dao.save(instance);
	}

	@Override
	public void update(StudentContact instance) {
		dao.update(instance);
	}

	@Override
	public void delete(StudentContact instance) {
		dao.delete(instance);
	}

	@Override
	public void deleteByRefId(String refId) {
		dao.deleteByRefId(refId);
	}
}
