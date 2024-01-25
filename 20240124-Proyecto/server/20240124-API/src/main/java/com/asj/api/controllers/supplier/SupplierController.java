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

import com.asj.api.models.supplier.SupplierModel;
import com.asj.api.models.supplier.VatConditionModel;
import com.asj.api.services.supplier.SupplierService;
import com.asj.api.services.supplier.VatConditionService;

@RestController
@RequestMapping("/suppliers")
@CrossOrigin
public class SupplierController {

	@Autowired
	SupplierService supplierService;

	@Autowired
	VatConditionService vatConditionService;
	
	@GetMapping("/test")
	public String test() {
		return "Suppliers endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<SupplierModel>> getAllSuppliers() {
		return ResponseEntity.ok(supplierService.getAllSuppliers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<SupplierModel> getSupplierById(@PathVariable Integer id) {
		Optional<SupplierModel> optionalSupplier = supplierService.getSupplierById(id);

		if (optionalSupplier.isPresent()) {
			return ResponseEntity.ok(optionalSupplier.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/vat-conditions")
	public ResponseEntity<List<VatConditionModel>> getAllVatConditions() {
		return ResponseEntity.ok(vatConditionService.getAllVatConditions());
	}
	
	@PostMapping
	public ResponseEntity<SupplierModel> createSupplier(@RequestBody SupplierModel supplier) {
		SupplierModel createdSupplier = supplierService.createSupplier(supplier);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdSupplier);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SupplierModel> updateSupplier(@PathVariable Integer id, @RequestBody SupplierModel supplier) {
		Optional<SupplierModel> optionalSupplier = supplierService.updateSupplier(id, supplier);

		if (optionalSupplier.isPresent()) {
			return ResponseEntity.ok(optionalSupplier.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<SupplierModel> patchTask(@PathVariable Integer id, @RequestBody SupplierModel supplier) {
		Optional<SupplierModel> optionalSupplier = supplierService.patchSupplier(id, supplier);

		if (optionalSupplier.isPresent()) {
			return ResponseEntity.ok(optionalSupplier.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SupplierModel> deleteSupplier(@PathVariable Integer id) {
		Optional<SupplierModel> optionalSupplier = supplierService.deleteSupplier(id);

		if (optionalSupplier.isPresent()) {
			return ResponseEntity.ok(optionalSupplier.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}