package com.asj.api.controllers.user;

import java.util.Collections;
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

import com.asj.api.models.user.UserModel;
import com.asj.api.services.user.UserService;
import com.asj.api.utils.Utils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/test")
	public String test() {
		return "Users endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<UserModel>> getAllUsers() {
		try {
			return ResponseEntity.ok(userService.getAllUsers());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserModel> getUserById(@PathVariable Integer id) {
		try {
			Optional<UserModel> optionalUser = userService.getUserById(id);

			if (optionalUser.isPresent()) {
				return ResponseEntity.ok(optionalUser.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> createUser(@Valid @RequestBody UserModel user, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = Utils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			UserModel createdUser = userService.createUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("El DNI ya existe");
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Integer id, @Valid @RequestBody UserModel user,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = Utils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			Optional<UserModel> optionalUser = userService.updateUser(id, user);

			if (optionalUser.isPresent()) {
				return ResponseEntity.ok(optionalUser.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UserModel> patchUser(@PathVariable Integer id, @RequestBody UserModel user) {
		try {
			Optional<UserModel> optionalUser = userService.patchUser(id, user);

			if (optionalUser.isPresent()) {
				return ResponseEntity.ok(optionalUser.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserModel> deleteUser(@PathVariable Integer id) {
		try {
			Optional<UserModel> optionalUser = userService.deleteUser(id);

			if (optionalUser.isPresent()) {
				return ResponseEntity.ok(optionalUser.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
