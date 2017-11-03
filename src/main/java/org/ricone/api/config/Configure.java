package org.ricone.api.config;

import org.ricone.api.cache.AppCache;
import org.ricone.api.component.config.ConfigService;
import org.ricone.api.component.config.model.App;
import org.ricone.api.component.config.model.District;
import org.ricone.api.exception.ConfigException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Configure 
{
	static 
	{
		try 
		{
			setConfigProperties();
			ConfigService.getInstance().initializeCredential();
			//initializeAppCache();
		} 
		catch (ConfigException e)
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

		Date startTime = new Date();
		ConfigProperties.getInstance().setProperty("init.startTime", startTime.getTime()+"");
		
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
		catch(Exception e) { e.printStackTrace();}
		finally
		{
			if(apps != null)
			{
				for(App app : apps)
				{
					List<District> districts = ConfigService.getInstance().getDistrictsByApp(app.getId());
					districts.forEach(district -> {
						HashMap<String, String> kv = ConfigService.getInstance().getDistrictAPIKV(district.getId());
						district.setKv(kv);
					});
					app.setDistricts(districts);
					AppCache.getInstance().put(app.getId(), app);
				}
			}
			else
			{
				throw new ConfigException("Failed to create App Cache");
			}
		}
	}

}
