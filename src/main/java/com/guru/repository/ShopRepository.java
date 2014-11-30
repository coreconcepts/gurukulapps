package com.guru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guru.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

}
