package com.pma.algashop.ordering.domain.valueobject;

import java.time.LocalDate;
import java.util.Objects;

public record BirthDate(LocalDate value) {

    public BirthDate(LocalDate value) {
        Objects.requireNonNull(value);
        if (value.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException();
        }
        this.value = value;
    }

    public Integer age() {
        return LocalDate.now().getYear() - value.getYear();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
