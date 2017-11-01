package org.ricone.api.task;

import org.ricone.api.security.Session;
import org.ricone.api.security.SessionManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SessionTask 
{
    @Scheduled(fixedRate = 6000)
    public void removeExpiredSessions() 
    {
    	Date now = new Date();
        for(Session session: SessionManager.getInstance().getSessions().values())
        {
        	if(now.after(session.getToken().getExp()))
        	{
        		SessionManager.getInstance().removeSession(session.getToken().getApplication_id());
        	}
        }
    }
}