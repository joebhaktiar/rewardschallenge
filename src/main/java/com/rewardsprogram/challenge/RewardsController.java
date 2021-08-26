package com.rewardsprogram.challenge;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RewardsController {
	
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private RewardsServiceImplementation rewardService;
	
	@PutMapping("/calculaterewardspertransaction")
	public int GetTransactionReward(@RequestParam("transactionCost") int transactionCost) {
		return rewardService.getTransactionRewards(transactionCost);
    }
	
	@PutMapping("/ingestdata")
	public CustomerEntity[] addTransactionFromJson() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		byte[] mapData = Files.readAllBytes(Paths.get("src/main/resources/TransactionHistory.txt"));
		CustomerEntity[] cea = null;
		cea = objectMapper.readValue(mapData, CustomerEntity[].class);
        List<CustomerEntity> customerList = Arrays.asList(cea);
        for(CustomerEntity customerEntityList : customerList) {
        	customerRepo.saveAndFlush(customerEntityList);
        }
		return cea;
    }
	
	@GetMapping("/getalltransactionhistory")
	public List<CustomerEntity> getTransactions() { //Get predefined transaction record from JSON		
		return customerRepo.findAll();
    }
	
	@GetMapping("/gettotalrewardspercustomerinlast3months/{id}")
	public int getRewardsPerCustomer(@PathVariable("id") Integer id) {
		Optional<CustomerEntity> customer = Optional.of(new CustomerEntity());
		customer = customerRepo.findById(id);
		return rewardService.getTotalRewardsPerCustomer(customer,3);
	}
	
	@GetMapping("/gettotaltewardspercustomerpermonth/{id}")
	public int getTotalRewardsPerCustomerPerMonth(@PathVariable("id") Integer id) {
		Optional<CustomerEntity> customer = Optional.of(new CustomerEntity());
		customer = customerRepo.findById(id);
		return rewardService.getTotalRewardsPerCustomer(customer,1);
	}
}