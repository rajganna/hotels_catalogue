package com.ascenda.hotels_data_merge.enrichments;

import com.ascenda.hotels_data_merge.dto.Hotel;

public interface Enricher {
    void enrich(Hotel dtoHotel, com.ascenda.hotels_data_merge.models.Hotel hotel) throws EnrichmentException;
}
