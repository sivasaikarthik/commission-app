package com.commission.Strategy.Factory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.commission.Strategy.DiscountStartegyInterface;
import com.commission.Strategy.FlatStategy;
import com.commission.Strategy.PercentageStartegy;
import com.commission.model.Category;

@Component
public class CommisionFacotry {
	private final Map<Category, DiscountStartegyInterface> calculatorMap = new HashMap<>();
	
	@Autowired
	public CommisionFacotry() {
		calculatorMap.put(Category.CLOTHING, new FlatStategy(50));
		calculatorMap.put(Category.FURNITURE, new PercentageStartegy(6, 100));
		calculatorMap.put(Category.MOBILECOVERS, new FlatStategy(5));
		calculatorMap.put(Category.CLOTHING, new PercentageStartegy(15, 1000));
	}
	
	public DiscountStartegyInterface getCalculator(Category item) {
		return this.calculatorMap.get(item);
	}
}
