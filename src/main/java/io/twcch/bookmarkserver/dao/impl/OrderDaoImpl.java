package io.twcch.bookmarkserver.dao.impl;

import io.twcch.bookmarkserver.dao.OrderDao;
import io.twcch.bookmarkserver.model.Order;
import io.twcch.bookmarkserver.model.OrderItem;
import io.twcch.bookmarkserver.rowmapper.OrderItemRowMapper;
import io.twcch.bookmarkserver.rowmapper.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createOrder(Integer userId, Integer totalAmount) {

        String sql = "INSERT INTO orders (user_id, total_amount, created_date, last_modified_date) " +
                "VALUES (:userId, :totalAmount, :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("totalAmount", totalAmount);

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        namedParameterJdbcTemplate.update(sql, map);

        // 使用 SQLite 的 last_insert_rowid() 函數取得最後插入的主鍵
        String getIdSql = "SELECT last_insert_rowid()";
        Integer orderId = namedParameterJdbcTemplate.queryForObject(getIdSql, new HashMap<>(), Integer.class);

        return orderId;

    }

    @Override
    public void createOrderItems(Integer orderId, List<OrderItem> orderItemList) {

        String sql = "INSERT INTO order_item (order_id, product_id, quantity, amount) " +
                "VALUES (:orderId, :productId, :quantity, :amount)";

        MapSqlParameterSource[] mapSqlParameterSources = new MapSqlParameterSource[orderItemList.size()];

        for (int i = 0; i < orderItemList.size(); i++) {
            OrderItem orderItem = orderItemList.get(i);

            mapSqlParameterSources[i] = new MapSqlParameterSource();
            mapSqlParameterSources[i].addValue("orderId", orderId);
            mapSqlParameterSources[i].addValue("productId", orderItem.getProductId());
            mapSqlParameterSources[i].addValue("quantity", orderItem.getQuantity());
            mapSqlParameterSources[i].addValue("amount", orderItem.getAmount());
        }

        namedParameterJdbcTemplate.batchUpdate(sql, mapSqlParameterSources);

    }

    @Override
    public Order getOrderById(Integer orderId) {

        String sql = "SELECT order_id, user_id, total_amount, created_date, last_modified_date " +
                "FROM orders WHERE order_id = :orderId";

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        List<Order> query = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());

        if (!query.isEmpty()) {
            return query.get(0);
        }

        return null;

    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(Integer orderId) {

        String sql = "SELECT oi.order_item_id, oi.order_id, oi.product_id, oi.quantity, oi.amount, " +
                "p.product_name, p.image_url " +
                "FROM order_item AS oi " +
                "JOIN products AS p ON oi.product_id = p.product_id " +
                "WHERE oi.order_id = :orderId";

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        List<OrderItem> orderItemList = namedParameterJdbcTemplate.query(sql, map, new OrderItemRowMapper());

        return orderItemList;

    }

}
