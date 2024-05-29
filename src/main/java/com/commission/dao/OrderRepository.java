package com.commission.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commission.model.Order;

public interface OrderRepository extends JpaRepository<Order, String>{

}
