package com.asj.api.repositories.order;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asj.api.models.order.OrderModel;

public interface OrderRepository extends JpaRepository<OrderModel, Integer> {

	boolean existsById(Integer id);

	boolean existsByNumber(String number);

	boolean existsByNumberAndIdNot(String number, Integer id);

	@Query(value = "SELECT MAX(id) FROM orders", nativeQuery = true)
	Integer getMaxOrderId();
	
	@Query(value = "SELECT COUNT(*) FROM orders o WHERE o.issue_date = :issueDate", nativeQuery = true)
    int countByIssueDate(@Param("issueDate") LocalDateTime issueDate);

	@Query(value = "SELECT COUNT(*) FROM orders o WHERE o.status_id = :id", nativeQuery = true)
	int countByStatus(@Param("id") Integer id);

	@Query(value = "SELECT COUNT(*) FROM orders o WHERE o.supplier_id = :id", nativeQuery = true)
	int countBySupplier(@Param("id") Integer id);

	@Query(value = "SELECT COUNT(*) FROM orders o WHERE o.user_id = :id", nativeQuery = true)
	int countByUser(@Param("id") Integer id);
}
