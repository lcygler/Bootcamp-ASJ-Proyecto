package com.asj.api.controllers.user;

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

import com.asj.api.models.user.UserCredentialModel;
import com.asj.api.services.user.UserCredentialService;

@RestController
@RequestMapping("/user-credentials")
@CrossOrigin
public class UserCredentialController {

	@Autowired
	UserCredentialService userCredentialService;

	@GetMapping("/test")
	public String test() {
		return "User credentials endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<UserCredentialModel>> getAllUserCredentials() {
		return ResponseEntity.ok(userCredentialService.getAllUserCredentials());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserCredentialModel> getUserCredentialById(@PathVariable Integer id) {
		Optional<UserCredentialModel> optionalUserCredential = userCredentialService.getUserCredentialById(id);

		if (optionalUserCredential.isPresent()) {
			return ResponseEntity.ok(optionalUserCredential.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<UserCredentialModel> createUserCredential(@RequestBody UserCredentialModel userCredential) {
		UserCredentialModel createdUserCredential = userCredentialService.createUserCredential(userCredential);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUserCredential);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserCredentialModel> updateUserCredential(@PathVariable Integer id, @RequestBody UserCredentialModel userCredential) {
		Optional<UserCredentialModel> optionalUserCredential = userCredentialService.updateUserCredential(id, userCredential);

		if (optionalUserCredential.isPresent()) {
			return ResponseEntity.ok(optionalUserCredential.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UserCredentialModel> patchTask(@PathVariable Integer id, @RequestBody UserCredentialModel userCredential) {
		Optional<UserCredentialModel> optionalUserCredential = userCredentialService.patchUserCredential(id, userCredential);

		if (optionalUserCredential.isPresent()) {
			return ResponseEntity.ok(optionalUserCredential.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserCredentialModel> deleteUserCredential(@PathVariable Integer id) {
		Optional<UserCredentialModel> optionalUserCredential = userCredentialService.deleteUserCredential(id);

		if (optionalUserCredential.isPresent()) {
			return ResponseEntity.ok(optionalUserCredential.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}