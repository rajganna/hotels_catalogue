package com.ascenda.hotels_data_merge.constants;

import java.util.concurrent.ConcurrentHashMap;

public enum ResponseType {

    /**
     * response type enums
     */
    SUCCESS(0, "response_success"),
    API_ERROR(9997, "API invocation error"),

    HYSTRIX_ERROR(9998, "hystrix error"),

    SYSTEM_ERROR(9999, "response_system_error");


    private int code;

    private String msg;

    ResponseType(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    private static final ConcurrentHashMap<Integer, ResponseType> responseTypeMap = new ConcurrentHashMap<>();

    static {
        for (ResponseType responseType : ResponseType.values()) {
            if (responseTypeMap.putIfAbsent(responseType.getCode(), responseType) != null) {
                throw new ExceptionInInitializerError("Has duplicate code in enums ResponseType!" + responseType.getCode() + "/" + responseType.getMsg());
            }
        }
    }

    public static ResponseType getResponseType(int code) {
        return responseTypeMap.get(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static String getMsg(int code) {
        ResponseType[] responseTypes = values();
        for (ResponseType responseType : responseTypes) {
            if (responseType.code == code) {
                return responseType.getMsg();
            }
        }
        return null;
    }
}
