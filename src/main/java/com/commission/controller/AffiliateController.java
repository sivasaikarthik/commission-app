package com.commission.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.commission.model.Affiliate;
import com.commission.service.AffService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Controller
@Tag(name = "Affiliate" , description =  "Affiliate Mangement APIs")
public class AffiliateController {
	@Autowired
	AffService affService;
	
	@GetMapping("/getAffilicateById/{id}")
	public Affiliate getByID(@PathVariable String id) {
		return affService.getAffiById(id);
	}
}
