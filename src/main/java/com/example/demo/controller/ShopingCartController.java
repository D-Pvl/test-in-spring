package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.entity.ShopingCart;
import com.example.demo.service.ShoppingCartService;

@RestController
@RequestMapping("/shopingCart")
public class ShopingCartController {

	
	@Autowired
	ShoppingCartService scService;	
	
	@PostMapping(value="/addProduct/{customerId}")
	public ShopingCart addProductsToCart(@PathVariable Integer customerId,@RequestBody List<Integer> productId) {
		
		return scService.addProductToSC(customerId, productId);
		
	}
	
	
	@DeleteMapping("/deleteProduct/{shoppingCartId}")
	public String deleteProduct(@PathVariable Integer shoppingCartId,@RequestBody Integer productId) {
		
		scService.deleteProductFromSC(shoppingCartId,productId);
		
		return "success";
	}
	
	@GetMapping("/findCartById/{cartId}")
	public ShopingCart findCartById(@PathVariable Integer cartId) {
		
		return scService.findCartById(cartId);
		
	}
	
	
	
	
	
	
	
	
	
}
