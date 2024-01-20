package com.ascenda.hotels_data_merge.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Hotel {
    @JsonProperty
    public String id;
    @JsonProperty("destination_id")
    public String destinationId;
    @JsonProperty
    public String name;
    @JsonProperty
    public String description;
    @JsonProperty("booking_conditions")
    public List<String> bookingConditions;
    public Amenities amenities;
    public Images images;
    public Location location;
    public Double price;

    public static Hotel initialize() {
        com.ascenda.hotels_data_merge.dto.Hotel hotel = new com.ascenda.hotels_data_merge.dto.Hotel();
        hotel.description = StringUtils.EMPTY;
        hotel.name = StringUtils.EMPTY;
        return hotel;
    }
}
