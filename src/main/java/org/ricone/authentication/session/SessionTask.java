package org.ricone.authentication.session;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SessionTask {
    @Scheduled(fixedRate = 5 * (60 * 1000))
    public void removeExpiredSessions() {
        Date now = new Date();
        System.out.println("Task: Checking to remove expired tokens - " + now);
        for (Session session : SessionManager.getInstance().getSessions().values()) {
            if(now.after(session.getToken().getExp())) {
                System.out.println("\tTask: Removed session associated to - " + session.getToken().getApplication_id());
                SessionManager.getInstance().removeSession(session.getToken().getApplication_id());
            }
        }
    }
}