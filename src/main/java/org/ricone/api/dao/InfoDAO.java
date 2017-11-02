package org.ricone.api.dao;

import org.hibernate.query.Query;
import org.ricone.api.component.config.ConfigService;
import org.ricone.api.config.ConfigProperties;
import org.ricone.api.exception.ConfigException;
import org.ricone.api.model.core.SchemaVersion;
import org.ricone.api.model.info.Api;
import org.ricone.api.model.info.Config;
import org.ricone.api.model.info.Db;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Repository
public class InfoDAO extends AbstractDAO<Integer, SchemaVersion>
{
	private final String MAJOR = "major";
	private final String MINOR = "minor";
	private final String BUG = "bugFix";

	@Resource
	private Environment env;

	public Db getDB()
	{
		final CriteriaBuilder cb = getSession().getCriteriaBuilder();
		final CriteriaQuery<SchemaVersion> select = cb.createQuery(SchemaVersion.class);
		final Root<SchemaVersion> from = select.from(SchemaVersion.class);

		select.distinct(true);
		select.select(from);

		select.orderBy(cb.desc(from.get(MAJOR)), cb.desc(from.get(MINOR)), cb.desc(from.get(BUG)));

		Query<SchemaVersion> q = getSession().createQuery(select).setMaxResults(1);
		SchemaVersion schemaVersion = q.getSingleResult();

		Db db = new Db();
		if(schemaVersion != null) {
			db.setVersion(schemaVersion.getVersion());
			db.setStatus("Up");
		}
		else {
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