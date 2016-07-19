package org.ricone.api.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheTask 
{
//    @Scheduled(fixedRate = 6000)
//    public void displayApps() 
//    {
//        for(App app: AppCache.getInstance().getApps().values())
//        {
//        	System.out.println(app.getId());
//        }
//    }
//    
    @Scheduled(fixedRate = 6000)
    public void displayProfiles() 
    {

    }
}