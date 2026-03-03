package com.pma.algashop.ordering.domain.valueobject;

import java.io.Serializable;
import java.util.Objects;

public record LoyaltyPoints(Integer value) implements Comparable<LoyaltyPoints>, Serializable {

    public static final LoyaltyPoints ZERO = new LoyaltyPoints(0);

    public LoyaltyPoints() {
        this(0);
    }

    public LoyaltyPoints(Integer value) {
        if (value < 0) {
            throw new IllegalArgumentException();
        }
        this.value = value;
    }

    public LoyaltyPoints add(Integer value) {
        return add(new LoyaltyPoints(value));
    }

    public LoyaltyPoints add(LoyaltyPoints loyaltyPoints) {
        Objects.requireNonNull(loyaltyPoints);
        if (loyaltyPoints.value() <= 0) {
            throw new IllegalArgumentException();
        }
        return new LoyaltyPoints(this.value + loyaltyPoints.value);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public int compareTo(LoyaltyPoints o) {
        return this.value().compareTo(o.value);
    }
}
