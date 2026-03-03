package com.pma.algashop.ordering.domain.valueobject;

import java.io.Serializable;
import java.util.Objects;

public record FullName(String firstName, String lastName) implements Serializable {

    public FullName(String firstName, String lastName) {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);

        if (firstName.isBlank()) {
            throw new IllegalArgumentException();
        }

        if (lastName.isBlank()) {
            throw new IllegalArgumentException();
        }

        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
