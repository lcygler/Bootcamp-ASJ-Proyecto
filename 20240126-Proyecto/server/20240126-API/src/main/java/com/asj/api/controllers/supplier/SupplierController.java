package com.asj.api.controllers.supplier;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.asj.api.models.supplier.SupplierModel;
import com.asj.api.services.supplier.SupplierService;
import com.asj.api.utils.Utils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

	@Autowired
	SupplierService supplierService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<SupplierModel>> getAllSuppliers() {
		try {
			return ResponseEntity.ok(supplierService.getAllSuppliers());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<SupplierModel> getSupplierById(@PathVariable Integer id) {
		try {
			Optional<SupplierModel> optionalSupplier = supplierService.getSupplierById(id);

			if (optionalSupplier.isPresent()) {
				return ResponseEntity.ok(optionalSupplier.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> createSupplier(@Valid @RequestBody SupplierModel supplier, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = Utils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			SupplierModel createdSupplier = supplierService.createSupplier(supplier);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdSupplier);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Supplier code already exists");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateSupplier(@PathVariable Integer id, @Valid @RequestBody SupplierModel supplier,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = Utils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			Optional<SupplierModel> optionalSupplier = supplierService.updateSupplier(id, supplier);

			if (optionalSupplier.isPresent()) {
				return ResponseEntity.ok(optionalSupplier.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<SupplierModel> patchSupplier(@PathVariable Integer id, @RequestBody SupplierModel supplier) {
		try {
			Optional<SupplierModel> optionalSupplier = supplierService.patchSupplier(id, supplier);

			if (optionalSupplier.isPresent()) {
				return ResponseEntity.ok(optionalSupplier.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SupplierModel> deleteSupplier(@PathVariable Integer id) {
		try {
			Optional<SupplierModel> optionalSupplier = supplierService.deleteSupplier(id);

			if (optionalSupplier.isPresent()) {
				return ResponseEntity.ok(optionalSupplier.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}
}
