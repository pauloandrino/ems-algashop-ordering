package com.pma.algashop.ordering.domain.valueobject;

import java.util.Objects;

public record ProductName(String value) {

    public ProductName {
        Objects.requireNonNull(value);
        if (value.isBlank()) {
            throw new IllegalArgumentException("ProductName cannot be blank");
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
