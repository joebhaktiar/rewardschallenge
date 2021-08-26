package com.rewardsprogram.challenge;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class RewardsServiceImplementation {
	protected int getTransactionRewards(int transactionCost) {
		int transactionReward = 0;
		int rewardOver100 = 0;
		int rewardOver50 = 0;
		if (transactionCost>100) {
			rewardOver100 = (transactionCost-100)*2;
		}
		if(transactionCost>50) {
			rewardOver50 = 50;
		}
		transactionReward = rewardOver100 + rewardOver50;
		return transactionReward;
	}
	
	protected Timestamp getDate() {
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp;
	}
	
	protected int getTotalRewardsPerCustomer(Optional<CustomerEntity> customer, int n) {
		int totalRewardsPerCustomer = 0;
		
		Timestamp timestampThreeMonthsAgo = new Timestamp(new Date().getTime());	 
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeInMillis(timestampThreeMonthsAgo.getTime());
	    cal.setTimeInMillis(timestampThreeMonthsAgo.getTime());
	    cal.add(Calendar.MONTH, -n);
	    timestampThreeMonthsAgo = new Timestamp(cal.getTime().getTime());
		
		for(RewardsEntity rDto : customer.get().getTransactions()) {
			if(rDto.getTransactionDate().after(timestampThreeMonthsAgo)) {
				totalRewardsPerCustomer = totalRewardsPerCustomer + rDto.getTransactionReward();
				}
		}
		return totalRewardsPerCustomer;
	}
}
