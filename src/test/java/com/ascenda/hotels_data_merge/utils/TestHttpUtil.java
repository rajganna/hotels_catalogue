package com.ascenda.hotels_data_merge.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class TestHttpUtil {

    @Spy
    HttpClient httpClient;

    @Test
    public void sendGet() throws URISyntaxException {
        String agodaURL = "http://www.agoda.com";
        given(httpClient.sendAsync(any(), any(HttpResponse.BodyHandlers.ofInputStream().getClass())))
                .willReturn(any());

        List<String> result = HttpUtil.sendGet(SuppliersUtil.getSuppliersURL(List.of(agodaURL)));

        assertEquals(result.get(0), "");
    }
}
