package com.asj.api.repositories.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asj.api.models.order.OrderModel;

public interface OrderRepository extends JpaRepository<OrderModel, Integer>{

}
