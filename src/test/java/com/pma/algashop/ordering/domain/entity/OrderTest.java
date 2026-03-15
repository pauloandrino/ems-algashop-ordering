package com.pma.algashop.ordering.domain.entity;

import com.pma.algashop.ordering.domain.valueobject.id.CustomerId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void shouldGenerate(){
        Order order = Order.draft(new CustomerId());
    }

}