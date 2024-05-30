package com.commission.Strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlatStategy implements DiscountStartegyInterface {

	private double flat;

	public FlatStategy() {
		super();
	}

	public FlatStategy(double flat) {
		super();
		this.flat = flat;
	}

	@Override
	public double getCommision(double price) {
		// TODO Auto-generated method stub
		return price - flat;

	}

}
