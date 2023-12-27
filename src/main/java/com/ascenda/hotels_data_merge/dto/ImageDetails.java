package com.ascenda.hotels_data_merge.dto;

import lombok.Data;

@Data
public class ImageDetails {
    public String link;
    public String description;

    public ImageDetails(String link, String description) {
        this.link = link;
        this.description = description;
    }
}
