package com.example.demo.service;

import com.example.demo.entity.Order_details;
import com.example.demo.repository.Order_detailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Order_detailsService {

    private final Order_detailsRepository order_detailsRepository;

    @Autowired
    public Order_detailsService(Order_detailsRepository order_detailsRepository) {
        this.order_detailsRepository = order_detailsRepository;
    }

    public void saveOrder_details(Order_details order_details){
        order_detailsRepository.save(order_details);
    }
}
