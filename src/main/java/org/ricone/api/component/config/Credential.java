package org.ricone.api.component.config;

public class Credential
{
	private String id;
	private int ttl;
	private String created;
	private int userId;
	
	public Credential(String id, int ttl, String created, int userId)
	{
		super();
		this.id = id;
		this.ttl = ttl;
		this.created = created;
		this.userId = userId;
	}
	
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public int getTtl()
	{
		return ttl;
	}

	public void setTtl(int ttl)
	{
		this.ttl = ttl;
	}

	public String getCreated()
	{
		return created;
	}

	public void setCreated(String created)
	{
		this.created = created;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}
}