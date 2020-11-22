package com.example.demo.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	CustomerRepository customerRepo;

	@GetMapping("/")
	public String viewHomePage(Model model) {

		List<Customer> listCustomers = customerService.allCustomers();
		model.addAttribute("listCustomers", listCustomers);
		return "index";

	}

	
	
	@PostMapping("/saveCustomer")
	public String saveCustomer (@ModelAttribute("customer")Customer customer) {  // create customer for index.html template
		
		 customerService.createCustomer(customer);
		 return "redirect:/";
	}
	
	@GetMapping("/showNewCustomerForm")
	public String showNewCustomerFrom (Model model) {   // for index.html template
		
		Customer customer = new Customer();
		model.addAttribute("customer",customer);
		
		return "new_customer";
	}
	
	@GetMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable("id") Integer id) {
		
		customerRepo.deleteById(id);
	
		return "redirect:/";
	}
	
	
	 
	@PostMapping(value = "/create")
	public Customer create(@RequestBody Customer customer) {

		return customerService.createCustomer(customer);

	}

	@PutMapping(value = "/updateCustomer/{customerId}")
	public Customer updateCustomer(@PathVariable Integer customerId, @RequestBody Customer customer) {

		return customerService.updateCustomer(customerId, customer);

	}

	@DeleteMapping(value = "/deleteCustomerByCustomerName/{customerId}")
	public String deleteCustomerByCustomerName(@PathVariable Integer customerId) {

		customerService.deleteCustomer(customerId);

		return "customer was deleted";

	}

}
