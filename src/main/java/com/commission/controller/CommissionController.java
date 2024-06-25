package com.commission.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.commission.model.Commission;
import com.commission.service.CommissionService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Controller
@Tag(name = "Affiliate" , description =  "Affiliate Mangement APIs")
public class CommissionController {
	@Autowired
	CommissionService commissionService;
	
	@GetMapping("/getAffilicateById/{affilateId}")
	public List<Commission> getByID(@PathVariable("affilateId") String id) {
		return commissionService.getAffiliateById(id, null);
	}
}
