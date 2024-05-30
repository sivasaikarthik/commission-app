package com.commission.dto;

import com.commission.model.Phase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateDto {
	private String orderId;
	private Phase phase;
}
