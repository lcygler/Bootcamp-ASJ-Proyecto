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

import com.asj.api.models.address.StateModel;
import com.asj.api.services.address.StateService;

@RestController
@RequestMapping("/states")
@CrossOrigin
public class StateController {

	@Autowired
	StateService stateService;

	@GetMapping("/test")
	public String test() {
		return "States endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<StateModel>> getAllStates() {
		return ResponseEntity.ok(stateService.getAllStates());
	}

	@GetMapping("/{id}")
	public ResponseEntity<StateModel> getStateById(@PathVariable Integer id) {
		Optional<StateModel> optionalState = stateService.getStateById(id);

		if (optionalState.isPresent()) {
			return ResponseEntity.ok(optionalState.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<StateModel> createState(@RequestBody StateModel state) {
		StateModel createdState = stateService.createState(state);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdState);
	}

	@PutMapping("/{id}")
	public ResponseEntity<StateModel> updateState(@PathVariable Integer id, @RequestBody StateModel state) {
		Optional<StateModel> optionalState = stateService.updateState(id, state);

		if (optionalState.isPresent()) {
			return ResponseEntity.ok(optionalState.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<StateModel> patchTask(@PathVariable Integer id, @RequestBody StateModel state) {
		Optional<StateModel> optionalState = stateService.patchState(id, state);

		if (optionalState.isPresent()) {
			return ResponseEntity.ok(optionalState.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<StateModel> deleteState(@PathVariable Integer id) {
		Optional<StateModel> optionalState = stateService.deleteState(id);

		if (optionalState.isPresent()) {
			return ResponseEntity.ok(optionalState.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}