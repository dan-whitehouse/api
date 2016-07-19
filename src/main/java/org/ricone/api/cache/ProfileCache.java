package org.ricone.api.cache;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import org.ricone.api.component.config.ConfigService;
import org.ricone.api.component.config.model.Profile;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Singleton
public class ProfileCache
{
	private static ProfileCache instance = null;
	private static final Integer EXPIRE = 2;

	private final LoadingCache<String, Profile> cache;
	 
	public static ProfileCache getInstance()
	{
		if(instance == null)
		{
			instance = new ProfileCache();
		}
		return instance;
	}
	
	public ProfileCache() 
	{
		cache = CacheBuilder.newBuilder()
		.expireAfterWrite(EXPIRE, TimeUnit.HOURS)
		.build( new CacheLoader<String, Profile>() 
		{
	             @Override
	             public Profile load( String profileId ) throws Exception 
	             {
	            	 return loadCache(profileId);
	             }	
		});
	}
	 
	 public Profile get(String profileId) 
	 {
	      return cache.getUnchecked(profileId);
	 }
	 
	 public void put(String profileId, Profile profile) 
	 {
	      cache.put(profileId, profile);
	 }
	 
	 private Profile loadCache(String appId) 
	 {
		 Profile profile = ConfigService.getInstance().getProfileByAppId(appId);
	     return profile;
	 }
}