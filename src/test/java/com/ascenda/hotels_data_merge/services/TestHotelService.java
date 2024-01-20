package com.ascenda.hotels_data_merge.services;

import com.ascenda.hotels_data_merge.dto.Hotel;
import com.ascenda.hotels_data_merge.enrichments.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class TestHotelService {

    @Mock
    CacheService cacheService;

    @Mock
    LocationEnrichment locationEnrichment;

    @Mock
    ImageEnrichment imageEnrichment;

    @Mock
    BookingConditionsEnrichment bookingConditionsEnrichment;

    @Mock
    AmenitiesEnrichment amenitiesEnrichment;

    @InjectMocks
    HotelService hotelService;
    @Test
    public void mergeWithCache() throws EnrichmentException, IOException, URISyntaxException, InterruptedException {
        Map<String, Hotel> hotelMap = new HashMap<>();
        Hotel hotel = new Hotel();
        hotel.id = "test";
        hotel.destinationId = String.valueOf(123);
        hotelMap.putIfAbsent("test", hotel);
        given(cacheService.getHotelCache()).willReturn(hotelMap);
        List<com.ascenda.hotels_data_merge.dto.Hotel> hotels = hotelService.merge(List.of("test"), 123);
        assertFalse(hotels.isEmpty());
    }

}
