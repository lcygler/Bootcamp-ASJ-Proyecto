package com.asj.api.repositories.address;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asj.api.models.address.StateModel;

public interface StateRepository extends JpaRepository<StateModel, Integer>{

}
