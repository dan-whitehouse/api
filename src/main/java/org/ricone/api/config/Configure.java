package org.ricone.api.config;

import java.util.HashMap;

import org.ricone.api.component.config.ConfigService;
import org.ricone.api.exception.ConfigException;

public class Configure 
{
	static 
	{
		try 
		{
			setConfigProperties();
			ConfigService.getInstance().initializeCredential();
		} 
		catch (ConfigException e) 
		{
			e.printStackTrace();
		}
	}
	
	private static void setConfigProperties() throws ConfigException
	{
		//From Environment
		ConfigProperties.getInstance().setProperty("component.config.provider", System.getenv("provider_id"));
		ConfigProperties.getInstance().setProperty("component.config.username", System.getenv("api_config_username"));
		ConfigProperties.getInstance().setProperty("component.config.password", System.getenv("api_config_password"));
		ConfigProperties.getInstance().setProperty("component.config.href", System.getenv("config_url"));
		
		//From Configuration Service
		try
		{
			String providerId = ConfigProperties.getInstance().getProperty("component.config.provider");
			HashMap<String, String> kv = ConfigService.getInstance().getProviderAPIKV(providerId);
			
			ConfigProperties.getInstance().setProperty("component.auth.href", kv.get("auth.url"));
			
			ConfigProperties.getInstance().setProperty("db.dialect", kv.get("db.dialect"));
			ConfigProperties.getInstance().setProperty("db.driver", kv.get("db.driver"));
			
			ConfigProperties.getInstance().setProperty("db.core.url", kv.get("db.core.url"));
			ConfigProperties.getInstance().setProperty("db.core.username", kv.get("db.core.username"));
			ConfigProperties.getInstance().setProperty("db.core.password", kv.get("db.core.password"));
						
			ConfigProperties.getInstance().setProperty("db.infra.url", kv.get("db.infra.url"));
			ConfigProperties.getInstance().setProperty("db.infra.username", kv.get("db.infra.username")); 
			ConfigProperties.getInstance().setProperty("db.infra.password", kv.get("db.infra.password"));
		}
		catch(ConfigException e)
		{
			throw new ConfigException("Failed to retrieve ProviderKV");
		}
	}
}
