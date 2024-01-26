package com.asj.api.repositories.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asj.api.models.order.StatusModel;

public interface StatusRepository extends JpaRepository<StatusModel, Integer>{

}
