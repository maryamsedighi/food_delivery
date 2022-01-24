package com.myproject.stockApp.exception;

public class StockAppException extends Exception {
    private String message;
    private String code;

    public StockAppException(String message, String code) {
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

