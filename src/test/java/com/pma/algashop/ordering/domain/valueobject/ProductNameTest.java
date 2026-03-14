package com.pma.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ProductNameTest {

    @Test
    void shouldCreateWithValidString() {
        ProductName pn = new ProductName("Product Name");
        assertThat(pn.value()).isEqualTo("Product Name");
    }

    @Test
    void shouldThrowForNull() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new ProductName(null));
    }

    @Test
    void shouldThrowForBlank() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new ProductName(""));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new ProductName("   "));
    }

    @Test
    void shouldToString() {
        ProductName pn = new ProductName("Product");
        assertThat(pn.toString()).isEqualTo("Product");
    }
}
