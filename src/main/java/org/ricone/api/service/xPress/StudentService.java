package org.ricone.api.service.xPress;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.controller.extension.MetaData;
import org.ricone.api.dao.xPress.StudentDAO;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Student;
import org.ricone.api.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("StudentService")
@Transactional
public class StudentService implements IStudentService
{
	@Autowired
	private StudentDAO dao;

	@Override
	public List<Student> findAll(MetaData metaData) throws Exception {
		return dao.findAll(metaData);
	}

	@Override
	public List<Student> findAllByLea(MetaData metaData, String refId) throws Exception {
		return dao.findAllByLeaRefId(metaData, refId);
	}

	@Override
	public List<Student> findAllBySchool(MetaData metaData, String refId) throws Exception {
		return dao.findAllBySchoolRefId(metaData, refId);
	}

	@Override
	public List<Student> findAllByRoster(MetaData metaData, String refId) throws Exception {
		return dao.findAllByRosterRefId(metaData, refId);
	}

	@Override
	public List<Student> findAllByStaff(MetaData metaData, String refId) throws Exception {
		return dao.findAllByStaffRefId(metaData, refId);
	}

	@Override
	public List<Student> findAllByContact(MetaData metaData, String refId) throws Exception {
		return dao.findAllByContactRefId(metaData, refId);
	}

	@Override
	public Student findById(MetaData metaData, String id) throws Exception {
		if (Util.isRefId(id)) {
			return dao.findByRefId(metaData, id);
		}
		else if(StringUtils.equalsIgnoreCase(metaData.getHeader("IdType"), "local")) {
			return dao.findByLocalId(metaData, id);
		}
		else if(StringUtils.equalsIgnoreCase(metaData.getHeader("IdType"), "state")) {
			return dao.findByStateId(metaData, id);
		}
		else {
			throw new NotFoundException("Id: " + id + " is not a valid refId. You may be missing the 'IdType' header.");
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
}
