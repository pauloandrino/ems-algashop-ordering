package com.pma.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class QuantityTest {

    @Test
    void shouldCreateWithValidValue() {
        Quantity q = new Quantity(5);
        assertThat(q.value()).isEqualTo(5);
    }

    @Test
    void shouldCreateWithZero() {
        Quantity q = new Quantity(0);
        assertThat(q.value()).isEqualTo(0);
    }

    @Test
    void shouldThrowForNegative() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Quantity(-1));
    }

    @Test
    void shouldHaveZero() {
        assertThat(Quantity.ZERO.value()).isEqualTo(0);
    }

    @Test
    void shouldAdd() {
        Quantity q1 = new Quantity(5);
        Quantity q2 = new Quantity(3);
        Quantity result = q1.add(q2);
        assertThat(result.value()).isEqualTo(8);
    }

    @Test
    void shouldCompareTo() {
        Quantity q1 = new Quantity(5);
        Quantity q2 = new Quantity(3);
        assertThat(q1.compareTo(q2)).isPositive();
        assertThat(q2.compareTo(q1)).isNegative();
        assertThat(q1.compareTo(q1)).isZero();
    }

    @Test
    void shouldToString() {
        Quantity q = new Quantity(5);
        assertThat(q.toString()).isEqualTo("5");
    }
}
