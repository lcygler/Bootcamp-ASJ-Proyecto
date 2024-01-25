package com.asj.api.controllers.supplier;

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

import com.asj.api.models.supplier.VatConditionModel;
import com.asj.api.services.supplier.VatConditionService;

@RestController
@RequestMapping("/vat-conditions")
@CrossOrigin
public class VatConditionController {

	@Autowired
	VatConditionService vatConditionService;

	@GetMapping("/test")
	public String test() {
		return "Vat conditions endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<VatConditionModel>> getAllVatConditions() {
		return ResponseEntity.ok(vatConditionService.getAllVatConditions());
	}

	@GetMapping("/{id}")
	public ResponseEntity<VatConditionModel> getVatConditionById(@PathVariable Integer id) {
		Optional<VatConditionModel> optionalVatCondition = vatConditionService.getVatConditionById(id);

		if (optionalVatCondition.isPresent()) {
			return ResponseEntity.ok(optionalVatCondition.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<VatConditionModel> createVatCondition(@RequestBody VatConditionModel vatCondition) {
		VatConditionModel createdVatCondition = vatConditionService.createVatCondition(vatCondition);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdVatCondition);
	}

	@PutMapping("/{id}")
	public ResponseEntity<VatConditionModel> updateVatCondition(@PathVariable Integer id, @RequestBody VatConditionModel vatCondition) {
		Optional<VatConditionModel> optionalVatCondition = vatConditionService.updateVatCondition(id, vatCondition);

		if (optionalVatCondition.isPresent()) {
			return ResponseEntity.ok(optionalVatCondition.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<VatConditionModel> patchTask(@PathVariable Integer id, @RequestBody VatConditionModel vatCondition) {
		Optional<VatConditionModel> optionalVatCondition = vatConditionService.patchVatCondition(id, vatCondition);

		if (optionalVatCondition.isPresent()) {
			return ResponseEntity.ok(optionalVatCondition.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<VatConditionModel> deleteVatCondition(@PathVariable Integer id) {
		Optional<VatConditionModel> optionalVatCondition = vatConditionService.deleteVatCondition(id);

		if (optionalVatCondition.isPresent()) {
			return ResponseEntity.ok(optionalVatCondition.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}