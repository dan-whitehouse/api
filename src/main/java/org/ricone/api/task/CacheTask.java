package org.ricone.api.task;

import org.ricone.api.cache.AppCache;
import org.ricone.api.cache.ProfileCache;
import org.ricone.api.component.config.model.App;
import org.ricone.api.component.config.model.Profile;
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
        for(Profile profile: ProfileCache.getInstance().getProfiles().values())
        {
        	System.out.println(profile.getId());
        }
    }
}