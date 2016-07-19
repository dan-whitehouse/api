package org.ricone.api.config;

import java.io.IOException;
import java.util.HashMap;

import org.ricone.api.cache.AppCache;
import org.ricone.api.cache.ProfileCache;
import org.ricone.api.component.config.ConfigService;
import org.ricone.api.component.config.model.App;
import org.ricone.api.component.config.model.Profile;
import org.ricone.api.exception.ConfigException;

public class Configure 
{
	static 
	{
		try 
		{
			setConfigProperties();
			ConfigService.getInstance().initializeCredential();
			initializeAppCache();
			initializeProfileCache();
			
		} 
		catch (ConfigException | IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private static void setConfigProperties() throws ConfigException
	{
		System.out.println("Loading Config Properties....");
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
	
	public static void initializeAppCache() throws ConfigException
	{
		System.out.println("Loading App Cache....");
		App[] apps = null; 
		try
		{
			apps = ConfigService.getInstance().getApps();
		}
		finally
		{
			if(apps != null)
			{
				for(App app : apps)
				{
					AppCache.getInstance().put(app.getId(), app);
				}
			}
			else
			{
				throw new ConfigException("Failed to create App Cache");
			}
		}
	}
	
	public static void initializeProfileCache() throws ConfigException, IOException
	{
		System.out.println("Loading Profile Cache....");
		Profile[] profiles = null;
		try
		{
			profiles = ConfigService.getInstance().getProfiles();
		}
		finally
		{
			if(profiles != null)
			{
				for(Profile profile : profiles)
				{
					ProfileCache.getInstance().put(profile.getId(), profile);
				}
			}
			else
			{
				throw new ConfigException("Failed to create Profile Cache");				
			}
		}
	}
}
