package com.commission.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.commission.model.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long>{
	@Query("SELECT t FROM Transaction t WHERE t.affiliateId = :affId")
	List<Transaction> getTransactionsByAffiliateId(String affId);
}
