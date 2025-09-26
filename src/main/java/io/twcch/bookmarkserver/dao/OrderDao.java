package io.twcch.bookmarkserver.dao;

import io.twcch.bookmarkserver.model.Order;
import io.twcch.bookmarkserver.model.OrderItem;

import java.util.List;

public interface OrderDao {

    public Integer createOrder(Integer userId, Integer totalAmount);

    public void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

    public Order getOrderById(Integer orderId);

    public List<OrderItem> getOrderItemsByOrderId(Integer orderId);

}
