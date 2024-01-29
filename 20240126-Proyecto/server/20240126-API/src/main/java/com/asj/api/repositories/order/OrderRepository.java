package com.asj.api.repositories.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asj.api.models.order.OrderModel;

public interface OrderRepository extends JpaRepository<OrderModel, Integer>{

	@Query(value = "SELECT MAX(id) FROM orders", nativeQuery = true)
	Integer getMaxOrderId();
}
