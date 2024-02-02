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
import com.asj.api.exceptions.InvalidIdentifierException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.supplier.TaxInformationModel;
import com.asj.api.services.supplier.TaxInformationService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tax-information")
public class TaxInformationController {

	@Autowired
	TaxInformationService taxInformationService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<TaxInformationModel>> getAllTaxInformation() {
		try {
			return ResponseEntity.ok(taxInformationService.getAllTaxInformation());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<TaxInformationModel> getTaxInformationById(@PathVariable Integer id) {
		try {
			Optional<TaxInformationModel> optionalTaxInformation = taxInformationService.getTaxInformationById(id);

			if (optionalTaxInformation.isPresent()) {
				return ResponseEntity.ok(optionalTaxInformation.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<Object> createTaxInformation(@Valid @RequestBody TaxInformationModel taxInformation,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = ValidationUtils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			TaxInformationModel createdTaxInformation = taxInformationService.createTaxInformation(taxInformation);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdTaxInformation);
		} catch (UniqueViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (InvalidIdentifierException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateTaxInformation(@PathVariable Integer id,
			@Valid @RequestBody TaxInformationModel taxInformation, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = ValidationUtils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			Optional<TaxInformationModel> optionalTaxInformation = taxInformationService.updateTaxInformation(id,
					taxInformation);

			if (optionalTaxInformation.isPresent()) {
				return ResponseEntity.ok(optionalTaxInformation.get());
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
	public ResponseEntity<Object> patchTaxInformation(@PathVariable Integer id,
			@RequestBody TaxInformationModel taxInformation) {
		try {
			Optional<TaxInformationModel> optionalTaxInformation = taxInformationService.patchTaxInformation(id,
					taxInformation);

			if (optionalTaxInformation.isPresent()) {
				return ResponseEntity.ok(optionalTaxInformation.get());
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
	public ResponseEntity<Object> deleteTaxInformation(@PathVariable Integer id) {
		try {
			Optional<TaxInformationModel> optionalTaxInformation = taxInformationService.deleteTaxInformation(id);

			if (optionalTaxInformation.isPresent()) {
				return ResponseEntity.ok(optionalTaxInformation.get());
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
