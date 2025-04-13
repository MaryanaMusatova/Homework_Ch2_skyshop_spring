package org.skypro.skyshop.model.exceptions;

public class ShopError {
    private final String code;
    private final String message;


    public ShopError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // Геттеры (обязательно для Jackson)
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}