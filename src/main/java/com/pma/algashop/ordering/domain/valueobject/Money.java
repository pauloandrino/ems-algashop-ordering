package com.pma.algashop.ordering.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public record Money(BigDecimal value) implements Comparable<Money> {

    public static final Money ZERO = new Money(BigDecimal.ZERO);

    public Money(BigDecimal value) {
        Objects.requireNonNull(value);
        BigDecimal bd = value.setScale(2, RoundingMode.HALF_EVEN);
        if (bd.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Money value cannot be negative");
        }
        this.value = bd;
    }

    public Money(String value) {
        this(new BigDecimal(value));
    }

    public Money multiply(Quantity quantity) {
        Objects.requireNonNull(quantity);
        if (quantity.value() < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }
        BigDecimal result = this.value.multiply(BigDecimal.valueOf(quantity.value()));
        return new Money(result);
    }

    public Money add(Money other) {
        Objects.requireNonNull(other);
        BigDecimal result = this.value.add(other.value);
        return new Money(result);
    }

    public Money divide(Money other) {
        Objects.requireNonNull(other);
        BigDecimal result = this.value.divide(other.value, 2, RoundingMode.HALF_EVEN);
        return new Money(result);
    }

    @Override
    public int compareTo(Money other) {
        Objects.requireNonNull(other);
        return this.value.compareTo(other.value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
