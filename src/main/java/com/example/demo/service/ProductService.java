package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Manufacturer;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductResponse;
import com.example.demo.repository.AddressRepositiry;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ManufacturerRepository;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepo;

	@Autowired
	AddressRepositiry addressRepo;

	@Autowired
	ManufacturerRepository manuRepo;

	@Autowired
	CategoryRepository catRepo;

	public Product createProduct(Product product) {

		if (manuRepo.existsByName(product.getManufacturer().getName())) {

			Manufacturer man = manuRepo.findByName(product.getManufacturer().getName());

			product.setManufacturer(man);

		}

		if (catRepo.existsByProductCategory(product.getCategory().getProductCategory())) {

			Category c = catRepo.findByProductCategory(product.getCategory().getProductCategory());

			product.setCategory(c);

		}

		return productRepo.save(product);

	}

	public Product updateProduct(Integer productId, Product product) {

		return productRepo.findById(productId).map(prod -> {
			prod.setName(product.getName());
			prod.setOrigin(product.getOrigin());
			prod.setPrice(product.getPrice());
			prod.setCategory(prod.getCategory());
			prod.setManufacturer(product.getManufacturer());
			return productRepo.save(prod);
		}).orElseGet(() -> {
			product.setId(productId);
			return productRepo.save(product);
		});

	}

	public String deleteProduct(Integer productId) {

		productRepo.deleteById(productId);
		return "product was successfully deleted";

	}

	public List<Product> getAllProduct() {

		return productRepo.findAll();

	}

	public List<ProductResponse> getAllByCategory(String productCategory) {

		Category ct = catRepo.findByProductCategory(productCategory);
		List<Product> products = productRepo.findAllByCategory(ct);
		List<ProductResponse> result = new ArrayList<>();

		for (Product product : products) {

			ProductResponse pr = new ProductResponse();
			pr.setName(product.getName());
			pr.setPrice(product.getPrice());
			pr.setCategory(product.getCategory());
			result.add(pr);

		}

		return result;

	}

	public List<Product> getAllByOrigin(String origin) {

		List<Product> result = productRepo.findAllByOrigin(origin);
		return result;

	}

	public List<ProductResponse> getAllByMacedonianOrigin() {

		String origin = "Macedonia";
		List<Product> products = productRepo.findAllByOrigin(origin);

		List<ProductResponse> result = new ArrayList<ProductResponse>();

		for (Product product : products) {

			ProductResponse pr = new ProductResponse();

			Integer oldPrice = Integer.parseInt(product.getPrice());
			Double newPrice = oldPrice * 0.18;
			String finalNewPrice = String.valueOf(newPrice);

			pr.setCategory(product.getCategory());
			pr.setManufacturer(product.getManufacturer());
			pr.setName(product.getName());
			pr.setOldPrice(product.getPrice());
			pr.setOrigin(product.getOrigin());
			pr.setPrice(finalNewPrice);

			result.add(pr);
		}

		return result;
	}

}
