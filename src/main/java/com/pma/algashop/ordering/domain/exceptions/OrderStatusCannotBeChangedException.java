package com.pma.algashop.ordering.domain.exceptions;

import com.pma.algashop.ordering.domain.entity.OrderStatus;
import com.pma.algashop.ordering.domain.valueobject.id.OrderId;

import static com.pma.algashop.ordering.domain.exceptions.ErrorMessages.ERROR_ORDER_STATUS_CANNOT_BE_CHANGED;

public class OrderStatusCannotBeChangedException extends DomainException {

    public OrderStatusCannotBeChangedException(OrderId id, OrderStatus status, OrderStatus newStatus) {
      super(String.format(ERROR_ORDER_STATUS_CANNOT_BE_CHANGED, id, status, newStatus));
    }
}
