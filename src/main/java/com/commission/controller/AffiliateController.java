package com.commission.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.commission.model.Affiliate;
import com.commission.service.AffService;

public class AffiliateController {
	@Autowired
	AffService affService;
	public Optional<Affiliate> getByID(String affilicateId) {
		return affService.getAffiById(affilicateId);
	}
}
