package org.ricone.config.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.ricone.config.ConfigService;
import org.ricone.config.model.App;
import org.ricone.config.model.District;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Singleton
public class AppCache
{
	private static AppCache instance = null;
	private static final Integer EXPIRE = 2;
	private final LoadingCache<String, App> cache;

	public static AppCache getInstance()
	{
		if(instance == null)
		{
			instance = new AppCache();
		}
		return instance;
	}
	
	 public AppCache() 
	 {
	      cache = CacheBuilder.newBuilder()
	           .expireAfterWrite(EXPIRE, TimeUnit.HOURS)
	           .build( new CacheLoader<String, App>() {
	                 @Override
	                 public App load( String appId ) throws Exception {
	                     return loadCache(appId);
	                 }
	           }
	     );
	 }
	 
	 public App get(String appId) 
	 {
	      return cache.getUnchecked(appId);
	 }
	 
	 public void put(String appId, App app) 
	 {
	      cache.put(appId, app);
	 }

	 public void clearCache()
	 {
	 	cache.invalidateAll();
	 }

	 private App loadCache(String appId) 
	 {
		 System.out.println("Loaded from Config: " + appId);
		 App app = ConfigService.getInstance().getApp(appId);
		 if(app != null)
		 {
			 List<District> districts = ConfigService.getInstance().getDistrictsByApp(app.getId());
			 districts.forEach(district -> {
				 HashMap<String, String> kv = ConfigService.getInstance().getDistrictAPIKV(district.getId());
				 district.setKv(kv);
			 });
			 app.setDistricts(districts);
		 }
		 return app;
	 }
}