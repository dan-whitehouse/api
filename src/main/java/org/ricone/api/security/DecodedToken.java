package org.ricone.api.security;

public class DecodedToken
{
	private String application_id;
	private long iat;
	private long exp;
	private String iss;

	
	public DecodedToken()
	{
		super();
	}
	
	public String getApplication_id()
	{
		return application_id;
	}
	public void setApplication_id(String application_id)
	{
		this.application_id = application_id;
	}
	public long getIat()
	{
		return iat;
	}
	public void setIat(long iat)
	{
		this.iat = iat;
	}
	public long getExp()
	{
		return exp;
	}
	public void setExp(long exp)
	{
		this.exp = exp;
	}
	public String getIss()
	{
		return iss;
	}
	public void setIss(String iss)
	{
		this.iss = iss;
	}	
}