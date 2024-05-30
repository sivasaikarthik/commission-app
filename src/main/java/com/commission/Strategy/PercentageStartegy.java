package com.commission.Strategy;

import org.springframework.stereotype.Service;

@Service
public class PercentageStartegy implements DiscountStartegyInterface {

	private double percentage;
	private double flat;

	public PercentageStartegy() {
		super();
	}

	public PercentageStartegy(double percentage, double flat) {
		super();
		this.percentage = percentage;
		this.flat = flat;
	}

	@Override
	public double getCommision(double price) {
		// TODO Auto-generated method stub
		return Math.max(price - flat, price - (price * percentage / 100));
	}

}
