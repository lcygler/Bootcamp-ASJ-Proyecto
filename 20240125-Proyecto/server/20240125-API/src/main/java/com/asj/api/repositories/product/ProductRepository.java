package com.asj.api.repositories.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asj.api.models.product.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, Integer>{

	List<ProductModel> findBySupplierId(Integer supplierId);
	
	@Query(value = "SELECT * FROM products WHERE id = %:productId%", nativeQuery = true)
	ProductModel getProductById(@Param("productId") Integer productId);
	
}
