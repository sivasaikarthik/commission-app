package com.commission.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.commission.model.Affiliate;
import com.commission.model.Order;

@Service
public interface AffiliateRepo  extends JpaRepository<Affiliate, String>{


}
