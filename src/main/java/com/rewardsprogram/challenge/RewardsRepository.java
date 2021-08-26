package com.rewardsprogram.challenge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardsRepository extends JpaRepository<RewardsEntity, Integer>{
	
}
