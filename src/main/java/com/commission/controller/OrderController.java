package com.commission.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.commission.model.Category;
import com.commission.model.Order;
import com.commission.model.Phase;
import com.commission.service.OrderService;
import com.commission.service.abstrac.OrderServiceInterface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService ;
	
	@PostMapping("/createOrder")
	public String processOrder(@RequestBody Order order) {
		orderService.createOrder(order);
		return "order" + order.getPhase() + "sucess";
	}
	
	@PostMapping("/updateOrder")
	public String processOrder(@RequestBody Dto dto) {
		orderService.updateOrder(dto.getOrderId(), dto.getPhase());
		return "order" + dto.getOrderId();
	}
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Dto {
	String orderId;
	Phase phase;
}

