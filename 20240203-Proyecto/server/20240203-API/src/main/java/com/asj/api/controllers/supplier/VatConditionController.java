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

import com.asj.api.exceptions.AssociatedEntitiesException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.exceptions.ValidationErrorException;
import com.asj.api.models.product.CategoryModel;
import com.asj.api.models.supplier.VatConditionModel;
import com.asj.api.services.supplier.VatConditionService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vat-conditions")
public class VatConditionController {

	@Autowired
	VatConditionService vatConditionService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<VatConditionModel>> getAllVatConditions() {
		List<VatConditionModel> vatConditions = vatConditionService.getAllVatConditions();
		return ResponseEntity.ok(vatConditions);
	}

	@GetMapping("/{id}")
	public ResponseEntity<VatConditionModel> getVatConditionById(@PathVariable Integer id) {
		VatConditionModel vatCondition = vatConditionService.getVatConditionById(id);
		return ResponseEntity.ok(vatCondition);
	}

	@PostMapping
	public ResponseEntity<VatConditionModel> createVatCondition(@Valid @RequestBody VatConditionModel vatCondition,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = ValidationUtils.handleErrors(bindingResult);
			throw new ValidationErrorException(errors);
		}

		VatConditionModel createdVatCondition = vatConditionService.createVatCondition(vatCondition);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdVatCondition);
	}

	@PutMapping("/{id}")
	public ResponseEntity<VatConditionModel> updateVatCondition(@PathVariable Integer id,
			@Valid @RequestBody VatConditionModel vatCondition, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = ValidationUtils.handleErrors(bindingResult);
			throw new ValidationErrorException(errors);
		}

		VatConditionModel updatedVatCondition = vatConditionService.updateVatCondition(id, vatCondition);
		return ResponseEntity.ok(updatedVatCondition);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<VatConditionModel> patchVatCondition(@PathVariable Integer id, @RequestBody VatConditionModel vatCondition) {
		VatConditionModel patchedVatCondition = vatConditionService.patchVatCondition(id, vatCondition);
		return ResponseEntity.ok(patchedVatCondition);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<VatConditionModel> deleteVatCondition(@PathVariable Integer id) {
		VatConditionModel deletedVatCondition = vatConditionService.deleteVatCondition(id);
		return ResponseEntity.ok(deletedVatCondition);
	}
}