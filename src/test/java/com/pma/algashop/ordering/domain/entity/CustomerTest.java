package com.pma.algashop.ordering.domain.entity;

import com.pma.algashop.ordering.domain.exceptions.CustomerArchivedException;
import com.pma.algashop.ordering.domain.utility.IdGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerTest {

    @Test
    void given_invalidEmail_whenTryCreateCustomer_shouldGenerateException() {

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    Customer customer = new Customer(
                            IdGenerator.generateTimeBasedUUID(),
                            "John Doe",
                            LocalDate.of(1991, 7, 5),
                            "invalid-email",
                            "478-555-6544",
                            "255-55-3211",
                            false,
                            OffsetDateTime.now()
                    );
                });
    }

    @Test
    void given_invalidEmail_whenTryUpdateCustomer_shouldGenerateException() {

        Customer customer = new Customer(
                IdGenerator.generateTimeBasedUUID(),
                "John Doe",
                LocalDate.of(1991, 7, 5),
                "john.doe@gmail.com",
                "478-555-6544",
                "255-55-3211",
                false,
                OffsetDateTime.now()
        );

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    customer.changeEmail("invalid-email");
                });
    }

    @Test
    void given_unarchivedCustomer_whenArchive_shouldAnonymize() {

        Customer customer = new Customer(
                IdGenerator.generateTimeBasedUUID(),
                "John Doe",
                LocalDate.of(1991, 7, 5),
                "john.doe@gmail.com",
                "478-555-6544",
                "255-55-3211",
                false,
                OffsetDateTime.now()
        );

        customer.archived();

        Assertions.assertWith(customer,
                c -> assertThat(c.fullName()).isEqualTo("Anonymous"),
                c -> assertThat(c.email()).isNotEqualTo("john.doe@gmail.com"),
                c -> assertThat(c.phone()).isEqualTo("000-000-0000"),
                c -> assertThat(c.document()).isEqualTo("000-000-0000"),
                c -> assertThat(c.birthDate()).isNull(),
                c -> assertThat(c.isPromotionNotificationsAllowed()).isFalse()
        );
    }

    @Test
    void given_archivedCustomer_whenTryToUpdate_shouldGenerateException() {
        Customer customer = new Customer(
                IdGenerator.generateTimeBasedUUID(),
                "Anonymous",
                null,
                "anonymous@anonymous.com",
                "000-000-0000",
                "000-000-0000",
                true,
                true,
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                10);

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(customer::archived);

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(() -> customer.changeEmail("email@email.com"));

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(() -> customer.changePhone("123-123-1111"));

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(customer::enablePromotionNotifications);

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(customer::disablePromotionNotifications);

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(() -> customer.changeName("John Doe"));
    }

}