package com.ascenda.hotels_data_merge.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Images {

    public Images() {
        this.amenities = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.site = new ArrayList<>();
    }

    public List<ImageDetails> rooms;
    public List<ImageDetails> site;
    public List<ImageDetails> amenities;

}
