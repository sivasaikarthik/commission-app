package com.commission.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Affiliate {
	
	@Id
	private String affiliateId;
	@OneToMany
	private ArrayList<OrderCommission> orderCommissions;
	
}
