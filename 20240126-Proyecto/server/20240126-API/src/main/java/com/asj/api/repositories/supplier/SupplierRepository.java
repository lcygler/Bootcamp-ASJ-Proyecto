package com.asj.api.repositories.supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asj.api.models.supplier.SupplierModel;

public interface SupplierRepository extends JpaRepository<SupplierModel, Integer> {

	@Query(value = "SELECT MAX(id) FROM suppliers", nativeQuery = true)
	Integer getMaxSupplierId();
}
