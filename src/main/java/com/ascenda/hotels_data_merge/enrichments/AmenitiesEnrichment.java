package com.ascenda.hotels_data_merge.enrichments;

import com.ascenda.hotels_data_merge.dto.Amenities;
import com.ascenda.hotels_data_merge.dto.Hotel;
import com.ascenda.hotels_data_merge.services.LanguageService;
import com.ascenda.hotels_data_merge.utils.ObjectMapperUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AmenitiesEnrichment implements Enricher {
    private final Logger logger = LoggerFactory.getLogger(BookingConditionsEnrichment.class);
    private final LanguageService languageService;

    public AmenitiesEnrichment(LanguageService languageService) {
        this.languageService = languageService;
    }

    @Override
    public void enrich(Hotel dtoHotel, com.ascenda.hotels_data_merge.models.Hotel hotel) throws EnrichmentException {
        try {

            if (dtoHotel.amenities == null)
                dtoHotel.amenities = new Amenities();

            if (hotel.facilities != null) {
                for (String facility : hotel.facilities) {
                    dtoHotel.amenities.general.add(languageService.correction(facility));
                }
            }

            if (hotel.amenities != null && hotel.amenities.isArray()) {
                for (JsonNode jsonNode : hotel.amenities) {
                    dtoHotel.amenities.room.add(WordUtils.uncapitalize(jsonNode.asText().trim()));
                }
            } else if (hotel.amenities != null && hotel.amenities.isObject()) {
                Amenities amenities = ObjectMapperUtil.getObjectMapper().treeToValue(hotel.amenities, Amenities.class);

                if (amenities.general != null) {
                    amenities.general.forEach(general -> dtoHotel.amenities.general.add(WordUtils.uncapitalize(general.trim())));
                }
                if (amenities.room != null)
                    amenities.room.forEach(room -> dtoHotel.amenities.room.add(WordUtils.uncapitalize(room.trim())));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new EnrichmentException("Unable to process amenities", e);
        }
    }
}
