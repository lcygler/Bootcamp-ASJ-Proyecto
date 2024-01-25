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

import com.asj.api.models.user.RoleModel;
import com.asj.api.services.user.RoleService;

@RestController
@RequestMapping("/roles")
@CrossOrigin
public class RoleController {

	@Autowired
	RoleService roleService;

	@GetMapping("/test")
	public String test() {
		return "Roles endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<RoleModel>> getAllRoles() {
		return ResponseEntity.ok(roleService.getAllRoles());
	}

	@GetMapping("/{id}")
	public ResponseEntity<RoleModel> getRoleById(@PathVariable Integer id) {
		Optional<RoleModel> optionalRole = roleService.getRoleById(id);

		if (optionalRole.isPresent()) {
			return ResponseEntity.ok(optionalRole.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<RoleModel> createRole(@RequestBody RoleModel role) {
		RoleModel createdRole = roleService.createRole(role);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRole);
	}

	@PutMapping("/{id}")
	public ResponseEntity<RoleModel> updateRole(@PathVariable Integer id, @RequestBody RoleModel role) {
		Optional<RoleModel> optionalRole = roleService.updateRole(id, role);

		if (optionalRole.isPresent()) {
			return ResponseEntity.ok(optionalRole.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<RoleModel> patchTask(@PathVariable Integer id, @RequestBody RoleModel role) {
		Optional<RoleModel> optionalRole = roleService.patchRole(id, role);

		if (optionalRole.isPresent()) {
			return ResponseEntity.ok(optionalRole.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<RoleModel> deleteRole(@PathVariable Integer id) {
		Optional<RoleModel> optionalRole = roleService.deleteRole(id);

		if (optionalRole.isPresent()) {
			return ResponseEntity.ok(optionalRole.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}