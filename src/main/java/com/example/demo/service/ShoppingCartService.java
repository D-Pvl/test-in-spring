package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Product;
import com.example.demo.entity.ShopingCart;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ShopingCartRepository;

@Service
public class ShoppingCartService {

	@Autowired
	ShopingCartRepository scRepo;

	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	ProductRepository productRepo;

	public ShopingCart addProductToSC(Integer customerId, List<Integer> productId) {

		ShopingCart sc = new ShopingCart();
		Customer customer = customerRepo.findById(customerId).get();

		if (customer == null) {
			System.out.println("customer does not exits");
		}

		List<Product> products = productRepo.findAllById(productId);

		sc.setProduct(products);
		sc.setCustomer(customer);
		sc.setCreatedOn(new Date(System.currentTimeMillis()));
		scRepo.save(sc);
		return sc;
	}

	public void deleteProductFromSC(Integer scId, Integer productId) {

		ShopingCart sc = scRepo.findById(scId).get();

		Product p = productRepo.findById(productId).get();
		
		if (sc.getProduct().contains(p)) {
			sc.getProduct().remove(p);
			scRepo.save(sc);
		}
		
	}

	public ShopingCart findCartById(Integer cartId) {

		ShopingCart sc = scRepo.findById(cartId).get();
		return sc;

	}
	
	
	
	
	

}
