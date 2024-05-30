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
import com.commission.dto.NewOrderDto;
import com.commission.model.Affiliate;
import com.commission.model.Category;
import com.commission.model.Order;
import com.commission.model.OrderCommission;
import com.commission.model.Phase;
import com.commission.model.Transaction;
import com.commission.service.abstrac.OrderServiceInterface;


@Service
public class OrderService implements OrderServiceInterface {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	AffService affService;
	
	public List<Order> getAll() {
		return orderRepository.findAll();
	}

	@Override
	public Order createOrder(NewOrderDto orderDto) {
		return orderRepository.save(new Order(orderDto.getOrderId(),orderDto.getProductId(), orderDto.getAffiliatedId(),orderDto.getPrice(),orderDto.getCategory(),LocalDateTime.now(), Phase.CREATED));
	}

	@Override
	public String updateOrder(String orderid, Phase phase) {
		Order order = orderRepository.getById(orderid);
		affService.phaseExcutorFactory(order, phase);
		order.setPhase(phase);
		orderRepository.save(order);
		return null;
	}

}
