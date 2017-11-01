package org.ricone.api.service;

import org.ricone.api.dao.InfoDAO;
import org.ricone.api.exception.ConfigException;
import org.ricone.api.exception.NotFoundException;
import org.ricone.api.model.info.Api;
import org.ricone.api.model.info.Config;
import org.ricone.api.model.info.Db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class InfoService {
	
	@Autowired
	InfoDAO infoDao;

	public Db getDB() throws NotFoundException
	{
		return infoDao.getDB();
	}

	public Api getAPI() throws NotFoundException, ConfigException {
		return infoDao.getAPI();
	}

	public Config getConfig() throws Exception
	{
		return infoDao.getConfig();
	}
}
