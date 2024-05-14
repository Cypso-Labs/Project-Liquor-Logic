package com.liquorlogic.posservice.serviceImpl;


import com.liquorlogic.posservice.entity.Order;
import com.liquorlogic.posservice.enums.OrderStatus;
import com.liquorlogic.posservice.repository.OrderRepository;
import com.liquorlogic.posservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor

public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public boolean deleteOrder(Order orderId) {
        orderRepository.delete(orderId);
        return true;
    }

    @Override
    public Optional<Order> findByOrderId(UUID orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public Order findByCustomerId(UUID customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public Order findByItemId(UUID itemId) {
        return orderRepository.findByItemId(itemId);
    }

    @Override
    public Order findByEmployeeId(UUID employeeId) {
        return orderRepository.findByEmployeeId(employeeId);
    }

    @Override
    public Order findByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    @Override
    public Order findByCreateDate(Date createDate) {
        return orderRepository.findByCreateDate(createDate);
    }
}



