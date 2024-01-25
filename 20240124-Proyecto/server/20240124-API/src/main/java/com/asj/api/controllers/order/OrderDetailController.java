package com.asj.api.controllers.order;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asj.api.models.order.OrderDetailModel;
import com.asj.api.services.order.OrderDetailService;

@RestController
@RequestMapping("/order-details")
@CrossOrigin
public class OrderDetailController {

	@Autowired
	OrderDetailService orderDetailService;

	@GetMapping("/test")
	public String test() {
		return "Order details endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<OrderDetailModel>> getAllOrderDetails() {
		return ResponseEntity.ok(orderDetailService.getAllOrderDetails());
	}
	
	@GetMapping(params = "orderId") // Invoke method only if request has query param
	public ResponseEntity<List<OrderDetailModel>> getOrderDetailsByOrder(@RequestParam Integer orderId) {
		return ResponseEntity.ok(orderDetailService.getOrderDetailsByOrder(orderId));
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderDetailModel> getOrderDetailById(@PathVariable Integer id) {
		Optional<OrderDetailModel> optionalOrderDetail = orderDetailService.getOrderDetailById(id);

		if (optionalOrderDetail.isPresent()) {
			return ResponseEntity.ok(optionalOrderDetail.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<OrderDetailModel> createOrderDetail(@RequestBody OrderDetailModel orderDetail) {
		OrderDetailModel createdOrderDetail = orderDetailService.createOrderDetail(orderDetail);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDetail);
	}

	@PutMapping("/{id}")
	public ResponseEntity<OrderDetailModel> updateOrderDetail(@PathVariable Integer id,
			@RequestBody OrderDetailModel orderDetail) {
		Optional<OrderDetailModel> optionalOrderDetail = orderDetailService.updateOrderDetail(id, orderDetail);

		if (optionalOrderDetail.isPresent()) {
			return ResponseEntity.ok(optionalOrderDetail.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<OrderDetailModel> patchTask(@PathVariable Integer id,
			@RequestBody OrderDetailModel orderDetail) {
		Optional<OrderDetailModel> optionalOrderDetail = orderDetailService.patchOrderDetail(id, orderDetail);

		if (optionalOrderDetail.isPresent()) {
			return ResponseEntity.ok(optionalOrderDetail.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<OrderDetailModel> deleteOrderDetail(@PathVariable Integer id) {
		Optional<OrderDetailModel> optionalOrderDetail = orderDetailService.deleteOrderDetail(id);

		if (optionalOrderDetail.isPresent()) {
			return ResponseEntity.ok(optionalOrderDetail.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}