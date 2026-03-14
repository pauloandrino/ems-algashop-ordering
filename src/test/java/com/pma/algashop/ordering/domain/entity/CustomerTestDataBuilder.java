package com.pma.algashop.ordering.domain.entity;

import com.pma.algashop.ordering.domain.valueobject.Address;
import com.pma.algashop.ordering.domain.valueobject.id.CustomerId;
import com.pma.algashop.ordering.domain.valueobject.Document;
import com.pma.algashop.ordering.domain.valueobject.Email;
import com.pma.algashop.ordering.domain.valueobject.FullName;
import com.pma.algashop.ordering.domain.valueobject.LoyaltyPoints;
import com.pma.algashop.ordering.domain.valueobject.Phone;
import com.pma.algashop.ordering.domain.valueobject.ZipCode;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public class CustomerTestDataBuilder {

    private CustomerTestDataBuilder() {
    }

    public static Customer.BrandNewCustomerBuilder brandNewCustomerBuilder() {
        return Customer.brandNew()
                .fullName(new FullName("John", "Doe"))
                .birthDate(LocalDate.of(1991, 7, 5))
                .email(new Email("john.doe@gmail.com"))
                .phone(new Phone("478-555-6544"))
                .document(new Document("255-55-3211"))
                .promotionNotificationsAllowed(false)
                .address(Address.builder()
                        .street("Main St")
                        .number("12345")
                        .neighborhood("Downtown")
                        .city("Springfield")
                        .state("California")
                        .zipCode(ZipCode.builder().value("12345").build())
                        .complement("Apt 101")
                        .build()
                );
    }

    public static Customer.ExistingCustomerBuilder existingCustomerBuilder() {
        return Customer.existing()
                .id(new CustomerId())
                .fullName(new FullName("John", "Doe"))
                .birthDate(null)
                .email(new Email("john.doe@email.com"))
                .phone(new Phone("321-446-6054"))
                .document(new Document("446-843-1265"))
                .promotionNotificationsAllowed(true)
                .archived(false)
                .registeredAt(OffsetDateTime.now())
                .archivedAt(null)
                .loyaltyPoints(new LoyaltyPoints(10))
                .address(Address.builder()
                        .street("Main St")
                        .number("12345")
                        .neighborhood("Downtown")
                        .city("Springfield")
                        .state("California")
                        .zipCode(ZipCode.builder().value("12345").build())
                        .complement("Apt 101")
                        .build());
    }

    public static Customer.ExistingCustomerBuilder existingAnonymizedCustomerBuilder() {
        return Customer.existing()
                .id(new CustomerId())
                .fullName(new FullName("Anonymous", "Anonymous"))
                .birthDate(null)
                .email(new Email("anonymous@anonymous.com"))
                .phone(new Phone("000-000-0000"))
                .document(new Document("000-000-0000"))
                .promotionNotificationsAllowed(true)
                .archived(true)
                .registeredAt(OffsetDateTime.now())
                .archivedAt(OffsetDateTime.now())
                .loyaltyPoints(new LoyaltyPoints(10))
                .address(Address.builder()
                        .street("Main St")
                        .number("12345")
                        .neighborhood("Downtown")
                        .city("Springfield")
                        .state("California")
                        .zipCode(ZipCode.builder().value("12345").build())
                        .complement("Apt 101")
                        .build());
    }
}
