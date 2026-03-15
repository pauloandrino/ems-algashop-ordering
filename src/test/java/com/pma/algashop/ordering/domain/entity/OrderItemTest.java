package com.pma.algashop.ordering.domain.entity;

import com.pma.algashop.ordering.domain.valueobject.Money;
import com.pma.algashop.ordering.domain.valueobject.ProductName;
import com.pma.algashop.ordering.domain.valueobject.Quantity;
import com.pma.algashop.ordering.domain.valueobject.id.OrderId;
import com.pma.algashop.ordering.domain.valueobject.id.ProductId;
import org.junit.jupiter.api.Test;

class OrderItemTest {

    @Test
    void shouldGenerate(){
        OrderItem orderItem = OrderItem.brandNew()
                .orderId(new OrderId())
                .productId(new ProductId())
                .productName(new ProductName("Test Product"))
                .price(new Money("100"))
                .quantity(new Quantity(2))
                .build();
    }


}