package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ShopingCart;

@Repository
public interface ShopingCartRepository extends JpaRepository<ShopingCart, Integer> {

}
