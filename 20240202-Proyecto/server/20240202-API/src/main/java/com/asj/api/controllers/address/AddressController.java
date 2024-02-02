package com.asj.api.controllers.address;

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
import com.asj.api.models.address.AddressModel;
import com.asj.api.services.address.AddressService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/addresses")
public class AddressController {

	@Autowired
	AddressService addressService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<AddressModel>> getAllAddresses() {
		return ResponseEntity.ok(addressService.getAllAddresses());
	}

	@GetMapping("/{id}")
	public ResponseEntity<AddressModel> getAddressById(@PathVariable Integer id) {
		try {
			Optional<AddressModel> optionalAddress = addressService.getAddressById(id);

			if (optionalAddress.isPresent()) {
				return ResponseEntity.ok(optionalAddress.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<Object> createAddress(@Valid @RequestBody AddressModel address, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = ValidationUtils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			AddressModel createdAddress = addressService.createAddress(address);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
		} catch (InvalidIdentifierException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateAddress(@PathVariable Integer id, @Valid @RequestBody AddressModel address,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = ValidationUtils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			Optional<AddressModel> optionalAddress = addressService.updateAddress(id, address);

			if (optionalAddress.isPresent()) {
				return ResponseEntity.ok(optionalAddress.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (InvalidIdentifierException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Object> patchAddress(@PathVariable Integer id, @RequestBody AddressModel address) {
		try {
			Optional<AddressModel> optionalAddress = addressService.patchAddress(id, address);

			if (optionalAddress.isPresent()) {
				return ResponseEntity.ok(optionalAddress.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (InvalidIdentifierException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteAddress(@PathVariable Integer id) {
		try {
			Optional<AddressModel> optionalAddress = addressService.deleteAddress(id);

			if (optionalAddress.isPresent()) {
				return ResponseEntity.ok(optionalAddress.get());
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
