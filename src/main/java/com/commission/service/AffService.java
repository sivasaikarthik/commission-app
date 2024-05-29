package com.commission.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commission.Strategy.FlatStategy;
import com.commission.Strategy.PercentageStartegy;
import com.commission.dao.AffiliateRepo;
import com.commission.model.Affiliate;
import com.commission.model.Order;
import com.commission.model.OrderCommission;
import com.commission.model.Phase;

import jakarta.transaction.Transactional;

@Service
public class AffService {
	
	@Autowired
	AffiliateRepo affirepo;
	
	@Autowired
	PercentageStartegy percentageStartegy;
	
	@Autowired
	FlatStategy flatStragy;
	
	public Optional<Affiliate> getAffiById(String id) {
		return affirepo.findById(id);
	}
	
	@Transactional
	public void phaseExcutorFactory(Order order, Phase phase) {
		if (phase == Phase.RETURN_PERIOD_EXPIRED) {
			Optional<Affiliate> affiliate = affirepo.findById(order.getAffiliatedId());
			Affiliate aff;
			if (affiliate.isEmpty()) {
				ArrayList<OrderCommission> commision = new ArrayList<>();
				commision.add(new OrderCommission(order.getOrderId(), flatStragy.getCommision()));
				 aff = new Affiliate(order.getAffiliatedId(), commision);
			} else {
				aff = affiliate.get();
				OrderCommission oc = new OrderCommission(order.getOrderId(), percentageStartegy.getCommision());
				ArrayList<OrderCommission> oac = aff.getOrderCommissions();
				oac.add(oc);
				aff.setOrderCommissions(oac);
			}
			
			// if orders commision sum is more than 100 then transaction will take place from here
			
			affirepo.save(aff);
		}
	}
}
