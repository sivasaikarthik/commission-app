package com.commission.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.commission.model.Commission;
import com.commission.model.OrderAffiliateKey;
import com.commission.model.Status;

import jakarta.transaction.Transactional;

@Repository
public interface CommisionRepo extends JpaRepository<Commission, OrderAffiliateKey> {

	@Query("SELECT SUM(c.amount) FROM Commission c " + "WHERE c.status = :status "
			+ "AND c.id.affiliateId = :affId")
	Double sumAmountByPendingStatusAndAffiliateId(@Param("affId") String affId,
			@Param("status") Status status);

	@Transactional
	@Modifying
	@Query("UPDATE Commission c SET c.status = :newStatus " 
	        + "WHERE c.status = :oldStatus "
	        + "AND c.id.affiliateId = :affiliateId " 
	        + "AND  (:orderId IS NULL OR c.id.orderId = :orderId)")
	void updateState(@Param("orderId") String orderId, 
	                 @Param("affiliateId") String affiliateId,
	                 @Param("oldStatus") Status oldStatus, 
	                 @Param("newStatus") Status newStatus);

	@Query("SELECT c FROM Commission c WHERE c.id.affiliateId = :affiliateId"
			+ " and (:status IS NULL OR c.status = :status)")
    List<Commission> findByAffiliateId(@Param("affiliateId") String affiliateId, @Param("status") Status status);
}

