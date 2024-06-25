package com.commission.Strategy.Factory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.commission.Strategy.CommissionStartegyInterface;
import com.commission.Strategy.FlatCommissionStategy;
import com.commission.Strategy.PercentageCommissionStartegy;
import com.commission.model.Category;

@Component
public class CommisionFacotry {
	private final Map<Category, CommissionStartegyInterface> calculatorMap = new HashMap<>();
	
	@Autowired
	public CommisionFacotry() {
		calculatorMap.put(Category.CLOTHING, new FlatCommissionStategy(50));
		calculatorMap.put(Category.FURNITURE, new PercentageCommissionStartegy(6, 100));
		calculatorMap.put(Category.MOBILECOVERS, new FlatCommissionStategy(5));
		calculatorMap.put(Category.MOBILES, new PercentageCommissionStartegy(15, 1000));
	}
	
	public CommissionStartegyInterface getCalculator(Category item) {
		return this.calculatorMap.get(item);
	}
}
