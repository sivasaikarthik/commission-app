package com.commission.service.abstrac;

import java.time.LocalDateTime;

import com.commission.model.Category;
import com.commission.model.Order;
import com.commission.model.Phase;

public interface OrderServiceInterface {
	public Order createOrder(Order order);
	public String updateOrder(String orderid, Phase phase);
}
