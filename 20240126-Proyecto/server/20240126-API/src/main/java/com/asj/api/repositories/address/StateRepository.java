package com.asj.api.repositories.address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asj.api.models.address.StateModel;

public interface StateRepository extends JpaRepository<StateModel, Integer>{

	List<StateModel> findByCountryId(Integer countryId);
}
