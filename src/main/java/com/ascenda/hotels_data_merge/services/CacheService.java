package com.ascenda.hotels_data_merge.services;

import com.ascenda.hotels_data_merge.constants.ApplicationConstants;
import com.ascenda.hotels_data_merge.dto.Hotel;
import com.ascenda.hotels_data_merge.dto.Pricing;
import graphql.com.google.common.collect.Maps;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CacheService {

    private final CacheManager cacheManager;

    public CacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public Map<String, Hotel> getHotelCache() {
        Cache cache = this.cacheManager.getCache(ApplicationConstants.CACHE_NAME);
        assert cache != null;
        return cache.get(ApplicationConstants.KEY_NAME) != null ?
                cache.get(ApplicationConstants.KEY_NAME, HashMap.class) : Maps.newHashMap();
    }

    public void putCache(Map<String, com.ascenda.hotels_data_merge.dto.Hotel> hotelMap) {
        Cache cache = this.cacheManager.getCache(ApplicationConstants.CACHE_NAME);
        assert cache != null;
        cache.put(ApplicationConstants.KEY_NAME, hotelMap);
    }

    public void putPricingCache(List<Pricing> pricingList) {
        Cache cache = this.cacheManager.getCache(ApplicationConstants.PRICING_CACHE);
        assert cache != null;
        cache.put(ApplicationConstants.PRICING_CACHE, pricingList);
    }

    public List<Pricing> getPricingCache() {
        Cache cache = this.cacheManager.getCache(ApplicationConstants.PRICING_CACHE);
        if (cache != null)
         return cache.get(ApplicationConstants.PRICING_CACHE, List.class);
        return new ArrayList<>();
    }
}
