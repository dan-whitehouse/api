package org.ricone.api.service;

import org.ricone.api.dao.LeaDAO;
import org.ricone.api.dao.SchoolDAO;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Lea;
import org.ricone.api.model.core.School;
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
	public List<School> findAll() throws Exception {
		return dao.findAll();
	}

	@Override
	public School findByRefId(String refId) throws Exception {
		return dao.findByRefId(refId);
	}

	@Override
	public School findByLocalId(String localId) throws Exception {
		return dao.findByLocalId(localId);
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
