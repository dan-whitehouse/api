package org.ricone.api.cache;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;

public class CacheContainer {

    private static final long maxItemsInCache = 500;
    private static final long cacheExpireAfterMinutes = 30;
    @SuppressWarnings("rawtypes")
	private final Map<String, LoadingCache> caches = Maps.newHashMap();


    public <K, V> V getFromCache(String cacheId, K key, CacheLoader<K, V> loader) throws Exception 
    {
        @SuppressWarnings("unchecked")
		LoadingCache<K, V> cache = caches.get(cacheId);
        if (cache == null) 
        {
            cache = CacheBuilder.newBuilder().
                     maximumSize(maxItemsInCache).
                     expireAfterWrite(cacheExpireAfterMinutes, TimeUnit.MINUTES).
                     build(loader);
            caches.put(cacheId, cache);
        }
        return cache.get(key);
    }
}