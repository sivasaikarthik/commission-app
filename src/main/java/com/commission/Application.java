package com.commission;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.commission.controller.OrderController;
import com.commission.model.Category;
import com.commission.model.Phase;

@SpringBootApplication
public class Application {

	
//	OrderController orderController = new OrderController();	
	public static void main(String[] args) {
	
		SpringApplication.run(Application.class, args);
	}


}
