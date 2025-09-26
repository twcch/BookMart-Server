package io.twcch.bookmarkserver.service;

import io.twcch.bookmarkserver.dto.CreateOrderRequest;

public interface OrderService {

    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

}
