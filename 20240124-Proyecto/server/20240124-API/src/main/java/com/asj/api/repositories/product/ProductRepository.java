package com.asj.api.repositories.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asj.api.models.product.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, Integer>{

	List<ProductModel> findBySupplierId(Integer supplierId);
}
