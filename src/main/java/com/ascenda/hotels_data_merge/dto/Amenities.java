package com.ascenda.hotels_data_merge.dto;

import lombok.Data;

import java.util.*;

@Data
public class Amenities {

    public Amenities() {
        this.general = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        this.room = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
    }

    public Set<String> general;
    public Set<String> room;
}
