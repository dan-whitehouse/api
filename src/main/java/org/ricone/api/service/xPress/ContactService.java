package org.ricone.api.service.xPress;

import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.dao.xPress.ContactDAO;
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
	private ContactDAO dao;

	@Override
	public List<StudentContact> findAll(MetaData metaData) throws Exception {
		return dao.findAll(metaData);
	}

	@Override
	public List<StudentContact> findAllByLea(MetaData metaData, String refId) throws Exception {
		return dao.findAllByLeaRefId(metaData, refId);
	}

	@Override
	public List<StudentContact> findAllBySchool(MetaData metaData, String refId) throws Exception {
		return dao.findAllBySchoolRefId(metaData, refId);
	}

	@Override
	public List<StudentContact> findAllByStudent(MetaData metaData, String refId) throws Exception {
		return dao.findAllByStudentRefId(metaData, refId);
	}

	@Override
	public StudentContact findByRefId(MetaData metaData, String refId) throws Exception {
		return dao.findByRefId(metaData, refId);
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
}
