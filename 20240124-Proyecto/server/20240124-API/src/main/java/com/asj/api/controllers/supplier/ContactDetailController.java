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

import com.asj.api.models.supplier.ContactDetailModel;
import com.asj.api.services.supplier.ContactDetailService;

@RestController
@RequestMapping("/contact-details")
@CrossOrigin
public class ContactDetailController {

	@Autowired
	ContactDetailService contactDetailService;

	@GetMapping("/test")
	public String test() {
		return "ContactDetails endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<ContactDetailModel>> getAllContactDetails() {
		return ResponseEntity.ok(contactDetailService.getAllContactDetails());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContactDetailModel> getContactDetailById(@PathVariable Integer id) {
		Optional<ContactDetailModel> optionalContactDetail = contactDetailService.getContactDetailById(id);

		if (optionalContactDetail.isPresent()) {
			return ResponseEntity.ok(optionalContactDetail.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<ContactDetailModel> createContactDetail(@RequestBody ContactDetailModel contactDetail) {
		ContactDetailModel createdContactDetail = contactDetailService.createContactDetail(contactDetail);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdContactDetail);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ContactDetailModel> updateContactDetail(@PathVariable Integer id, @RequestBody ContactDetailModel contactDetail) {
		Optional<ContactDetailModel> optionalContactDetail = contactDetailService.updateContactDetail(id, contactDetail);

		if (optionalContactDetail.isPresent()) {
			return ResponseEntity.ok(optionalContactDetail.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ContactDetailModel> patchTask(@PathVariable Integer id, @RequestBody ContactDetailModel contactDetail) {
		Optional<ContactDetailModel> optionalContactDetail = contactDetailService.patchContactDetail(id, contactDetail);

		if (optionalContactDetail.isPresent()) {
			return ResponseEntity.ok(optionalContactDetail.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ContactDetailModel> deleteContactDetail(@PathVariable Integer id) {
		Optional<ContactDetailModel> optionalContactDetail = contactDetailService.deleteContactDetail(id);

		if (optionalContactDetail.isPresent()) {
			return ResponseEntity.ok(optionalContactDetail.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}