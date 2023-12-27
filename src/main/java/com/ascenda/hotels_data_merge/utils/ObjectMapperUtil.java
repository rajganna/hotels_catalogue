package com.ascenda.hotels_data_merge.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperUtil {
    private static ObjectMapper initializedObjectMapper;

    /**
     * Bootstraps an object mapper as initialized by the main microservice
     * applicaiton class.
     *
     * @return properly initialized object mapper for manual use which matches
     *      the one used by the microservice application
     */
    public static ObjectMapper getObjectMapper() {
        if (initializedObjectMapper == null) {
            initializedObjectMapper = new ObjectMapper();
            initializedObjectMapper
                    .registerModule(new JavaTimeModule())
                    .enable(SerializationFeature.INDENT_OUTPUT)
                    .enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
                    .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                    .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
        }

        return initializedObjectMapper;
    }
}
