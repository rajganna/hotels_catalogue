package com.ascenda.hotels_data_merge.services;

import com.ascenda.hotels_data_merge.constants.ApplicationConstants;
import com.ascenda.hotels_data_merge.dto.Hotel;
import graphql.com.google.common.collect.Maps;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CacheService {

    private final CacheManager cacheManager;

    public CacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public Map<String, Hotel> getCache() {
        Cache cache = this.cacheManager.getCache(ApplicationConstants.CACHE_NAME);
        Map<String, com.ascenda.hotels_data_merge.dto.Hotel> hotels = cache.get(ApplicationConstants.KEY_NAME) != null ?
                cache.get(ApplicationConstants.KEY_NAME, HashMap.class) : Maps.newHashMap();
        return hotels;
    }

    public void putCache(Map<String, com.ascenda.hotels_data_merge.dto.Hotel> hotelMap) {
        Cache cache = this.cacheManager.getCache(ApplicationConstants.CACHE_NAME);
        cache.put(ApplicationConstants.KEY_NAME, hotelMap);
    }
}
