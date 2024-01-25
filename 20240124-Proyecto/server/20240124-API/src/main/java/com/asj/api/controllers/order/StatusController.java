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

import com.asj.api.models.order.StatusModel;
import com.asj.api.services.order.StatusService;

@RestController
@RequestMapping("/statuses")
@CrossOrigin
public class StatusController {

	@Autowired
	StatusService statusService;

	@GetMapping("/test")
	public String test() {
		return "Statuses endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<StatusModel>> getAllStatuses() {
		return ResponseEntity.ok(statusService.getAllStatuses());
	}

	@GetMapping("/{id}")
	public ResponseEntity<StatusModel> getStatusById(@PathVariable Integer id) {
		Optional<StatusModel> optionalStatus = statusService.getStatusById(id);

		if (optionalStatus.isPresent()) {
			return ResponseEntity.ok(optionalStatus.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<StatusModel> createStatus(@RequestBody StatusModel status) {
		StatusModel createdStatus = statusService.createStatus(status);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdStatus);
	}

	@PutMapping("/{id}")
	public ResponseEntity<StatusModel> updateStatus(@PathVariable Integer id, @RequestBody StatusModel status) {
		Optional<StatusModel> optionalStatus = statusService.updateStatus(id, status);

		if (optionalStatus.isPresent()) {
			return ResponseEntity.ok(optionalStatus.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<StatusModel> patchTask(@PathVariable Integer id, @RequestBody StatusModel status) {
		Optional<StatusModel> optionalStatus = statusService.patchStatus(id, status);

		if (optionalStatus.isPresent()) {
			return ResponseEntity.ok(optionalStatus.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<StatusModel> deleteStatus(@PathVariable Integer id) {
		Optional<StatusModel> optionalStatus = statusService.deleteStatus(id);

		if (optionalStatus.isPresent()) {
			return ResponseEntity.ok(optionalStatus.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}