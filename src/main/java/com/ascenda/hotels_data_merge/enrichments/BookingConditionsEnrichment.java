package com.ascenda.hotels_data_merge.enrichments;

import com.ascenda.hotels_data_merge.dto.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BookingConditionsEnrichment implements Enricher {

    private final Logger logger = LoggerFactory.getLogger(BookingConditionsEnrichment.class);
    @Override
    public void enrich(Hotel dtoHotel, com.ascenda.hotels_data_merge.models.Hotel hotel) throws EnrichmentException {
        try {
            if (hotel.bookingConditions != null) {
                if (dtoHotel.bookingConditions == null)
                    dtoHotel.bookingConditions = new ArrayList<>();

                for (String bookingCondition : hotel.bookingConditions) {
                    if (dtoHotel.bookingConditions.stream().parallel().noneMatch(e -> e.equalsIgnoreCase(bookingCondition)))
                        dtoHotel.bookingConditions.add(bookingCondition);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new EnrichmentException("Unable to process locations", e);
        }
    }
}
