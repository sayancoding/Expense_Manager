package com.example.ExpenseService.enmus;

public enum Currency {
    INR("INR"),
    USD("USD"),
    EUR("EUR");

    private final String code;

    Currency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


}
