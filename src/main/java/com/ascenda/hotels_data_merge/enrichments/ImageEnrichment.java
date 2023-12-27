package com.ascenda.hotels_data_merge.enrichments;

import com.ascenda.hotels_data_merge.dto.Hotel;
import com.ascenda.hotels_data_merge.dto.ImageDetails;
import com.ascenda.hotels_data_merge.dto.Images;
import com.ascenda.hotels_data_merge.services.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ImageEnrichment implements Enricher {
    private final Logger logger = LoggerFactory.getLogger(ImageEnrichment.class);
    private final ImageService imageService;

    public ImageEnrichment(ImageService imageService) {
        this.imageService = imageService;
    }

    @Override
    public void enrich(Hotel dtoHotel, com.ascenda.hotels_data_merge.models.Hotel hotel) throws EnrichmentException {
        try {

            if (dtoHotel.images == null)
                dtoHotel.images = new Images();

            if (hotel.images != null) {

                hotel.images.amenities.stream().parallel().forEach(image -> {
                    ImageDetails imageDetails = new ImageDetails(image.url, image.description);
                    if (!dtoHotel.images.amenities.contains(imageDetails) && isValidURL(image.url))
                        dtoHotel.images.amenities.add(new ImageDetails(image.url, image.description));
                });


                hotel.images.rooms.stream().parallel().forEach(image -> {
                    ImageDetails imageDetails = new ImageDetails(image.url, image.description);
                    if (!dtoHotel.images.rooms.contains(imageDetails) && isValidURL(image.url))
                        dtoHotel.images.rooms.add(imageDetails);
                });


                hotel.images.site.stream().parallel().forEach(image -> {
                    ImageDetails imageDetails = new ImageDetails(image.url, image.description);
                    if (!dtoHotel.images.site.contains(imageDetails) && isValidURL(image.url))
                        dtoHotel.images.site.add(imageDetails);
                });
            }


        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new EnrichmentException("Unable to process images", e);
        }
    }

    private boolean isValidURL(String url) {
        return imageService.isValid(url);
    }
}
