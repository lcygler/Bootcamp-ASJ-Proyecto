package com.asj.api.controllers.supplier;

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

import com.asj.api.models.supplier.VatConditionModel;
import com.asj.api.services.supplier.VatConditionService;
import com.asj.api.utils.Utils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vat-conditions")
public class VatConditionController {

	@Autowired
	VatConditionService vatConditionService;

	@GetMapping("/test")
	public String test() {
		return "Vat conditions endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<VatConditionModel>> getAllVatConditions() {
		try {
			return ResponseEntity.ok(vatConditionService.getAllVatConditions());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<VatConditionModel> getVatConditionById(@PathVariable Integer id) {
		try {
			Optional<VatConditionModel> optionalVatCondition = vatConditionService.getVatConditionById(id);

			if (optionalVatCondition.isPresent()) {
				return ResponseEntity.ok(optionalVatCondition.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> createVatCondition(@Valid @RequestBody VatConditionModel vatCondition,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = Utils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			VatConditionModel createdVatCondition = vatConditionService.createVatCondition(vatCondition);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdVatCondition);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateVatCondition(@PathVariable Integer id,
			@Valid @RequestBody VatConditionModel vatCondition, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = Utils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			Optional<VatConditionModel> optionalVatCondition = vatConditionService.updateVatCondition(id, vatCondition);

			if (optionalVatCondition.isPresent()) {
				return ResponseEntity.ok(optionalVatCondition.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<VatConditionModel> patchVatCondition(@PathVariable Integer id,
			@RequestBody VatConditionModel vatCondition) {
		try {
			Optional<VatConditionModel> optionalVatCondition = vatConditionService.patchVatCondition(id, vatCondition);

			if (optionalVatCondition.isPresent()) {
				return ResponseEntity.ok(optionalVatCondition.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<VatConditionModel> deleteVatCondition(@PathVariable Integer id) {
		try {
			Optional<VatConditionModel> optionalVatCondition = vatConditionService.deleteVatCondition(id);

			if (optionalVatCondition.isPresent()) {
				return ResponseEntity.ok(optionalVatCondition.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
