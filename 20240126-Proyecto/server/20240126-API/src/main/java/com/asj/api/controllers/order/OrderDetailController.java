package com.asj.api.controllers.order;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asj.api.models.order.OrderDetailModel;
import com.asj.api.services.order.OrderDetailService;
import com.asj.api.utils.Utils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/order-details")
public class OrderDetailController {

	@Autowired
	OrderDetailService orderDetailService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<OrderDetailModel>> getAllOrderDetails() {
		try {
			return ResponseEntity.ok(orderDetailService.getAllOrderDetails());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping(params = "orderId") // [GET] /order-details?orderId={id}
	public ResponseEntity<List<OrderDetailModel>> getOrderDetailsByOrder(@RequestParam Integer orderId) {
		try {
			return ResponseEntity.ok(orderDetailService.getOrderDetailsByOrder(orderId));
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderDetailModel> getOrderDetailById(@PathVariable Integer id) {
		try {
			Optional<OrderDetailModel> optionalOrderDetail = orderDetailService.getOrderDetailById(id);

			if (optionalOrderDetail.isPresent()) {
				return ResponseEntity.ok(optionalOrderDetail.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> createOrderDetail(@Valid @RequestBody OrderDetailModel orderDetail,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = Utils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			OrderDetailModel createdOrderDetail = orderDetailService.createOrderDetail(orderDetail);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDetail);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateOrderDetail(@PathVariable Integer id,
			@Valid @RequestBody OrderDetailModel orderDetail, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = Utils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			Optional<OrderDetailModel> optionalOrderDetail = orderDetailService.updateOrderDetail(id, orderDetail);

			if (optionalOrderDetail.isPresent()) {
				return ResponseEntity.ok(optionalOrderDetail.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<OrderDetailModel> patchOrderDetail(@PathVariable Integer id,
			@RequestBody OrderDetailModel orderDetail) {
		try {
			Optional<OrderDetailModel> optionalOrderDetail = orderDetailService.patchOrderDetail(id, orderDetail);

			if (optionalOrderDetail.isPresent()) {
				return ResponseEntity.ok(optionalOrderDetail.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<OrderDetailModel> deleteOrderDetail(@PathVariable Integer id) {
		try {
			Optional<OrderDetailModel> optionalOrderDetail = orderDetailService.deleteOrderDetail(id);

			if (optionalOrderDetail.isPresent()) {
				return ResponseEntity.ok(optionalOrderDetail.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}
}
