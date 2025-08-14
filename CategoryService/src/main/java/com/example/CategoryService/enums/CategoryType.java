package com.example.CategoryService.enums;

public enum CategoryType {
    EXPENSE("EXPENSE"), INCOME("INCOME");
    private final String type;

    CategoryType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }


}
