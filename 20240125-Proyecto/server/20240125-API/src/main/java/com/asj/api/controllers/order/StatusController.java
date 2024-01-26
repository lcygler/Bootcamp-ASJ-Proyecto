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
import org.springframework.web.bind.annotation.RestController;

import com.asj.api.models.order.StatusModel;
import com.asj.api.services.order.StatusService;
import com.asj.api.utils.Utils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/statuses")
public class StatusController {

	@Autowired
	StatusService statusService;

	@GetMapping("/test")
	public String test() {
		return "Statuses endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<StatusModel>> getAllStatuses() {
		try {
			return ResponseEntity.ok(statusService.getAllStatuses());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<StatusModel> getStatusById(@PathVariable Integer id) {
		try {
			Optional<StatusModel> optionalStatus = statusService.getStatusById(id);

			if (optionalStatus.isPresent()) {
				return ResponseEntity.ok(optionalStatus.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> createStatus(@Valid @RequestBody StatusModel status, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = Utils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			StatusModel createdStatus = statusService.createStatus(status);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdStatus);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateStatus(@PathVariable Integer id, @Valid @RequestBody StatusModel status,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = Utils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			Optional<StatusModel> optionalStatus = statusService.updateStatus(id, status);

			if (optionalStatus.isPresent()) {
				return ResponseEntity.ok(optionalStatus.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<StatusModel> patchStatus(@PathVariable Integer id, @RequestBody StatusModel status) {
		try {
			Optional<StatusModel> optionalStatus = statusService.patchStatus(id, status);

			if (optionalStatus.isPresent()) {
				return ResponseEntity.ok(optionalStatus.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<StatusModel> deleteStatus(@PathVariable Integer id) {
		try {
			Optional<StatusModel> optionalStatus = statusService.deleteStatus(id);

			if (optionalStatus.isPresent()) {
				return ResponseEntity.ok(optionalStatus.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
