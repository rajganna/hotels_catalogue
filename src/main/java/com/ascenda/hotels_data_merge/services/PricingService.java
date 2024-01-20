package com.ascenda.hotels_data_merge.services;

import com.ascenda.hotels_data_merge.dto.Hotel;
import com.ascenda.hotels_data_merge.dto.Pricing;
import com.ascenda.hotels_data_merge.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@Service
public class PricingService {

    /**
     * Hotel service enriches the data from different suppliers.
     *
     */
    @Value("${hotels.pricing}")
    public String pricingUrl;

    private final CacheService cacheService;

    public PricingService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Async
    public void getPricing(List<String> hotelIds) throws URISyntaxException, IOException, InterruptedException {
        if (hotelIds != null) {
            String url = buildUrl(hotelIds);
            List<Pricing> response = HttpUtil.sendPricingGet(url);
            this.cacheService.putPricingCache(response);
        }
    }

    @Scheduled(fixedRate = 25000)
    public void syncPricing() throws URISyntaxException, IOException, InterruptedException {
        Map<String, Hotel> hotels = this.cacheService.getHotelCache();
        this.getPricing(hotels.keySet().stream().toList());
    }

    private String buildUrl(List<String> hotelIds) {
        StringBuilder baseUrlBuilder = new StringBuilder(pricingUrl);
        for(String hotelId : hotelIds) {
            baseUrlBuilder.append("&hotel_ids=").append(hotelId);
        }
        return baseUrlBuilder.toString();
    }
}