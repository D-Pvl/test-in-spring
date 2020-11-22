package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductResponse;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping(value = "/create")
	public Product createProduct(@RequestBody Product product) {

		return productService.createProduct(product);
	}

	@PutMapping(value = "/update/{productId}")
	public Product updateProduct(@PathVariable Integer productId, @RequestBody Product product) {

		return productService.updateProduct(productId, product);

	}

	@DeleteMapping(value = "/delete/{productId}")
	public void deleteProduct(@PathVariable Integer productId) {

		productService.deleteProduct(productId);

	}

	@GetMapping(value = "/getAllProducts")
	public List<Product> getAllProduct() {

		return productService.getAllProduct();

	}

	@GetMapping(value = "/getByCategory/{productCategory}")
	public List<ProductResponse> getByCategory(@PathVariable String productCategory) {

		return productService.getAllByCategory(productCategory);

	}

	@GetMapping(value = "/getByOrigin/{origin}")
	public List<Product> getAllProductsByOrigin(@PathVariable String origin) {

		
		return productService.getAllByOrigin(origin);
		
	
		
	}
		
		@GetMapping(value="/getByMacedonianOrigin")
		public List <ProductResponse> getAllByMacedonianOrigin (){
			
			
			return productService.getAllByMacedonianOrigin();
			
		
		
	}

}
