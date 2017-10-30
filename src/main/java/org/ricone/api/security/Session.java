package org.ricone.api.security;

import org.ricone.api.component.config.model.App;

public class Session 
{
	private DecodedToken token;
	private App app;
	
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
}
