package org.ricone.init;

import org.apache.commons.lang3.StringUtils;
import org.ricone.error.exception.ConfigException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class ConfigProperties 
{
	private static ConfigProperties instance = null;
	private static final String PROPERTY_FILE = "config.properties";
	private static Resource resource = new ClassPathResource(PROPERTY_FILE);
	private static Properties properties = null;
	
	public static ConfigProperties getInstance() throws ConfigException
	{
		if(instance == null)
		{
			instance = new ConfigProperties();
			
			try 
			{
				properties = PropertiesLoaderUtils.loadProperties(resource);
			}
			catch (IOException e) 
			{
				throw new ConfigException("Could not load: " + PROPERTY_FILE);
			}
		}
		return instance;
	}
	
	public String getProperty(String key) throws ConfigException
	{
		String out = properties.getProperty(key);
		if(StringUtils.isNotEmpty(out))
		{		
			return out;
		}
		else
		{
			throw new ConfigException("Could not load key: " + key + " from: " + PROPERTY_FILE);
		}		
	}
	public void setProperty(String key, String value) throws ConfigException
	{
		properties.setProperty(key, value);		
	}
}
