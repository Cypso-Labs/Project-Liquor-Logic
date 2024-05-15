package com.liquorlogic.posservice.service;



import com.liquorlogic.posservice.entity.Order;
import com.liquorlogic.posservice.enums.OrderStatus;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface OrderService {
    Order saveOrder (Order order);
    List<Order> getAllOrder();
    boolean deleteOrder (Order orderId);
    Optional<Order> findByOrderId (UUID orderId);
    Order findByItemId (UUID itemId);
    Order findByUserId (UUID userId);
    Order findByStatus (OrderStatus status);
    Order findByCreateDate (Date createDate);
    Order findByUpdateDate (Date updateDate);
}
