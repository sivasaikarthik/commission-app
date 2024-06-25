package com.commission.Strategy;

import org.springframework.stereotype.Service;

@Service
public class FlatCommissionStategy implements CommissionStartegyInterface{

	private double flat;
	static double MIN_AMOUNT = 500;
	public FlatCommissionStategy() {
		super();
	}

	public FlatCommissionStategy(double flat) {
		super();
		this.flat = flat;
	}

	@Override
	public double getCommision(double price) {
		// if buying price is less than threshold 
		// there will be zero discount
		return (price > MIN_AMOUNT) ? flat: 0;
	}

}
