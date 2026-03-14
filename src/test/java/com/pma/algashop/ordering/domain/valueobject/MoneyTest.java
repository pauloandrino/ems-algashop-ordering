package com.pma.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class MoneyTest {

    @Test
    void shouldCreateWithValidString() {
        Money money = new Money("10.50");
        assertThat(money.value()).isEqualTo(new BigDecimal("10.50"));
    }

    @Test
    void shouldCreateWithValidBigDecimal() {
        Money money = new Money(new BigDecimal("10.50"));
        assertThat(money.value()).isEqualTo(new BigDecimal("10.50"));
    }

    @Test
    void shouldThrowForNullString() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Money((String) null));
    }

    @Test
    void shouldThrowForNullBigDecimal() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Money((BigDecimal) null));
    }

    @Test
    void shouldThrowForNegativeString() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Money("-1.00"));
    }

    @Test
    void shouldThrowForNegativeBigDecimal() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Money(new BigDecimal("-1.00")));
    }

    @Test
    void shouldHaveZero() {
        assertThat(Money.ZERO.value()).isEqualTo(BigDecimal.ZERO.setScale(2));
    }

    @Test
    void shouldAdd() {
        Money m1 = new Money("10.00");
        Money m2 = new Money("5.50");
        Money result = m1.add(m2);
        assertThat(result.value()).isEqualTo(new BigDecimal("15.50"));
    }

    @Test
    void shouldDivide() {
        Money m1 = new Money("10.00");
        Money m2 = new Money("2.00");
        Money result = m1.divide(m2);
        assertThat(result.value()).isEqualTo(new BigDecimal("5.00"));
    }

    @Test
    void shouldMultiply() {
        Money m = new Money("10.00");
        Quantity q = new Quantity(2);
        Money result = m.multiply(q);
        assertThat(result.value()).isEqualTo(new BigDecimal("20.00"));
    }

    @Test
    void shouldThrowMultiplyForQuantityZero() {
        Money m = new Money("10.00");
        Quantity q = new Quantity(0);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> m.multiply(q));
    }

    @Test
    void shouldCompareTo() {
        Money m1 = new Money("10.00");
        Money m2 = new Money("5.00");
        assertThat(m1.compareTo(m2)).isPositive();
        assertThat(m2.compareTo(m1)).isNegative();
        assertThat(m1.compareTo(m1)).isZero();
    }

    @Test
    void shouldToString() {
        Money m = new Money("10.50");
        assertThat(m.toString()).isEqualTo("10.50");
    }
}
