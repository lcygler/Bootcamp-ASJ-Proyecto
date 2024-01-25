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

import com.asj.api.models.supplier.TaxInformationModel;
import com.asj.api.services.supplier.TaxInformationService;

@RestController
@RequestMapping("/tax-information")
@CrossOrigin
public class TaxInformationController {

	@Autowired
	TaxInformationService taxInformationService;

	@GetMapping("/test")
	public String test() {
		return "Tax information endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<TaxInformationModel>> getAllTaxInformation() {
		return ResponseEntity.ok(taxInformationService.getAllTaxInformation());
	}

	@GetMapping("/{id}")
	public ResponseEntity<TaxInformationModel> getTaxInformationById(@PathVariable Integer id) {
		Optional<TaxInformationModel> optionalTaxInformation = taxInformationService.getTaxInformationById(id);

		if (optionalTaxInformation.isPresent()) {
			return ResponseEntity.ok(optionalTaxInformation.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<TaxInformationModel> createTaxInformation(@RequestBody TaxInformationModel taxInformation) {
		TaxInformationModel createdTaxInformation = taxInformationService.createTaxInformation(taxInformation);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdTaxInformation);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TaxInformationModel> updateTaxInformation(@PathVariable Integer id, @RequestBody TaxInformationModel taxInformation) {
		Optional<TaxInformationModel> optionalTaxInformation = taxInformationService.updateTaxInformation(id, taxInformation);

		if (optionalTaxInformation.isPresent()) {
			return ResponseEntity.ok(optionalTaxInformation.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<TaxInformationModel> patchTask(@PathVariable Integer id, @RequestBody TaxInformationModel taxInformation) {
		Optional<TaxInformationModel> optionalTaxInformation = taxInformationService.patchTaxInformation(id, taxInformation);

		if (optionalTaxInformation.isPresent()) {
			return ResponseEntity.ok(optionalTaxInformation.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<TaxInformationModel> deleteTaxInformation(@PathVariable Integer id) {
		Optional<TaxInformationModel> optionalTaxInformation = taxInformationService.deleteTaxInformation(id);

		if (optionalTaxInformation.isPresent()) {
			return ResponseEntity.ok(optionalTaxInformation.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}