package org.ricone.api.config;

import java.util.HashMap;
import java.util.Properties;


import javax.annotation.Resource;
import javax.sql.DataSource;

import org.ricone.api.exception.ConfigException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "org.ricone.api")
@PropertySource("classpath:database.properties")
public class DBConfig {

    private static final String PROPERTY_NAME_DATABASE_DRIVER   = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_NAME_DATABASE_URL      = "db.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
	
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

	private static final String PROPERTY_NAME_HIBERNATE_CACHE_USE_2ND_LEVEL = "hibernate.cache.use_second_level_cache";
	private static final String PROPERTY_NAME_HIBERNATE_CACHE_FACTORY_CLASS = "hibernate.cache.region.factory_class";

	@Resource
	private Environment env;
	
	@Bean
	public DataSource dataSource() throws ConfigException
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));

		try 
		{
			dataSource.setUrl(ConfigProperties.getInstance().getProperty("db.core.url"));
			dataSource.setUsername(ConfigProperties.getInstance().getProperty("db.core.username"));
			dataSource.setPassword(ConfigProperties.getInstance().getProperty("db.core.password"));
		} 
		catch (ConfigException e) 
		{
			throw new ConfigException("test");
		}
		
		
		return dataSource;
	}
	
	private Properties hibProperties() {
		Properties properties = new Properties();
		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		properties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
		properties.put(PROPERTY_NAME_HIBERNATE_CACHE_USE_2ND_LEVEL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_CACHE_USE_2ND_LEVEL));
		properties.put(PROPERTY_NAME_HIBERNATE_CACHE_FACTORY_CLASS, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_CACHE_FACTORY_CLASS));
		properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		return properties;	
	}
	
	@Bean
	public HibernateTransactionManager transactionManager() throws ConfigException {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() throws ConfigException 
	{
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan(env.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
		sessionFactoryBean.setHibernateProperties(hibProperties());
		return sessionFactoryBean;
	}
}