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

import com.asj.api.models.address.StateModel;
import com.asj.api.services.address.StateService;
import com.asj.api.utils.Utils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/states")
public class StateController {

	@Autowired
	StateService stateService;

	@GetMapping("/test")
	public String test() {
		return "States endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<StateModel>> getAllStates() {
		try {
			return ResponseEntity.ok(stateService.getAllStates());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<StateModel> getStateById(@PathVariable Integer id) {
		try {
			Optional<StateModel> optionalState = stateService.getStateById(id);

			if (optionalState.isPresent()) {
				return ResponseEntity.ok(optionalState.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> createState(@Valid @RequestBody StateModel state, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = Utils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			StateModel createdState = stateService.createState(state);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdState);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateState(@PathVariable Integer id, @Valid @RequestBody StateModel state,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = Utils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			Optional<StateModel> optionalState = stateService.updateState(id, state);

			if (optionalState.isPresent()) {
				return ResponseEntity.ok(optionalState.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<StateModel> patchState(@PathVariable Integer id, @RequestBody StateModel state) {
		try {
			Optional<StateModel> optionalState = stateService.patchState(id, state);

			if (optionalState.isPresent()) {
				return ResponseEntity.ok(optionalState.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<StateModel> deleteState(@PathVariable Integer id) {
		try {
			Optional<StateModel> optionalState = stateService.deleteState(id);

			if (optionalState.isPresent()) {
				return ResponseEntity.ok(optionalState.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
