package com.asj.api.repositories.address;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asj.api.models.address.AddressModel;

public interface AddressRepository extends JpaRepository<AddressModel, Integer>{

}
