package io.twcch.bookmarkserver.service.impl;

import io.twcch.bookmarkserver.dao.OrderDao;
import io.twcch.bookmarkserver.dao.ProductDao;
import io.twcch.bookmarkserver.dto.BuyItem;
import io.twcch.bookmarkserver.dto.CreateOrderRequest;
import io.twcch.bookmarkserver.model.Order;
import io.twcch.bookmarkserver.model.OrderItem;
import io.twcch.bookmarkserver.model.Product;
import io.twcch.bookmarkserver.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    @Qualifier("orderDaoImpl")
    private OrderDao orderDao;

    @Autowired
    @Qualifier("productDaoImpl")
    private ProductDao productDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            // 計算訂單總金額
            int amount = product.getPrice() * buyItem.getQuantity();
            totalAmount += amount;

            // 轉換 BuyItem 為 OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }

        // 創建訂單
        Integer orderId = orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;

    }

    @Override
    public Order getOrderById(Integer orderId) {

        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;

    }

}
