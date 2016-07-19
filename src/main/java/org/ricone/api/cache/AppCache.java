package org.ricone.api.cache;

import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import org.ricone.api.component.config.ConfigService;
import org.ricone.api.component.config.model.App;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

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
	                 public App load( String productId ) throws Exception {
	                     return loadCache(productId);
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
	 
	 private App loadCache(String appId) 
	 {
		 System.out.println("Loaded from Config: " + appId);
		 App app = ConfigService.getInstance().getApp(appId);
	     return app;
	 }
}