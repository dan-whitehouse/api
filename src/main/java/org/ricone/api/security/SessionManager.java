package org.ricone.api.security;

import java.util.HashMap;

public class SessionManager
{
	private static SessionManager instance = null;
	private HashMap<String, Session> sessions = new HashMap<>();

	public static SessionManager getInstance()
	{
		if(instance == null)
		{
			instance = new SessionManager();
		}
		return instance;
	}

	public HashMap<String, Session> getSessions() {
		return sessions;
	}

	void addSession(String appId, Session session)
	{
		this.sessions.put(appId, session);
	}
	
	public void removeSession(String appId) 
	{
		this.sessions.remove(appId);
	}
}