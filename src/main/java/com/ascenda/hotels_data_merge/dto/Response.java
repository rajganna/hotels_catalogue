package com.ascenda.hotels_data_merge.dto;

import com.ascenda.hotels_data_merge.constants.ResponseType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL, content = JsonInclude.Include.NON_NULL)
public class Response<T> {
    private int errorCode;
    private String errorMsg;
    private String token;
    private T data;
    private long tm = System.currentTimeMillis() / 1000;

    public Response() {
    }

    public Response(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Response(ResponseType responseType) {
        this.errorMsg = responseType.getMsg();
        this.errorCode = responseType.getCode();
    }

    public Response(ResponseType responseType, T data) {
        this.errorMsg = responseType.getMsg();
        this.errorCode = responseType.getCode();
        this.setData(data);
    }

    public Response(ResponseType responseType, T data, boolean isTrans) {
        if (isTrans) {
            this.errorMsg = responseType.getMsg();
        } else {
            this.errorMsg = responseType.getMsg();
        }
        this.errorCode = responseType.getCode();
        this.setData(data);
    }

    public Response(ResponseType responseType, boolean isTrans) {
        if (isTrans) {
            this.errorMsg = responseType.getMsg();
        } else {
            this.errorMsg = responseType.getMsg();
        }
        this.errorCode = responseType.getCode();
    }

    @SuppressWarnings("unchecked")
    public static <T> Response<T> buildSuccessResponse() {
        return new Response<>(ResponseType.SUCCESS);
    }

    public static <T> Response<T> typedSuccess() {
        return new Response<>(ResponseType.SUCCESS);
    }

    public static <T> Response<T> returnSpecificResponse(Response<?> response) {
        Response<T> res = new Response<>();
        res.setErrorCode(response.getErrorCode());
        res.setErrorMsg(response.getErrorMsg());
        return res;
    }

    public static <T> Response<T> typedSuccess(T data) {
        return new Response<>(ResponseType.SUCCESS, data);
    }

    public static <T> Response<T> buildSystemErrorResponse() {
        return new Response<>(ResponseType.SYSTEM_ERROR);
    }

    public static <T> Response<T> typedSystemError() {
        return new Response<>(ResponseType.SYSTEM_ERROR);
    }

    public static <T> Response<T> typedApiError() {
        return new Response<>(ResponseType.API_ERROR);
    }


    public static Response convertResponse(Response source, Response target) {
        source.setErrorCode(target.errorCode);
        source.setErrorMsg(target.errorMsg);
        source.setToken(target.token);
        return source;
    }

    public Response<T> convertTypedResponse(Response<?> target) {
        if (target == null) {
            target = Response.typedSystemError();
        }
        this.setErrorCode(target.errorCode);
        this.setErrorMsg(target.errorMsg);
        this.setToken(target.token);
        return this;
    }

    public void setResponseType(ResponseType responseType) {
        this.errorMsg = responseType.getMsg();
        this.errorCode = responseType.getCode();
    }

    public void setResponseType(ResponseType responseType, Object... args) {
        this.errorMsg = responseType.getMsg();
        this.errorCode = responseType.getCode();
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setErrorMsg(String errorMsg, Object... args) {
        this.errorMsg = errorMsg;
    }

    public void setErrorMsg(String errorMsg, boolean isTrans) {
        if (isTrans) {
            this.errorMsg = errorMsg;
        } else {
            this.errorMsg = errorMsg;
        }
    }

    public boolean isSuccess() {
        return this.errorCode == ResponseType.SUCCESS.getCode();
    }

    public boolean isFail() {
        return !isSuccess();
    }

    public void setResponseTypeWithApp(ResponseType responseType, String app) {
        this.errorMsg = responseType.getMsg();
        this.errorCode = responseType.getCode();
    }

    public void setResponseTypeWithApp(ResponseType responseType, String app, Object... args) {
        this.errorMsg = responseType.getMsg();
        this.errorCode = responseType.getCode();
    }

    public Response<T> convertTypedResponseWithApp(Response<?> target, String app) {
        if (target == null) {
            target = Response.typedSystemError();
        }
        this.setErrorCode(target.errorCode);
        this.setToken(target.token);
        return this;
    }
}
