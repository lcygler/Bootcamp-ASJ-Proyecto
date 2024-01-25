package com.asj.api.controllers.address;

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

import com.asj.api.models.address.AddressModel;
import com.asj.api.services.address.AddressService;

@RestController
@RequestMapping("/addresses")
@CrossOrigin
public class AddressController {

	@Autowired
	AddressService addressService;

	@GetMapping("/test")
	public String test() {
		return "Addresses endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<AddressModel>> getAllAddresses() {
		return ResponseEntity.ok(addressService.getAllAddresses());
	}

	@GetMapping("/{id}")
	public ResponseEntity<AddressModel> getAddressById(@PathVariable Integer id) {
		Optional<AddressModel> optionalAddress = addressService.getAddressById(id);

		if (optionalAddress.isPresent()) {
			return ResponseEntity.ok(optionalAddress.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<AddressModel> createAddress(@RequestBody AddressModel address) {
		AddressModel createdAddress = addressService.createAddress(address);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AddressModel> updateAddress(@PathVariable Integer id, @RequestBody AddressModel address) {
		Optional<AddressModel> optionalAddress = addressService.updateAddress(id, address);

		if (optionalAddress.isPresent()) {
			return ResponseEntity.ok(optionalAddress.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<AddressModel> patchTask(@PathVariable Integer id, @RequestBody AddressModel address) {
		Optional<AddressModel> optionalAddress = addressService.patchAddress(id, address);

		if (optionalAddress.isPresent()) {
			return ResponseEntity.ok(optionalAddress.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<AddressModel> deleteAddress(@PathVariable Integer id) {
		Optional<AddressModel> optionalAddress = addressService.deleteAddress(id);

		if (optionalAddress.isPresent()) {
			return ResponseEntity.ok(optionalAddress.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}