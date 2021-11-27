package com.myproject.fooddelivery.exception;

public class FoodDeliveryException extends Exception {
    private String message;
    private String code;

    public FoodDeliveryException(String message, String code) {
        super(code + ":" + message);
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

