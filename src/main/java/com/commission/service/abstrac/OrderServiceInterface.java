package com.commission.service.abstrac;

import com.commission.dto.OrderDto;
import com.commission.exceptions.OrderNotFoundException;
import com.commission.model.Order;
import com.commission.model.Phase;

public interface OrderServiceInterface {
	Order createOrder(OrderDto orderDto);
	OrderDto getOrderById(String id) throws OrderNotFoundException;
	String updateOrder(String orderId, Phase phase) throws OrderNotFoundException;
}