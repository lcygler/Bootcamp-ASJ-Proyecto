package com.asj.api.services.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesExistException;
import com.asj.api.exceptions.InvalidIdentifierException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.order.OrderModel;
import com.asj.api.repositories.order.OrderDetailRepository;
import com.asj.api.repositories.order.OrderRepository;
import com.asj.api.services.supplier.SupplierService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	StatusService statusService;

	@Autowired
	SupplierService supplierService;

	@Autowired
	OrderDetailRepository orderDetailRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<OrderModel> getAllOrders() {
		return orderRepository.findAll();
	}

	public Optional<OrderModel> getOrderById(Integer id) {
		return orderRepository.findById(id);
	}

	@Transactional
	public OrderModel createOrder(OrderModel order) {
		if (!isNumberUnique(order.getNumber())) {
			throw new UniqueViolationException("Order number must be unique");
		}

		validateStatus(order.getStatus().getId());
		validateSupplier(order.getSupplier().getId());

		order.setCreatedAt(LocalDateTime.now());
		order.setUpdatedAt(LocalDateTime.now());
		order.setIsDeleted(false);

		OrderModel createdOrder = orderRepository.save(order);
		entityManager.refresh(createdOrder);

		return createdOrder;
	}

	@Transactional
	public Optional<OrderModel> updateOrder(Integer id, OrderModel order) {
		if (!isNumberUniqueAndIdNot(order.getNumber(), id)) {
			throw new UniqueViolationException("Order number must be unique");
		}

		validateStatus(order.getStatus().getId());
		validateSupplier(order.getSupplier().getId());

		Optional<OrderModel> optionalOrder = Optional.empty();

		if (orderRepository.existsById(id)) {
			order.setUpdatedAt(LocalDateTime.now());

			OrderModel updatedOrder = orderRepository.save(order);
			entityManager.refresh(updatedOrder);

			optionalOrder = Optional.of(updatedOrder);
		}

		return optionalOrder;
	}

	@Transactional
	public Optional<OrderModel> patchOrder(Integer id, OrderModel order) {
		Optional<OrderModel> optionalOrder = getOrderById(id);

		if (optionalOrder.isPresent()) {
			OrderModel existingOrder = optionalOrder.get();

			if (order.getNumber() != null) {
				if (!isNumberUniqueAndIdNot(order.getNumber(), id)) {
					throw new UniqueViolationException("Order number must be unique");
				}
				existingOrder.setNumber(order.getNumber());
			}

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
				validateStatus(order.getStatus().getId());
				existingOrder.setStatus(order.getStatus());
			}

			if (order.getSupplier() != null) {
				validateSupplier(order.getSupplier().getId());
				existingOrder.setSupplier(order.getSupplier());
			}

			if (order.getUser() != null) {
				existingOrder.setUser(order.getUser());
			}

			if (order.getIsDeleted() != null) {
				existingOrder.setIsDeleted(order.getIsDeleted());
			}

			existingOrder.setUpdatedAt(LocalDateTime.now());

			orderRepository.save(existingOrder);
			entityManager.flush();
			entityManager.refresh(existingOrder);

			return Optional.of(existingOrder);
		} else {
			return Optional.empty();
		}
	}

	@Transactional
	public Optional<OrderModel> deleteOrder(Integer id) {
		if (orderDetailRepository.countByOrder(id) > 0) {
			throw new AssociatedEntitiesExistException("Deletion failed: Associated entities found");
		}

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

	private boolean isNumberUnique(String number) {
		return !orderRepository.existsByNumber(number);
	}

	private boolean isNumberUniqueAndIdNot(String number, Integer id) {
		return !orderRepository.existsByNumberAndIdNot(number, id);
	}

	public boolean isIdValid(Integer id) {
		return orderRepository.existsById(id);
	}

	private void validateStatus(Integer id) {
		if (!statusService.isIdValid(id)) {
			throw new InvalidIdentifierException("Status is not valid");
		}
	}

	private void validateSupplier(Integer id) {
		if (!supplierService.isIdValid(id)) {
			throw new InvalidIdentifierException("Supplier is not valid");
		}
	}

}
