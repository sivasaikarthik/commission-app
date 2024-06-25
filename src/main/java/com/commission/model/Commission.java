package com.commission.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "comission")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Commission extends BaseModel{
	@EmbeddedId
	private OrderAffiliateKey id;
	private double amount;
	private Status status;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "trans_id")
//	private Transaction transaction;
}
