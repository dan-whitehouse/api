package org.ricone.api.task;

import org.ricone.api.cache.AppCache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheTask 
{

    @Scheduled(fixedRate = 15 * (60 * 1000))
    public void clearCache()
    {
        //Removes all cached apps
        System.out.println("Task: Clearing App Cache");
        AppCache.getInstance().clearCache();
    }
}