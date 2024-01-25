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
import org.springframework.web.bind.annotation.RestController;

import com.asj.api.models.order.OrderDetailModel;
import com.asj.api.models.order.OrderModel;
import com.asj.api.services.order.OrderDetailService;
import com.asj.api.services.order.OrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	OrderDetailService orderDetailService;
	
	@GetMapping("/test")
	public String test() {
		return "Orders endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<OrderModel>> getAllOrders() {
		return ResponseEntity.ok(orderService.getAllOrders());
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderModel> getOrderById(@PathVariable Integer id) {
		Optional<OrderModel> optionalOrder = orderService.getOrderById(id);

		if (optionalOrder.isPresent()) {
			return ResponseEntity.ok(optionalOrder.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{id}/details")
	public ResponseEntity<List<OrderDetailModel>> getOrderDetailsByOrderId(@PathVariable Integer id) {
		List<OrderDetailModel> orderDetails = orderDetailService.getOrderDetailsByOrder(id);
		return ResponseEntity.ok(orderDetails);
	}

	@PostMapping
	public ResponseEntity<OrderModel> createOrder(@RequestBody OrderModel order) {
		OrderModel createdOrder = orderService.createOrder(order);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
	}

	@PutMapping("/{id}")
	public ResponseEntity<OrderModel> updateOrder(@PathVariable Integer id, @RequestBody OrderModel order) {
		Optional<OrderModel> optionalOrder = orderService.updateOrder(id, order);

		if (optionalOrder.isPresent()) {
			return ResponseEntity.ok(optionalOrder.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<OrderModel> patchTask(@PathVariable Integer id, @RequestBody OrderModel order) {
		Optional<OrderModel> optionalOrder = orderService.patchOrder(id, order);

		if (optionalOrder.isPresent()) {
			return ResponseEntity.ok(optionalOrder.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<OrderModel> deleteOrder(@PathVariable Integer id) {
		Optional<OrderModel> optionalOrder = orderService.deleteOrder(id);

		if (optionalOrder.isPresent()) {
			return ResponseEntity.ok(optionalOrder.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}