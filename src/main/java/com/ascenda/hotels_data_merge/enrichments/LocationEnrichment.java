package com.ascenda.hotels_data_merge.enrichments;

import com.ascenda.hotels_data_merge.dto.Hotel;
import com.ascenda.hotels_data_merge.dto.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LocationEnrichment implements Enricher {

    private final Logger logger = LoggerFactory.getLogger(LocationEnrichment.class);

    @Override
    public void enrich(Hotel dtoHotel, com.ascenda.hotels_data_merge.models.Hotel hotel) throws EnrichmentException {
        try {
            if (dtoHotel.location == null)
                dtoHotel.location = new Location();
            dtoHotel.location.city = dtoHotel.location.city == null ? hotel.city : dtoHotel.location.city;
            dtoHotel.location.address = dtoHotel.location.address == null ? hotel.address : dtoHotel.location.address;
            dtoHotel.location.country = dtoHotel.location.country == null ? hotel.country : dtoHotel.location.country;
            dtoHotel.location.latitude = dtoHotel.location.latitude == null ? hotel.latitude : dtoHotel.location.latitude;
            dtoHotel.location.longitude = dtoHotel.location.longitude == null ? hotel.longitude : dtoHotel.location.longitude;
            if (hotel.postalCode != null)
                dtoHotel.location.address += ", " + hotel.postalCode;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new EnrichmentException("Unable to process locations", e);
        }
    }
}
