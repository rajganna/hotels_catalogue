package com.ascenda.hotels_data_merge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Pricing {
    @JsonProperty("id")
    public String hotelId;
    @JsonProperty("price")
    public Double price;
}
