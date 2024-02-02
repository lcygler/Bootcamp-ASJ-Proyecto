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

import com.asj.api.exceptions.AssociatedEntitiesException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.user.RoleModel;
import com.asj.api.services.user.RoleService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	RoleService roleService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<RoleModel>> getAllRoles() {
		try {
			return ResponseEntity.ok(roleService.getAllRoles());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<RoleModel> getRoleById(@PathVariable Integer id) {
		try {
			Optional<RoleModel> optionalRole = roleService.getRoleById(id);

			if (optionalRole.isPresent()) {
				return ResponseEntity.ok(optionalRole.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<Object> createRole(@Valid @RequestBody RoleModel role, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = ValidationUtils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			RoleModel createdRole = roleService.createRole(role);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdRole);
		} catch (UniqueViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateRole(@PathVariable Integer id, @Valid @RequestBody RoleModel role,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = ValidationUtils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			Optional<RoleModel> optionalRole = roleService.updateRole(id, role);

			if (optionalRole.isPresent()) {
				return ResponseEntity.ok(optionalRole.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (UniqueViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Object> patchRole(@PathVariable Integer id, @RequestBody RoleModel role) {
		try {
			Optional<RoleModel> optionalRole = roleService.patchRole(id, role);

			if (optionalRole.isPresent()) {
				return ResponseEntity.ok(optionalRole.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (UniqueViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteRole(@PathVariable Integer id) {
		try {
			Optional<RoleModel> optionalRole = roleService.deleteRole(id);

			if (optionalRole.isPresent()) {
				return ResponseEntity.ok(optionalRole.get());
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
