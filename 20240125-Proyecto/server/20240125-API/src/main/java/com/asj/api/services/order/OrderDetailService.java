package com.asj.api.services.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asj.api.models.order.OrderDetailModel;
import com.asj.api.repositories.order.OrderDetailRepository;

@Service
public class OrderDetailService {

	@Autowired
	OrderDetailRepository orderDetailRepository;

	public List<OrderDetailModel> getAllOrderDetails() {
		return orderDetailRepository.findAll();
	}
	
	public List<OrderDetailModel> getOrderDetailsByOrder(Integer orderId) {
        return orderDetailRepository.findByOrder_Id(orderId);
    }

	public Optional<OrderDetailModel> getOrderDetailById(Integer id) {
		return orderDetailRepository.findById(id);
	}

	public OrderDetailModel createOrderDetail(OrderDetailModel orderDetail) {
		orderDetail.setCreatedAt(LocalDateTime.now());
		orderDetail.setUpdatedAt(LocalDateTime.now());

		return orderDetailRepository.save(orderDetail);
	}

	public Optional<OrderDetailModel> updateOrderDetail(Integer id, OrderDetailModel orderDetail) {
		Optional<OrderDetailModel> optionalOrderDetail = Optional.empty();

		if (orderDetailRepository.existsById(id)) {
			orderDetail.setUpdatedAt(LocalDateTime.now());
			optionalOrderDetail = Optional.of(orderDetailRepository.save(orderDetail));
		}

		return optionalOrderDetail;
	}

	public Optional<OrderDetailModel> patchOrderDetail(Integer id, OrderDetailModel orderDetail) {
		Optional<OrderDetailModel> optionalOrderDetail = getOrderDetailById(id);

		if (optionalOrderDetail.isPresent()) {
			OrderDetailModel existingOrderDetail = optionalOrderDetail.get();

			if (orderDetail.getOrder() != null) {
				existingOrderDetail.setOrder(orderDetail.getOrder());
			}

			if (orderDetail.getProduct() != null) {
				existingOrderDetail.setProduct(orderDetail.getProduct());
			}

			if (orderDetail.getQuantity() != null) {
				existingOrderDetail.setQuantity(orderDetail.getQuantity());
			}

			if (orderDetail.getPrice() != null) {
				existingOrderDetail.setPrice(orderDetail.getPrice());
			}

			existingOrderDetail.setUpdatedAt(LocalDateTime.now());

			return Optional.of(orderDetailRepository.save(existingOrderDetail));
		} else {
			return Optional.empty();
		}
	}

	public Optional<OrderDetailModel> deleteOrderDetail(Integer id) {
		Optional<OrderDetailModel> optionalOrderDetail = getOrderDetailById(id);

		if (optionalOrderDetail.isPresent()) {
			orderDetailRepository.deleteById(id);
		}

		return optionalOrderDetail;
	}

}