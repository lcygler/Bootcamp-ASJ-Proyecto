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

import com.asj.api.models.supplier.ContactDetailModel;
import com.asj.api.services.supplier.ContactDetailService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contact-details")
public class ContactDetailController {

	@Autowired
	ContactDetailService contactDetailService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<ContactDetailModel>> getAllContactDetails() {
		try {
			return ResponseEntity.ok(contactDetailService.getAllContactDetails());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContactDetailModel> getContactDetailById(@PathVariable Integer id) {
		try {
			Optional<ContactDetailModel> optionalContactDetail = contactDetailService.getContactDetailById(id);

			if (optionalContactDetail.isPresent()) {
				return ResponseEntity.ok(optionalContactDetail.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> createContactDetail(@Valid @RequestBody ContactDetailModel contactDetail,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = ValidationUtils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			ContactDetailModel createdContactDetail = contactDetailService.createContactDetail(contactDetail);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdContactDetail);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateContactDetail(@PathVariable Integer id,
			@Valid @RequestBody ContactDetailModel contactDetail, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = ValidationUtils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			Optional<ContactDetailModel> optionalContactDetail = contactDetailService.updateContactDetail(id,
					contactDetail);

			if (optionalContactDetail.isPresent()) {
				return ResponseEntity.ok(optionalContactDetail.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ContactDetailModel> patchContactDetail(@PathVariable Integer id,
			@RequestBody ContactDetailModel contactDetail) {
		try {
			Optional<ContactDetailModel> optionalContactDetail = contactDetailService.patchContactDetail(id,
					contactDetail);

			if (optionalContactDetail.isPresent()) {
				return ResponseEntity.ok(optionalContactDetail.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ContactDetailModel> deleteContactDetail(@PathVariable Integer id) {
		try {
			Optional<ContactDetailModel> optionalContactDetail = contactDetailService.deleteContactDetail(id);

			if (optionalContactDetail.isPresent()) {
				return ResponseEntity.ok(optionalContactDetail.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}
}
