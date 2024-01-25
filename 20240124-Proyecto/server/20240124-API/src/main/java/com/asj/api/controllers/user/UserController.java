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

import com.asj.api.models.user.UserModel;
import com.asj.api.services.user.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/test")
	public String test() {
		return "Users endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<UserModel>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserModel> getUserById(@PathVariable Integer id) {
		Optional<UserModel> optionalUser = userService.getUserById(id);

		if (optionalUser.isPresent()) {
			return ResponseEntity.ok(optionalUser.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<UserModel> createUser(@RequestBody UserModel user) {
		UserModel createdUser = userService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserModel> updateUser(@PathVariable Integer id, @RequestBody UserModel user) {
		Optional<UserModel> optionalUser = userService.updateUser(id, user);

		if (optionalUser.isPresent()) {
			return ResponseEntity.ok(optionalUser.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UserModel> patchTask(@PathVariable Integer id, @RequestBody UserModel user) {
		Optional<UserModel> optionalUser = userService.patchUser(id, user);

		if (optionalUser.isPresent()) {
			return ResponseEntity.ok(optionalUser.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserModel> deleteUser(@PathVariable Integer id) {
		Optional<UserModel> optionalUser = userService.deleteUser(id);

		if (optionalUser.isPresent()) {
			return ResponseEntity.ok(optionalUser.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}