package com.commission.Strategy;

import org.springframework.stereotype.Service;

@Service
public class FlatStategy implements StartegyInterface{

	@Override
	public float getCommision() {
		// TODO Auto-generated method stub
		return (float) 0.1;
	}

}
