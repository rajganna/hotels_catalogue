package com.ascenda.hotels_data_merge.models;

import java.util.ArrayList;
import java.util.List;

public class Images {

    public Images() {
        this.rooms = new ArrayList<>();
        this.site = new ArrayList<>();
        this.amenities = new ArrayList<>();
    }

    public List<Image> rooms;
    public List<Image> amenities;
    public List<Image> site;
}
