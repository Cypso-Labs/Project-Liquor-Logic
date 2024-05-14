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
    Order findByCustomerId (UUID customerId);
    Order findByItemId (UUID itemId);
    Order findByEmployeeId (UUID employeeId);
    Order findByStatus (OrderStatus status);
    Order findByCreateDate (Date createDate);
}
