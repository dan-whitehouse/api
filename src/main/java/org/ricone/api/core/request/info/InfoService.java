package org.ricone.api.core.request.info;

import org.ricone.api.core.request.info.model.Api;
import org.ricone.api.core.request.info.model.Config;
import org.ricone.api.core.request.info.model.Db;
import org.ricone.error.exception.ConfigException;
import org.ricone.error.exception.NotFoundException;
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
