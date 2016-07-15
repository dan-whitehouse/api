package org.ricone.api.component.config.model;

public class App
{
	private String id;	
	private String severityLevelDataAPI;	
	private String providerSecret;
	private String profile_id;	
	
	public App()
	{
		super();
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getSeverityLevelDataAPI()
	{
		return severityLevelDataAPI;
	}

	public void setSeverityLevelDataAPI(String severityLevelDataAPI)
	{
		this.severityLevelDataAPI = severityLevelDataAPI;
	}

	public String getProviderSecret()
	{
		return providerSecret;
	}

	public void setProviderSecret(String providerSecret)
	{
		this.providerSecret = providerSecret;
	}	
	
	public String getProfile_id()
	{
		return profile_id;
	}
	
	public void setProfile_id(String profile_id)
	{
		this.profile_id = profile_id;
	}
}
	