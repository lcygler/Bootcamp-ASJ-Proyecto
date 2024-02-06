package com.asj.api.repositories.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asj.api.models.order.OrderDetailModel;

public interface OrderDetailRepository extends JpaRepository<OrderDetailModel, Integer> {

	List<OrderDetailModel> findByOrder_Id(Integer orderId);

	@Query(value = "SELECT COUNT(*) FROM order_details od WHERE od.order_id = :id", nativeQuery = true)
	int countByOrder(@Param("id") Integer id);

	@Query(value = "SELECT COUNT(*) FROM order_details od WHERE od.product_id = :id", nativeQuery = true)
	int countByProduct(@Param("id") Integer id);
}
