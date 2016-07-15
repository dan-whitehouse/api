package org.ricone.api.cache;

import java.util.HashMap;

import org.ricone.api.component.config.ConfigService;
import org.ricone.api.component.config.model.Profile;

public class ProfileCache implements ICache<Profile>
{
	private static ProfileCache instance = null;
	private HashMap<String, Profile> profiles = new HashMap<String, Profile>();
	
	public static ProfileCache getInstance()
	{
		if(instance == null)
		{
			instance = new ProfileCache();
		}
		return instance;
	}
	
	public HashMap<String, Profile> getProfiles() 
	{
		return profiles;
	}

	public Profile getProfile(String appId) 
	{
		Profile profile = profiles.get(appId);		
		if(profile == null)
		{
			profile = ConfigService.getInstance().getProfileByAppId(appId);		
		}
		return profile;
	}
	
	public void addProfile(String profileId, Profile profile) 
	{
		this.profiles.put(profileId, profile);
	}
	
	public void removeProfile(String profileId) 
	{
		this.profiles.remove(profileId);
	}
	
	public void saveProfile(String profileId, Profile profile) 
	{
		this.profiles.remove(profileId);
	}
	
	public void loadProfile(String profileId) 
	{
		this.profiles.remove(profileId);
	}
}
