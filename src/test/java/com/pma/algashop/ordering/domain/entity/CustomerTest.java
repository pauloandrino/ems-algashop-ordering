package com.pma.algashop.ordering.domain.entity;

import com.pma.algashop.ordering.domain.exceptions.CustomerArchivedException;
import com.pma.algashop.ordering.domain.valueobject.CustomerId;
import com.pma.algashop.ordering.domain.valueobject.Document;
import com.pma.algashop.ordering.domain.valueobject.Email;
import com.pma.algashop.ordering.domain.valueobject.FullName;
import com.pma.algashop.ordering.domain.valueobject.LoyaltyPoints;
import com.pma.algashop.ordering.domain.valueobject.Phone;
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
                            new CustomerId(),
                            new FullName("John", "Doe"),
                            LocalDate.of(1991, 7, 5),
                            new Email("invalid-email"),
                            new Phone("478-555-6544"),
                            new Document("255-55-3211"),
                            false,
                            OffsetDateTime.now()
                    );
                });
    }

    @Test
    void given_invalidEmail_whenTryUpdateCustomer_shouldGenerateException() {

        Customer customer = new Customer(
                new CustomerId(),
                new FullName("John", "Doe"),
                LocalDate.of(1991, 7, 5),
                new Email("john.doe@gmail.com"),
                new Phone("478-555-6544"),
                new Document("255-55-3211"),
                false,
                OffsetDateTime.now()
        );

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    customer.changeEmail(new Email("invalid-email"));
                });
    }

    @Test
    void given_unarchivedCustomer_whenArchive_shouldAnonymize() {

        Customer customer = new Customer(
                new CustomerId(),
                new FullName("John", "Doe"),
                LocalDate.of(1991, 7, 5),
                new Email("john.doe@gmail.com"),
                new Phone("478-555-6544"),
                new Document("255-55-3211"),
                false,
                OffsetDateTime.now()
        );

        customer.archived();

        Assertions.assertWith(customer,
                c -> assertThat(c.fullName()).isEqualTo(new FullName("Anonymous", "Anonymous")),
                c -> assertThat(c.email()).isNotEqualTo(new Email("john.doe@gmail.com")),
                c -> assertThat(c.phone()).isEqualTo(new Phone("000-000-0000")),
                c -> assertThat(c.document()).isEqualTo(new Document("000-000-0000")),
                c -> assertThat(c.birthDate()).isNull(),
                c -> assertThat(c.isPromotionNotificationsAllowed()).isFalse()
        );
    }

    @Test
    void given_archivedCustomer_whenTryToUpdate_shouldGenerateException() {
        Customer customer = new Customer(
                new CustomerId(),
                new FullName("Anonymous", "Anonymous"),
                null,
                new Email("anonymous@anonymous.com"),
                new Phone("000-000-0000"),
                new Document("000-000-0000"),
                true,
                true,
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                new LoyaltyPoints(10));

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(customer::archived);

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(() -> customer.changeEmail(new Email("email@email.com")));

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(() -> customer.changePhone(new Phone("123-123-1111")));

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(customer::enablePromotionNotifications);

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(customer::disablePromotionNotifications);

        Assertions.assertThatExceptionOfType(CustomerArchivedException.class)
                .isThrownBy(() -> customer.changeName(new FullName("John", "Doe")));
    }


    @Test
    void given_brandNewCustomer_whenAddLoyaltyPoints_shouldSumPoints() {

        Customer customer = new Customer(
                new CustomerId(),
                new FullName("John", "Doe"),
                LocalDate.of(1991, 7, 5),
                new Email("john.doe@gmail.com"),
                new Phone("478-555-6544"),
                new Document("255-55-3211"),
                false,
                OffsetDateTime.now()
        );

        customer.addLoyaltyPoints(new LoyaltyPoints(10));
        customer.addLoyaltyPoints(new LoyaltyPoints(20));

        Assertions.assertThat(customer.loyaltyPoints()).isEqualTo(new LoyaltyPoints(30));
    }

    @Test
    void given_brandNewCustomer_whenAddInvalidLoyaltyPoints_shouldGenerateException() {

        Customer customer = new Customer(
                new CustomerId(),
                new FullName("John", "Doe"),
                LocalDate.of(1991, 7, 5),
                new Email("john.doe@gmail.com"),
                new Phone("478-555-6544"),
                new Document("255-55-3211"),
                false,
                OffsetDateTime.now()
        );

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> customer.addLoyaltyPoints(LoyaltyPoints.ZERO));

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> customer.addLoyaltyPoints(new LoyaltyPoints(-10)));
    }

}