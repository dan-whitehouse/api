package org.ricone.api.cache;

import java.util.HashMap;

import org.ricone.api.component.config.ConfigService;
import org.ricone.api.component.config.model.App;


public class AppCache implements ICache<App>
{
	private static AppCache instance = null;
	private HashMap<String, App> apps = new HashMap<String, App>();
	
	public static AppCache getInstance()
	{
		if(instance == null)
		{
			instance = new AppCache();
		}
		return instance;
	}
	
	public HashMap<String, App> getApps() 
	{
		return apps;
	}
	
	public App getApp(String appId) 
	{
		App app = apps.get(appId);		
		if(app == null)
		{
			app = ConfigService.getInstance().getApp(appId);		
		}
		return app;
	}

	public void addApp(String appId, App app) 
	{
		this.apps.put(appId, app);
	}
	
	public void removeApp(String appId) 
	{
		this.apps.remove(appId);
	}
}
