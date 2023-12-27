package com.ascenda.hotels_data_merge.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class Hotel {
    @JsonProperty
    @JsonAlias({"Id", "hotel_id"})
    public String id;
    @JsonProperty
    @JsonAlias({"destination_id", "destination", "DestinationId"})
    public String destinationId;
    @JsonProperty
    @JsonAlias({"Name", "hotel_name"})
    public String name;
    @JsonProperty
    @JsonAlias({"Description", "details"})
    public String description;
    public String info;
    @JsonProperty
    @JsonAlias({"Latitude", "lat"})
    public Double latitude;
    @JsonProperty
    @JsonAlias({"Longitude", "lng"})
    public Double longitude;
    @JsonProperty
    @JsonAlias({"Address"})
    public String address;
    @JsonProperty
    @JsonAlias({"City"})
    public String city;
    @JsonProperty
    @JsonAlias({"Country"})
    public String country;
    @JsonProperty
    @JsonAlias({"PostalCode"})
    public String postalCode;
    @JsonProperty
    @JsonAlias("Facilities")
    public List<String> facilities;
    public JsonNode amenities;
    public Images images;
    public Location location;
    @JsonProperty
    @JsonAlias("booking_conditions")
    public List<String> bookingConditions;

}
