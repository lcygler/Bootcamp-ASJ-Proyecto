package com.asj.api.controllers.user;

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

import com.asj.api.models.user.UserCredentialModel;
import com.asj.api.services.user.UserCredentialService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user-credentials")
public class UserCredentialController {

	@Autowired
	UserCredentialService userCredentialService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<UserCredentialModel>> getAllUserCredentials() {
		try {
			return ResponseEntity.ok(userCredentialService.getAllUserCredentials());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserCredentialModel> getUserCredentialById(@PathVariable Integer id) {
		try {
			Optional<UserCredentialModel> optionalUserCredential = userCredentialService.getUserCredentialById(id);

			if (optionalUserCredential.isPresent()) {
				return ResponseEntity.ok(optionalUserCredential.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> createUserCredential(@Valid @RequestBody UserCredentialModel userCredential,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = ValidationUtils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			UserCredentialModel createdUserCredential = userCredentialService.createUserCredential(userCredential);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdUserCredential);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUserCredential(@PathVariable Integer id,
			@Valid @RequestBody UserCredentialModel userCredential, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = ValidationUtils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			Optional<UserCredentialModel> optionalUserCredential = userCredentialService.updateUserCredential(id,
					userCredential);

			if (optionalUserCredential.isPresent()) {
				return ResponseEntity.ok(optionalUserCredential.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UserCredentialModel> patchUserCredential(@PathVariable Integer id,
			@RequestBody UserCredentialModel userCredential) {
		try {
			Optional<UserCredentialModel> optionalUserCredential = userCredentialService.patchUserCredential(id,
					userCredential);

			if (optionalUserCredential.isPresent()) {
				return ResponseEntity.ok(optionalUserCredential.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserCredentialModel> deleteUserCredential(@PathVariable Integer id) {
		try {
			Optional<UserCredentialModel> optionalUserCredential = userCredentialService.deleteUserCredential(id);

			if (optionalUserCredential.isPresent()) {
				return ResponseEntity.ok(optionalUserCredential.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}
}
