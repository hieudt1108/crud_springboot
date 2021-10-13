package com.example.demo.service;

import com.example.demo.entity.Orders;
import com.example.demo.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersSevice {
    private final OrdersRepository orderRepository;

    @Autowired
    public OrdersSevice(OrdersRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public String getTop1Id() {
        return orderRepository.getTopId();
    }

    public void saveOrder(Orders order) {
        orderRepository.save(order);
    }
}
