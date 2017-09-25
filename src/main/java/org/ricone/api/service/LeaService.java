package org.ricone.api.service;

import java.util.List;
import javax.transaction.Transactional;
import org.ricone.api.dao.LeaDAO;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.core.Lea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("leaService")
@Transactional
public class LeaService implements ILeaService
{
	@Autowired
	LeaDAO dao;

	@Override
	public List<Lea> findAll() throws Exception {
		return dao.findAll();
	}

	@Override
	public Lea findByRefId(String refId) throws Exception {
		return dao.findByRefId(refId);
	}

	@Override
	public Lea findByLocalId(String localId) throws Exception {
		return dao.findByLocalId(localId);
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
