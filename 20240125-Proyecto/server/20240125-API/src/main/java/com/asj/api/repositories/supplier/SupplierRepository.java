package com.asj.api.repositories.supplier;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asj.api.models.supplier.SupplierModel;

public interface SupplierRepository extends JpaRepository<SupplierModel, Integer>{

}
