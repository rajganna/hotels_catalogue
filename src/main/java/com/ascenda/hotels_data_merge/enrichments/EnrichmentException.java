package com.ascenda.hotels_data_merge.enrichments;

public class EnrichmentException extends Exception {
    public EnrichmentException(String message, Exception exception) {
        super(message, exception);
    }
}
