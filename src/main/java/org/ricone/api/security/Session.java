package org.ricone.api.security;

import org.ricone.api.component.config.App;
import org.ricone.api.component.config.Profile;

public class Session 
{
	private DecodedToken token;
	private App app;
	private Profile profile;
	
	public DecodedToken getToken() 
	{
		return token;
	}
	public void setToken(DecodedToken token) 
	{
		this.token = token;
	}
	public App getApp() 
	{
		return app;
	}
	public void setApp(App app) 
	{
		this.app = app;
	}
	public Profile getProfile() 
	{
		return profile;
	}
	public void setProfile(Profile profile) 
	{
		this.profile = profile;
	}
	
	
}
