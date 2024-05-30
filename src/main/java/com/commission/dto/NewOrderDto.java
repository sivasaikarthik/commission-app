package com.commission.dto;

import java.time.LocalDateTime;

import com.commission.model.Category;
import com.commission.model.Phase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewOrderDto {
	private String orderId;
	private String productId;
	private String affiliatedId;
	private int price;
	private Category category;
}
