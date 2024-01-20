package com.ascenda.hotels_data_merge.enrichments;

import com.ascenda.hotels_data_merge.constants.ApplicationConstants;
import com.ascenda.hotels_data_merge.dto.Hotel;
import com.ascenda.hotels_data_merge.dto.Pricing;
import com.ascenda.hotels_data_merge.services.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class PricingEnrichment implements Enricher {

    private final Logger logger = LoggerFactory.getLogger(LocationEnrichment.class);

    private final CacheService cachingService;

    public PricingEnrichment(CacheService cacheService) {
        this.cachingService = cacheService;
    }

    @Override
    public void enrich(Hotel dtoHotel, com.ascenda.hotels_data_merge.models.Hotel hotel) {
        logger.info("Merge pricing info");
        List<Pricing> pricingList = this.cachingService.getPricingCache();
        Optional<Pricing> pricing = pricingList.stream().filter(x -> Objects.equals(x.hotelId, hotel.id)).findFirst();
        pricing.ifPresent(value -> dtoHotel.price = value.getPrice());
    }
}