package com.ascenda.hotels_data_merge.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Image {
    @JsonProperty
    @JsonAlias("link")
    public String url;
    @JsonProperty
    @JsonAlias("caption")
    public String description;
}
