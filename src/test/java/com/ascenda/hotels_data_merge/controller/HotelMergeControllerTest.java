package com.ascenda.hotels_data_merge.controller;

import com.ascenda.hotels_data_merge.services.HotelService;
import com.ascenda.hotels_data_merge.services.ImageService;
import com.ascenda.hotels_data_merge.services.LanguageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HotelMergeControllerTest {

    private static final String domain = "http://127.0.0.1";

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private LanguageService languageService;

    @Test
    public void merge() throws Exception {
        String response = performGet("/api/hotel/merge");
        assertTrue(response.contains("SjyX"));
    }

    @Test
    public void mergeWithHotelIdsAndDestinationIdFilter() throws Exception {
        String response = performGet("/api/hotel/merge?hotelIds=SjyX&destinationId=5432");
        assertTrue(response.contains("SjyX"));
    }

    @Test
    public void mergeWithHotelIdsFilter() throws Exception {
        String response = performGet("/api/hotel/merge?hotelIds=SjyX");
        assertTrue(response.contains("SjyX"));
    }

    @Test
    public void mergeWithDestinationIdFilter() throws Exception {
        String response = performGet("/api/hotel/merge?destinationId=5432");
        assertTrue(response.contains("SjyX"));
    }

    @Test
    public void mergeWithInvalidDestinationIdFilter() throws Exception {
        String response = performGet("/api/hotel/merge?destinationId=54324");
        assertFalse(response.contains("SjyX"));
    }

    @Test
    public void mergeWithoutHotelIdsFilter() throws Exception {
        String response = performGet("/api/hotel/merge?hotelIds=SjyXs");
        assertFalse(response.contains("SjyX"));
    }

    private String performGet(String url) throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get(String.format("%s:%s%s", domain, port, url))
        ).andExpect(status().isOk()).andReturn();

        return mvcResult.getResponse().getContentAsString();
    }



}
