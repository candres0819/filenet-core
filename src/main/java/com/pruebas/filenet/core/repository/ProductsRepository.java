package com.pruebas.filenet.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pruebas.filenet.core.domain.Products;

public interface ProductsRepository extends JpaRepository<Products, Integer> {

}
