package com.asj.api.services.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.InvalidIdentifierException;
import com.asj.api.models.order.OrderDetailModel;
import com.asj.api.repositories.order.OrderDetailRepository;
import com.asj.api.services.product.ProductService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class OrderDetailService {

	@Autowired
	OrderDetailRepository orderDetailRepository;

	@Autowired
	OrderService orderService;

	@Autowired
	ProductService productService;

	@PersistenceContext
	private EntityManager entityManager;

	public List<OrderDetailModel> getAllOrderDetails() {
		return orderDetailRepository.findAll();
	}

	public List<OrderDetailModel> getOrderDetailsByOrder(Integer orderId) {
		return orderDetailRepository.findByOrder_Id(orderId);
	}

	public Optional<OrderDetailModel> getOrderDetailById(Integer id) {
		return orderDetailRepository.findById(id);
	}

	@Transactional
	public OrderDetailModel createOrderDetail(OrderDetailModel orderDetail) {
		validateOrder(orderDetail.getOrder().getId());
		validateProduct(orderDetail.getProduct().getId());

		orderDetail.setCreatedAt(LocalDateTime.now());
		orderDetail.setUpdatedAt(LocalDateTime.now());

		OrderDetailModel createdOrderDetail = orderDetailRepository.save(orderDetail);
		entityManager.refresh(createdOrderDetail);

		return createdOrderDetail;
	}

	@Transactional
	public List<OrderDetailModel> createOrderDetails(List<OrderDetailModel> orderDetails) {
		List<OrderDetailModel> createdOrderDetails = new ArrayList<>();

		for (OrderDetailModel orderDetail : orderDetails) {
			OrderDetailModel createdOrderDetail = createOrderDetail(orderDetail);
			createdOrderDetails.add(createdOrderDetail);
		}

		return createdOrderDetails;
	}

	@Transactional
	public Optional<OrderDetailModel> updateOrderDetail(Integer id, OrderDetailModel orderDetail) {
		validateOrder(orderDetail.getOrder().getId());
		validateProduct(orderDetail.getProduct().getId());

		Optional<OrderDetailModel> optionalOrderDetail = Optional.empty();

		if (orderDetailRepository.existsById(id)) {
			orderDetail.setUpdatedAt(LocalDateTime.now());

			OrderDetailModel updatedOrderDetail = orderDetailRepository.save(orderDetail);
			entityManager.refresh(updatedOrderDetail);

			optionalOrderDetail = Optional.of(updatedOrderDetail);

		}

		return optionalOrderDetail;
	}

	@Transactional
	public Optional<OrderDetailModel> patchOrderDetail(Integer id, OrderDetailModel orderDetail) {
		Optional<OrderDetailModel> optionalOrderDetail = getOrderDetailById(id);

		if (optionalOrderDetail.isPresent()) {
			OrderDetailModel existingOrderDetail = optionalOrderDetail.get();

			if (orderDetail.getOrder() != null) {
				validateOrder(orderDetail.getOrder().getId());
				existingOrderDetail.setOrder(orderDetail.getOrder());
			}

			if (orderDetail.getProduct() != null) {
				validateProduct(orderDetail.getProduct().getId());
				existingOrderDetail.setProduct(orderDetail.getProduct());
			}

			if (orderDetail.getQuantity() != null) {
				existingOrderDetail.setQuantity(orderDetail.getQuantity());
			}

			if (orderDetail.getPrice() != null) {
				existingOrderDetail.setPrice(orderDetail.getPrice());
			}

			existingOrderDetail.setUpdatedAt(LocalDateTime.now());

			orderDetailRepository.save(existingOrderDetail);
			entityManager.flush();
			entityManager.refresh(existingOrderDetail);

			return Optional.of(existingOrderDetail);

		} else {
			return Optional.empty();
		}
	}

	@Transactional
	public Optional<OrderDetailModel> deleteOrderDetail(Integer id) {
		Optional<OrderDetailModel> optionalOrderDetail = getOrderDetailById(id);

		if (optionalOrderDetail.isPresent()) {
			orderDetailRepository.deleteById(id);
		}

		return optionalOrderDetail;
	}

	private void validateOrder(Integer id) {
		if (!orderService.isIdValid(id)) {
			throw new InvalidIdentifierException("Order is not valid");
		}
	}

	private void validateProduct(Integer id) {
		if (!productService.isIdValid(id)) {
			throw new InvalidIdentifierException("Product is not valid");
		}
	}

}