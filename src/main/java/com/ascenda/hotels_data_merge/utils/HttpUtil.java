package com.ascenda.hotels_data_merge.utils;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
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
}
