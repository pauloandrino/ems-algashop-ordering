package com.pma.algashop.ordering.domain.entity;

import com.pma.algashop.ordering.domain.exceptions.OrderStatusCannotBeChangedException;
import com.pma.algashop.ordering.domain.valueobject.Money;
import com.pma.algashop.ordering.domain.valueobject.ProductName;
import com.pma.algashop.ordering.domain.valueobject.Quantity;
import com.pma.algashop.ordering.domain.valueobject.id.CustomerId;
import com.pma.algashop.ordering.domain.valueobject.id.ProductId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

class OrderTest {

    @Test
    void shouldGenerate(){
        Order order = Order.draft(new CustomerId());
    }

    @Test
    void shouldAddItem(){
        Order order = Order.draft(new CustomerId());
        ProductId productId = new ProductId();

        order.addItem(
                productId,
                new ProductName("Test Product"),
                new Money("10.0"),
                new Quantity(1)
        );

        Assertions.assertThat(order.items().size()).isEqualTo(1);

        OrderItem orderItem = order.items().iterator().next();

        Assertions.assertWith(orderItem,
                (i -> Assertions.assertThat(i.productId()).isNotNull()),
                (i -> Assertions.assertThat(i.productName()).isEqualTo(new ProductName("Test Product"))),
                (i -> Assertions.assertThat(i.productId()).isEqualTo(productId)),
                (i -> Assertions.assertThat(i.price()).isEqualTo(new Money("10.0"))),
                (i -> Assertions.assertThat(i.quantity()).isEqualTo(new Quantity(1)))
        );
    }

    @Test
    void shouldGenerateExceptionWhenTryToChangeItemsSet(){
        Order order = Order.draft(new CustomerId());
        ProductId productId = new ProductId();

        order.addItem(
                productId,
                new ProductName("Test Product"),
                new Money("10.0"),
                new Quantity(1)
        );

        Set<OrderItem> orderItems = order.items();

        Assertions.assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(orderItems::clear);
    }

    @Test
    void shouldCalculateTotals(){
        Order order = Order.draft(new CustomerId());
        ProductId productId = new ProductId();

        order.addItem(
                productId,
                new ProductName("Test Product"),
                new Money("10.0"),
                new Quantity(1)
        );

        order.addItem(
                productId,
                new ProductName("Mouse Pad"),
                new Money("50.0"),
                new Quantity(2)
        );

        Assertions.assertThat(order.totalAmount()).isEqualTo(new Money("110.0"));
    }

    @Test
    public void givenDraftOrder_whenPlace_shouldChangeToPlaced(){
        Order order = Order.draft(new CustomerId());
        order.placeOrder();
        Assertions.assertThat(order.isPlaced()).isTrue();
    }

    @Test
    public void givenPlacedOrder_whenTryToPlace_shouldGenerateException(){
        Order order = Order.draft(new CustomerId());
        order.placeOrder();
        Assertions.assertThatExceptionOfType(OrderStatusCannotBeChangedException.class)
                .isThrownBy(order::placeOrder);
    }
}