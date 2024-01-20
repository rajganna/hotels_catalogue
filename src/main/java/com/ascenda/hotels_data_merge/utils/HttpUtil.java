package com.ascenda.hotels_data_merge.utils;

import com.ascenda.hotels_data_merge.dto.Pricing;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static List<String> sendGet(List<URI> targets) {
        HttpClient client = HttpClient.newHttpClient();

        try {
            List<CompletableFuture<String>> futures = targets.stream()
                    .map(target -> client
                            .sendAsync(
                                    HttpRequest.newBuilder(target).GET().build(),
                                    HttpResponse.BodyHandlers.ofString())
                            .thenApply(HttpResponse::body))
                    .toList();
            return futures.stream().map(CompletableFuture::join).toList();
        } catch (Exception ex) {
            logger.error(String.format("Exception during calling of Jacquard: %s",
                    Throwables.getStackTraceAsString(ex)));
            throw ex;
        }
    }

    public static List<Pricing> sendPricingGet(String target) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(HttpRequest.newBuilder(new URI(target)).GET().build(),
                HttpResponse.BodyHandlers.ofString());

        return ObjectMapperUtil.getObjectMapper().readValue(response.body().toString(), new TypeReference<>() {});
    }
}
