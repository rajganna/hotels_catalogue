package com.ascenda.hotels_data_merge.utils;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSuppersUtil {

    @Test
    public void getSuppliersUtil() throws URISyntaxException {
        String agodaURL = "http://www.agoda.com";
        List<String> suppliers = List.of(agodaURL);
        List<URI> uris = SuppliersUtil.getSuppliersURL(suppliers);
        assertEquals(uris.get(0), new URI(agodaURL));
    }

}
