package org.ricone.api.service;

import org.ricone.api.dao.StudentDAO;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
	public Student findByRefId(String refId) throws Exception {
		return dao.findByRefId(refId);
	}

	@Override
	public Student findByLocalId(String localId) throws Exception {
		return dao.findByLocalId(localId);
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
