package com.asj.api.services.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asj.api.models.order.OrderModel;
import com.asj.api.repositories.order.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	StatusService statusService;

	public List<OrderModel> getAllOrders() {
		return orderRepository.findAll();
	}

	public Optional<OrderModel> getOrderById(Integer id) {
		return orderRepository.findById(id);
	}
		
	public OrderModel createOrder(OrderModel order) {
		order.setNumber(generateOrderNumber());
		order.setStatus(statusService.getStatusById(1).get());
		order.setCreatedAt(LocalDateTime.now());
		order.setUpdatedAt(LocalDateTime.now());
		order.setIsDeleted(false);
		
		return orderRepository.save(order);
	}

	public Optional<OrderModel> updateOrder(Integer id, OrderModel order) {
		Optional<OrderModel> optionalOrder = Optional.empty();

		if (orderRepository.existsById(id)) {
			order.setUpdatedAt(LocalDateTime.now());
			optionalOrder = Optional.of(orderRepository.save(order));
		}

		return optionalOrder;
	}

	public Optional<OrderModel> patchOrder(Integer id, OrderModel order) {
		Optional<OrderModel> optionalOrder = getOrderById(id);

		if (optionalOrder.isPresent()) {
			OrderModel existingOrder = optionalOrder.get();

			if (order.getIssueDate() != null) {
				existingOrder.setIssueDate(order.getIssueDate());
			}

			if (order.getDeliveryDate() != null) {
				existingOrder.setDeliveryDate(order.getDeliveryDate());
			}

			if (order.getComments() != null) {
				existingOrder.setComments(order.getComments());
			}

			if (order.getTotal() != null) {
				existingOrder.setTotal(order.getTotal());
			}

			if (order.getStatus() != null) {
				existingOrder.setStatus(order.getStatus());
			}

			if (order.getSupplier() != null) {
				existingOrder.setSupplier(order.getSupplier());
			}

			if (order.getUser() != null) {
				existingOrder.setUser(order.getUser());
			}

			if (order.getIsDeleted() != null) {
				existingOrder.setIsDeleted(order.getIsDeleted());
			}

			existingOrder.setUpdatedAt(LocalDateTime.now());

			return Optional.of(orderRepository.save(existingOrder));
		} else {
			return Optional.empty();
		}
	}

	public Optional<OrderModel> deleteOrder(Integer id) {
		Optional<OrderModel> optionalOrder = getOrderById(id);

		if (optionalOrder.isPresent()) {
			orderRepository.deleteById(id);
		}

		return optionalOrder;
	}
	
	public Integer getNextOrderId() {
		Integer maxOrderId = orderRepository.getMaxOrderId();

		if (maxOrderId != null) {
			return maxOrderId + 1;
		} else {
			return 1;
		}
	}

	private String generateOrderNumber() {
		return "OC-" + getNextOrderId();
	}

}