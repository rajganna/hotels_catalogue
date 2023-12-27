package com.ascenda.hotels_data_merge.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class SuppliersUtil {
    public static List<URI> getSuppliersURL(List<String> suppliers) throws URISyntaxException {
        List<URI> targets = new ArrayList<>();
        for (String supplier : suppliers) {
            targets.add(new URI(supplier));
        }
        return targets;
    }
}
