package com.commission.Strategy;

import org.springframework.stereotype.Service;

@Service
public class PercentageStartegy implements StartegyInterface{

	@Override
	public float getCommision() {
		// TODO Auto-generated method stub
		return 1;
	}

}
