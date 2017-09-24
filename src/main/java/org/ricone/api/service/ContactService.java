package org.ricone.api.service;

import org.ricone.api.dao.ContactDAO;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.StudentContact;
import org.springframework.beans.factory.annotation.Autowired;
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
	public List<StudentContact> findAll() throws NotFoundException {
		return dao.findAll();
	}

	@Override
	public StudentContact findByRefId(String refId) throws NotFoundException {
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
