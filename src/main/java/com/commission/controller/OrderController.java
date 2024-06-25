package com.commission.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.commission.dto.OrderDto;
import com.commission.dto.OrderUpdateDto;
import com.commission.exceptions.OrderNotFoundException;
import com.commission.model.Category;
import com.commission.model.Order;
import com.commission.model.Phase;
import com.commission.service.OrderService;
import com.commission.service.abstrac.OrderServiceInterface;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name = "Order" , description =  "Order Mangement APIs")
public class OrderController {
	
	@Autowired
	OrderService orderService ;
	
	@GetMapping("/getOrders")
	public List<Order> getAll() {
		return orderService.getAll();
	}
	
	@GetMapping("/getOrders/{id}")
	public ResponseEntity<OrderDto> getOrderBytId(@PathVariable String id) throws OrderNotFoundException {
		OrderDto orderDto =  orderService.getOrderById(id);
		return ResponseEntity.ok(orderDto);
	}

	
	@PostMapping("/createOrder")
	/* @ApiOperation(value = "Get a hello message", response = String.class) */
	public String createorder(@RequestBody OrderDto orderDto) {
		orderService.createOrder(orderDto);
		return orderDto.getOrderId() + " sucess";
	}
	
	@PutMapping("/updateOrder")
	public String processOrder(@RequestBody OrderUpdateDto dto) throws OrderNotFoundException {
		return orderService.updateOrder(dto.getOrderId(), dto.getPhase());
	}
}

