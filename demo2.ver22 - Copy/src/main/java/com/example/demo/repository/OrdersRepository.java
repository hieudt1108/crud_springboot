package com.example.demo.repository;

import com.example.demo.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Integer> {

    @Query(value = "SELECT TOP(1) id FROM Order ORDER BY id DESC", nativeQuery = true)
    String getTopId();


}
