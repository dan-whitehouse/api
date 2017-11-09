package org.ricone.api.core.info;

import org.ricone.api.core.info.model.Api;
import org.ricone.api.core.info.model.Config;
import org.ricone.api.core.info.model.Db;
import org.ricone.exception.ConfigException;
import org.ricone.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class InfoService {
	
	@Autowired
	private InfoDAO infoDao;

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
