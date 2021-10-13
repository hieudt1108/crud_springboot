package com.example.demo.repository;

import com.example.demo.entity.Category;
import com.example.demo.entity.Order_details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Order_detailsRepository extends JpaRepository<Order_details,Integer> {
}
