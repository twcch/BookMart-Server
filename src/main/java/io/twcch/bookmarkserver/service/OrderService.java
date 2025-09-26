package io.twcch.bookmarkserver.service;

import io.twcch.bookmarkserver.dto.CreateOrderRequest;
import io.twcch.bookmarkserver.model.Order;

public interface OrderService {

    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    public Order getOrderById(Integer orderId);

}
