package com.commission.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commission.Strategy.Factory.CommisionFacotry;
import com.commission.dao.CommisionRepo;
import com.commission.model.Commission;
import com.commission.model.Order;
import com.commission.model.OrderAffiliateKey;
import com.commission.model.Phase;
import com.commission.model.Status;

import jakarta.transaction.Transactional;

@Service
public class CommissionService {

	private static double AMOUNT_THRESHOLD = 100;
	@Autowired
	CommisionRepo commissionRepo;

	@Autowired
	CommisionFacotry commisionFacotry;
	
	@Autowired
	TransactionService transactionService;

	public List<Commission> getAffiliateById(String id, Status status) {
		return commissionRepo.findByAffiliateId(id, status);
	}

	@Transactional
	public void phaseExcutor(Order order) {
		if (order.getPhase() == Phase.RETURN_PERIOD_EXPIRED) {
			// If phase is expired we will change and the status to active
			this.updateStatus(order.getOrderId(), order.getAffiliatedId(), Status.PENDING, Status.ACTIVE);

			// If the commission amount is more that threshold we will change the status to
			// active active to paid
			// We make the transfer the amount
			if (this.checkStatusTotalAmountOfPendingCommissionsForAffiliate(order.getAffiliatedId())) {
				transactionService.makeTransaction(this.getAffiliateById(order.getAffiliatedId(), Status.ACTIVE));
				this.updateStatus(null, order.getAffiliatedId(), Status.ACTIVE, Status.PAID);
			}
		} else if (order.getPhase() == Phase.CREATED) {
			Commission commision = new Commission(new OrderAffiliateKey(order.getOrderId(), order.getAffiliatedId()),
					commisionFacotry.getCalculator(order.getCategory()).getCommision(order.getPrice()), Status.PENDING);
			commissionRepo.save(commision);
		} else if (order.getPhase() == Phase.CANCELLED || order.getPhase() == Phase.RETURN_PERIOD_EXPIRED) {
			this.updateStatus(order.getOrderId(), order.getAffiliatedId(), Status.PENDING, Status.INACTIVE);
		}
	}

	public boolean checkStatusTotalAmountOfPendingCommissionsForAffiliate(String affiliateId) {
		double totalAmount = commissionRepo.sumAmountByPendingStatusAndAffiliateId(affiliateId,
				Status.ACTIVE);
			return totalAmount	> CommissionService.AMOUNT_THRESHOLD;
	}

	public void updateStatus(String orderId, String affId, Status oldStatus, Status newStatus) {
		commissionRepo.updateState(orderId, affId, oldStatus, newStatus);
	}

}
