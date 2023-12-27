package com.ascenda.hotels_data_merge.controller;

import com.ascenda.hotels_data_merge.dto.Hotel;
import com.ascenda.hotels_data_merge.dto.Response;
import com.ascenda.hotels_data_merge.services.HotelService;
import com.ascenda.hotels_data_merge.services.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/hotel")
public class HotelMergeController {

    private final Logger logger = LoggerFactory.getLogger(ImageService.class);

    private final HotelService hotelService;

    public HotelMergeController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @RequestMapping(value = "merge", method = RequestMethod.GET)
    public Response<List<Hotel>> merge(@RequestParam(name="hotelIds", required = false) List<@NotNull String> hotelIds, @RequestParam(name = "destinationId", required = false) Integer destinationId) {

        try {
            List<com.ascenda.hotels_data_merge.dto.Hotel> hotels = hotelService.merge(hotelIds, destinationId);
            return Response.typedSuccess(hotels);
        } catch (Exception exception) {
            logger.error("Error processing the request: {}", exception.getMessage(), exception);
            return Response.typedSystemError();
        }
    }
}

