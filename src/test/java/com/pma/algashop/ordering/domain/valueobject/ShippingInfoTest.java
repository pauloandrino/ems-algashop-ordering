package com.pma.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ShippingInfoTest {

    @Test
    void shouldCreateWithBuilder() {
        FullName fn = new FullName("John", "Doe");
        Document doc = new Document("123456789");
        Phone ph = new Phone("123456789");
        Address addr = Address.builder()
                .street("Street")
                .complement("Comp")
                .neighborhood("Neigh")
                .number("123")
                .city("City")
                .state("State")
                .zipCode(new ZipCode("12345"))
                .build();

        ShippingInfo si = ShippingInfo.builder()
                .fullName(fn)
                .document(doc)
                .phone(ph)
                .address(addr)
                .build();

        assertThat(si.fullName()).isEqualTo(fn);
        assertThat(si.document()).isEqualTo(doc);
        assertThat(si.phone()).isEqualTo(ph);
        assertThat(si.address()).isEqualTo(addr);
    }

    @Test
    void shouldThrowForNullFullName() {
        Document doc = new Document("123");
        Phone ph = new Phone("123");
        Address addr = Address.builder()
                .street("s")
                .neighborhood("n")
                .number("1")
                .city("c")
                .state("s")
                .zipCode(new ZipCode("12345"))
                .build();

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> ShippingInfo.builder()
                        .fullName(null)
                        .document(doc)
                        .phone(ph)
                        .address(addr)
                        .build());
    }

    @Test
    void shouldThrowForNullDocument() {
        FullName fn = new FullName("John", "Doe");
        Phone ph = new Phone("123");
        Address addr = Address.builder()
                .street("s")
                .neighborhood("n")
                .number("1")
                .city("c")
                .state("s")
                .zipCode(new ZipCode("12345"))
                .build();

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> ShippingInfo.builder()
                        .fullName(fn)
                        .document(null)
                        .phone(ph)
                        .address(addr)
                        .build());
    }

    @Test
    void shouldThrowForNullPhone() {
        FullName fn = new FullName("John", "Doe");
        Document doc = new Document("123");
        Address addr = Address.builder()
                .street("s")
                .neighborhood("n")
                .number("1")
                .city("c")
                .state("s")
                .zipCode(new ZipCode("12345"))
                .build();

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> ShippingInfo.builder()
                        .fullName(fn)
                        .document(doc)
                        .phone(null)
                        .address(addr)
                        .build());
    }

    @Test
    void shouldThrowForNullAddress() {
        FullName fn = new FullName("John", "Doe");
        Document doc = new Document("123");
        Phone ph = new Phone("123");

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> ShippingInfo.builder()
                        .fullName(fn)
                        .document(doc)
                        .phone(ph)
                        .address(null)
                        .build());
    }
}
