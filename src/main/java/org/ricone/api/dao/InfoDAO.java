package org.ricone.api.dao;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ricone.api.cache.CacheContainer;
import org.ricone.api.component.config.ConfigService;
import org.ricone.api.config.ConfigProperties;
import org.ricone.api.exception.ConfigException;
import org.ricone.api.model.info.Api;
import org.ricone.api.model.info.Config;
import org.ricone.api.model.info.Db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Repository
public class InfoDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	private final CacheContainer cacheContainer = new CacheContainer();

	@Resource
	private Environment env;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Db getDB()
	{
		Db db = new Db();
		String version = null;
		String query = "select concat(sv.major, '.', sv.minor, '.', sv.bugFix) as version from SchemaVersion sv order by sv.dateApplied desc";

		try
		{
			version = (String) getCurrentSession().createQuery(query).setMaxResults(1).uniqueResult();
		}
		catch(Exception e) {}

		if(StringUtils.isNotBlank(version))
		{
			db.setVersion(version);
			db.setStatus("Up");
		}
		else
		{
			db.setStatus("Down");
		}
		return db;
	}

	public Api getAPI() throws ConfigException {
		Api api = new Api();
		api.setVersion(env.getRequiredProperty("info.app.version"));
		api.setStarted(ConfigProperties.getInstance().getProperty("init.startTime"));

		LocalDateTime start = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(api.getStarted())), ZoneId.systemDefault());
		LocalDateTime now = LocalDateTime.now();

		Duration d = Duration.between(start, now);
		api.setUptime((double)d.getSeconds());

		return api;
	}

	public Config getConfig()
	{
		return ConfigService.getInstance().getConfigStatus();
	}
}