package com.ascenda.hotels_data_merge.services;

import com.ascenda.hotels_data_merge.enrichments.*;
import com.ascenda.hotels_data_merge.models.Hotel;
import com.ascenda.hotels_data_merge.utils.HttpUtil;
import com.ascenda.hotels_data_merge.utils.ObjectMapperUtil;
import com.ascenda.hotels_data_merge.utils.SuppliersUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@Service
public class HotelService {
    /**
     * Hotel service enriches the data from different suppliers.
     *
     */
    @Value("${hotels.suppliers}")
    private List<String> suppliers;
    private final CacheService cacheService;
    private final LocationEnrichment locationEnrichment;
    private final ImageEnrichment imageEnrichment;
    private final BookingConditionsEnrichment bookingConditionsEnrichment;
    private final AmenitiesEnrichment amenitiesEnrichment;

    public HotelService(CacheService cacheService, LocationEnrichment locationEnrichment, ImageEnrichment imageEnrichment, BookingConditionsEnrichment bookingConditionsEnrichment, AmenitiesEnrichment amenitiesEnrichment) {
        this.cacheService = cacheService;
        this.locationEnrichment = locationEnrichment;
        this.imageEnrichment = imageEnrichment;
        this.bookingConditionsEnrichment = bookingConditionsEnrichment;
        this.amenitiesEnrichment = amenitiesEnrichment;
    }

    public List<com.ascenda.hotels_data_merge.dto.Hotel> merge(List<String> hotelIds, Integer destinationId) throws EnrichmentException, IOException, URISyntaxException {
        Map<String, com.ascenda.hotels_data_merge.dto.Hotel> hotelMap = cacheService.getCache();
        if (hotelMap.size() == 0) {
            List<String> response = HttpUtil.sendGet(SuppliersUtil.getSuppliersURL(suppliers));
            for (var hotel : response) {
                List<Hotel> hotels = List.of(ObjectMapperUtil
                        .getObjectMapper()
                        .readValue(hotel, Hotel[].class));
                transform(hotels);
            }
        }

        return filter(hotelIds, destinationId);
    }

    private void transform(List<Hotel> hotels) throws EnrichmentException {
        Map<String, com.ascenda.hotels_data_merge.dto.Hotel> hotelMap = cacheService.getCache();

        for (Hotel hotel : hotels) {
            boolean isMerge = hotelMap != null && hotelMap.containsKey(hotel.id);
            com.ascenda.hotels_data_merge.dto.Hotel dtoHotel = map(hotel, isMerge ? hotelMap.get(hotel.id) : com.ascenda.hotels_data_merge.dto.Hotel.initialize());
            if (!isMerge) {
                assert hotelMap != null;
                hotelMap.putIfAbsent(hotel.id, dtoHotel);
            }
        }
        cacheService.putCache(hotelMap);
    }

    private List<com.ascenda.hotels_data_merge.dto.Hotel> filter(List<String> hotelIds, Integer destinationId) {
        Collection<com.ascenda.hotels_data_merge.dto.Hotel> hotels = cacheService.getCache().values();

        if (hotelIds != null) {
            hotels = hotels.stream().filter(hotel -> hotelIds.contains(hotel.id)).toList();
        }

        if (destinationId != null) {
            hotels = hotels.stream().filter(hotel -> Objects.equals(hotel.destinationId, destinationId.toString())).toList();
        }
        return new ArrayList<>(hotels);
    }

    private com.ascenda.hotels_data_merge.dto.Hotel map(Hotel hotel, com.ascenda.hotels_data_merge.dto.Hotel dtoHotel) throws EnrichmentException {

        dtoHotel.id = hotel.id;
        dtoHotel.destinationId = hotel.destinationId;
        if (hotel.description != null && hotel.description.length() > dtoHotel.description.length())
            dtoHotel.description = hotel.description;
        if (hotel.name != null && hotel.name.length() > dtoHotel.name.length())
            dtoHotel.name = hotel.name;

        locationEnrichment.enrich(dtoHotel, hotel);
        amenitiesEnrichment.enrich(dtoHotel, hotel);
        bookingConditionsEnrichment.enrich(dtoHotel, hotel);
        imageEnrichment.enrich(dtoHotel, hotel);

        return dtoHotel;
    }

}
