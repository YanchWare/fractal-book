package com.yanchware.fractal.book.values;

public enum OwnerType {
    PERSONAL("Personal"),
    ORGANIZATIONAL("Organizational");

    private final String value;

    OwnerType(String value)
    {
        this.value = value;
    }
}
