package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

	private String name;
	private String price;
	private String oldPrice;
	private Category category;
	private String origin;
	private Manufacturer manufacturer;
}
