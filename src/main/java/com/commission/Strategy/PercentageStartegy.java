package com.commission.Strategy;

import org.springframework.stereotype.Service;

@Service
public class PercentageStartegy implements DiscountStartegyInterface {

	private double percentage;
	private double maxCap;

	public PercentageStartegy() {
		super();
	}

	public PercentageStartegy(double percentage, double maxCap) {
		super();
		this.percentage = percentage;
		this.maxCap = maxCap;
	}

	@Override
	public double getCommision(double price) {
		return Math.min(this.maxCap, (price * percentage / 100));
	}

}
