package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Address;
import com.example.demo.entity.Customer;
import com.example.demo.repository.AddressRepositiry;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	AddressRepositiry addressRepo;

	public Customer createCustomer(Customer customer) {

		if (addressRepo.existsById(customer.getAddress().getId())) {
			Address address = addressRepo.findById(customer.getAddress().getId()).get();
			customer.setAddress(address);
		}

		return customerRepo.save(customer);
	}

	public Customer updateCustomer(Integer customerId, Customer customer) {

		return customerRepo.findById(customerId).map(cust -> {
			cust.setFirstName(customer.getFirstName());
			cust.setLastName(customer.getLastName());
			cust.setAddress(customer.getAddress());
			return customerRepo.save(cust);
		}).orElseGet(() -> {
			customer.setId(customerId);
			return customerRepo.save(customer);
		});

	}

	public void deleteCustomer(Integer customerId) {

		customerRepo.deleteById(customerId);

	}

	public List <Customer> allCustomers () {
		
		List <Customer> all =  customerRepo.findAll();
				
		return all;
	}
	
	
	
}
