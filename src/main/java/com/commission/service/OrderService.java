package com.commission.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commission.dao.OrderRepository;
import com.commission.dto.OrderDto;
import com.commission.exceptions.OrderNotFoundException;
import com.commission.model.Order;
import com.commission.model.Phase;
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
	public Order createOrder(OrderDto orderDto) {
	    Order order = new Order(
	        orderDto.getOrderId(),
	        orderDto.getProductId(),
	        orderDto.getAffiliatedId(),
	        orderDto.getPrice(),
	        orderDto.getCategory(),
	        LocalDateTime.now(),
	        Phase.CREATED
	    );
	    return orderRepository.save(order);
	}


	@Override
	public String updateOrder(String orderId, Phase phase) throws OrderNotFoundException {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() ->new OrderNotFoundException("No order exists associate to this id" + orderId));
		affService.phaseExcutorFactory(order, phase);
		order.setPhase(phase);
		orderRepository.save(order);
		return "Order ID " + orderId + " updated successfully to phase " + phase;
	}

	@Override
	public OrderDto getOrderById(String id) throws OrderNotFoundException{
		return orderRepository.findById(id).
				map(order -> new OrderDto(
						order.getOrderId(), 
			            order.getProductId(), 
			            order.getAffiliatedId(), 
			            order.getPrice(), 
			            order.getCategory()
						))
				.orElseThrow(() -> new OrderNotFoundException("No order exists associate to this id" + id));
	}

}
