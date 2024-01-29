package com.asj.api.repositories.supplier;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asj.api.models.supplier.TaxInformationModel;

public interface TaxInformationRepository extends JpaRepository<TaxInformationModel, Integer> {

}
