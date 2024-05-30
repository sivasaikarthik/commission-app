package com.commission.service.abstrac;

import com.commission.dto.NewOrderDto;
import com.commission.model.Order;
import com.commission.model.Phase;

public interface OrderServiceInterface {
	Order createOrder(NewOrderDto orderDto);
	String updateOrder(String orderId, Phase phase);
}
