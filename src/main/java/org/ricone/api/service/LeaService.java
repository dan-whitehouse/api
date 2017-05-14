package org.ricone.api.service;


import java.util.List;

import javax.transaction.Transactional;

import org.ricone.api.dao.LeaDao;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.Lea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LeaService {
	
	@Autowired
	LeaDao leaDao;
	
	public List<Lea> getLeas() throws NotFoundException
	{
		return leaDao.getLeas();
	}
	
	public Lea getByRefId(String refId) throws Exception
	{
		//Attempts to load from Cache, otherwise will query database;
		//return leaDao.findById(refId);
		return leaDao.findByRefId(refId);
	}

	public Lea getByLocalId(String localId)
	{
		return leaDao.findByLocalId(localId);
	}
}
