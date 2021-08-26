package com.rewardsprogram.challenge;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Data
@NoArgsConstructor
public class RewardsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String transactionId;
    private Timestamp transactionDate;
    private int transactionCost;
    private int transactionReward;
}
