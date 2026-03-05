package com.pma.algashop.ordering.domain.valueobject;

import lombok.Builder;

import java.util.Objects;

public record ZipCode(String value) {

    @Builder
    public ZipCode {
        Objects.requireNonNull(value);
        if (value.isBlank()) {
            throw new IllegalArgumentException();
        }
        if (value.length() != 5) {
            throw  new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
