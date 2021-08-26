package com.rewardsprogram.challenge;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Entity
@NoArgsConstructor
public class CustomerEntity {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RewardsEntity> transactions = new ArrayList<>();
}
