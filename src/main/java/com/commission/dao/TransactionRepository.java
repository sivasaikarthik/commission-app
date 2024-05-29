package com.commission.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commission.model.Order;
import com.commission.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String>{

}
