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
	LeaDao laeDao ;
	
	public List<Lea> getLeas()
	{
		return laeDao.getLeas();
	}
	
	public Lea getByRefId(String refId) throws NotFoundException
	{
		return laeDao.findByRefId(refId);
	}

	public Lea getByLocalId(String localId)
	{
		return laeDao.findByLocalId(localId);
	}
}
