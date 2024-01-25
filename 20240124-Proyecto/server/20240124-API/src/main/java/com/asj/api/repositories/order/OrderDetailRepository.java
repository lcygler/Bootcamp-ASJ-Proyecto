package com.asj.api.repositories.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asj.api.models.order.OrderDetailModel;

public interface OrderDetailRepository extends JpaRepository<OrderDetailModel, Integer> {

	List<OrderDetailModel> findByOrder_Id(Integer orderId);
}
