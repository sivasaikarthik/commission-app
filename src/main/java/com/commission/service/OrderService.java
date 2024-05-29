package com.commission.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.tools.cache.FlatFileCacheBacking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commission.Strategy.FlatStategy;
import com.commission.Strategy.PercentageStartegy;
import com.commission.dao.AffiliateRepo;
import com.commission.dao.OrderRepository;
import com.commission.dao.TransactionRepository;
import com.commission.model.Affiliate;
import com.commission.model.Category;
import com.commission.model.Order;
import com.commission.model.OrderCommission;
import com.commission.model.Phase;
import com.commission.model.Transaction;
import com.commission.service.abstrac.OrderServiceInterface;

@Service
public class OrderService implements OrderServiceInterface{

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	AffiliateRepo affiliateRepo;
	
	@Autowired
	AffService affService;
	
	@Autowired
	PercentageStartegy percentageStartegy;
	
	@Autowired
	FlatStategy flatStragy;
	public void phaseExcutorFactory(Order order, Phase phase) {
		if (phase == Phase.RETURN_PERIOD_EXPIRED) {
			Optional<Affiliate> affiliate = affiliateRepo.findById(order.getAffiliatedId());
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
			affiliateRepo.save(aff);
		}
	}

	
	@Override
	public Order createOrder(Order order) {
		// TODO Auto-generated method stub
//		Order order = new Order(orderId, productId,affiliatedId,price,category,timeStampmm,phase);
		return orderRepository.save(order);
		
	}

	@Override
	public String updateOrder(String orderid, Phase phase) {
		// TODO Auto-generated method stub
		
		Order order = orderRepository.getById(orderid);
		affService.phaseExcutorFactory(order, phase);
		order.setPhase(phase);
		orderRepository.save(order);
		return null;
	}
	
	
	
}
