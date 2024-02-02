package com.asj.api.controllers.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asj.api.exceptions.AssociatedEntitiesException;
import com.asj.api.exceptions.InvalidIdentifierException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.order.OrderDetailModel;
import com.asj.api.models.order.OrderModel;
import com.asj.api.services.order.OrderDetailService;
import com.asj.api.services.order.OrderService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	OrderDetailService orderDetailService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<OrderModel>> getAllOrders() {
		try {
			return ResponseEntity.ok(orderService.getAllOrders());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderModel> getOrderById(@PathVariable Integer id) {
		try {
			Optional<OrderModel> optionalOrder = orderService.getOrderById(id);

			if (optionalOrder.isPresent()) {
				return ResponseEntity.ok(optionalOrder.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<List<OrderDetailModel>> getOrderDetailsByOrderId(@PathVariable Integer id) {
		try {
			List<OrderDetailModel> orderDetails = orderDetailService.getOrderDetailsByOrder(id);
			return ResponseEntity.ok(orderDetails);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/number")
	public ResponseEntity<String> getOrderNumber() {
		try {
			String number = orderService.generateOrderNumber(LocalDateTime.now());
			return ResponseEntity.ok(number);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<Object> createOrder(@Valid @RequestBody OrderModel order, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = ValidationUtils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			OrderModel createdOrder = orderService.createOrder(order);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
		} catch (UniqueViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (InvalidIdentifierException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateOrder(@PathVariable Integer id, @Valid @RequestBody OrderModel order,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = ValidationUtils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			Optional<OrderModel> optionalOrder = orderService.updateOrder(id, order);

			if (optionalOrder.isPresent()) {
				return ResponseEntity.ok(optionalOrder.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (UniqueViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (InvalidIdentifierException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Object> patchOrder(@PathVariable Integer id, @RequestBody OrderModel order) {
		try {
			Optional<OrderModel> optionalOrder = orderService.patchOrder(id, order);

			if (optionalOrder.isPresent()) {
				return ResponseEntity.ok(optionalOrder.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (UniqueViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (InvalidIdentifierException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteOrder(@PathVariable Integer id) {
		try {
			Optional<OrderModel> optionalOrder = orderService.deleteOrder(id);

			if (optionalOrder.isPresent()) {
				return ResponseEntity.ok(optionalOrder.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (AssociatedEntitiesException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
