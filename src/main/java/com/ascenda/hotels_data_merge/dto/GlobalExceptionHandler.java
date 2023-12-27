package com.ascenda.hotels_data_merge.dto;

import com.ascenda.hotels_data_merge.constants.ResponseType;
import com.ascenda.hotels_data_merge.enrichments.EnrichmentException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({EnrichmentException.class})
    public Response<String> handleEnrichmentException(EnrichmentException exception) {
        Response<String> response = new Response<>();
        response.setErrorCode(ResponseType.API_ERROR.getCode());
        return response;
    }
    @ExceptionHandler({RuntimeException.class})
    public Response<String> handleRuntimeException(RuntimeException exception) {
        Response<String> response = new Response<>();
        response.setErrorCode(ResponseType.SYSTEM_ERROR.getCode());
        return response;
    }
}
