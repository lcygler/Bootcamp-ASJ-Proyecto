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

import com.asj.api.exceptions.AssociatedEntitiesExistException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.supplier.IndustryModel;
import com.asj.api.services.supplier.IndustryService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/industries")
public class IndustryController {

	@Autowired
	IndustryService industryService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<IndustryModel>> getAllIndustries() {
		try {
			return ResponseEntity.ok(industryService.getAllIndustries());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<IndustryModel> getIndustryById(@PathVariable Integer id) {
		try {
			Optional<IndustryModel> optionalIndustry = industryService.getIndustryById(id);

			if (optionalIndustry.isPresent()) {
				return ResponseEntity.ok(optionalIndustry.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<Object> createIndustry(@Valid @RequestBody IndustryModel industry,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = ValidationUtils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			IndustryModel createdIndustry = industryService.createIndustry(industry);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdIndustry);
		} catch (UniqueViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateIndustry(@PathVariable Integer id, @Valid @RequestBody IndustryModel industry,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = ValidationUtils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			Optional<IndustryModel> optionalIndustry = industryService.updateIndustry(id, industry);

			if (optionalIndustry.isPresent()) {
				return ResponseEntity.ok(optionalIndustry.get());
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
	public ResponseEntity<Object> patchIndustry(@PathVariable Integer id, @RequestBody IndustryModel industry) {
		try {
			Optional<IndustryModel> optionalIndustry = industryService.patchIndustry(id, industry);

			if (optionalIndustry.isPresent()) {
				return ResponseEntity.ok(optionalIndustry.get());
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
	public ResponseEntity<Object> deleteIndustry(@PathVariable Integer id) {
		try {
			Optional<IndustryModel> optionalIndustry = industryService.deleteIndustry(id);

			if (optionalIndustry.isPresent()) {
				return ResponseEntity.ok(optionalIndustry.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (AssociatedEntitiesExistException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
