package com.ascenda.hotels_data_merge.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Location {
    @JsonProperty("lat")
    public Double latitude;
    @JsonProperty("lng")
    public Double longitude;
    @JsonProperty
    public String address;
    public String city;
    public String country;
}
