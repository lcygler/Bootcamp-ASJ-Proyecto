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

import com.asj.api.models.supplier.IndustryModel;
import com.asj.api.services.supplier.IndustryService;

@RestController
@RequestMapping("/industries")
@CrossOrigin
public class IndustryController {

	@Autowired
	IndustryService industryService;

	@GetMapping("/test")
	public String test() {
		return "Industries endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<IndustryModel>> getAllIndustries() {
		return ResponseEntity.ok(industryService.getAllIndustries());
	}

	@GetMapping("/{id}")
	public ResponseEntity<IndustryModel> getIndustryById(@PathVariable Integer id) {
		Optional<IndustryModel> optionalIndustry = industryService.getIndustryById(id);

		if (optionalIndustry.isPresent()) {
			return ResponseEntity.ok(optionalIndustry.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<IndustryModel> createIndustry(@RequestBody IndustryModel industry) {
		IndustryModel createdIndustry = industryService.createIndustry(industry);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdIndustry);
	}

	@PutMapping("/{id}")
	public ResponseEntity<IndustryModel> updateIndustry(@PathVariable Integer id, @RequestBody IndustryModel industry) {
		Optional<IndustryModel> optionalIndustry = industryService.updateIndustry(id, industry);

		if (optionalIndustry.isPresent()) {
			return ResponseEntity.ok(optionalIndustry.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<IndustryModel> patchTask(@PathVariable Integer id, @RequestBody IndustryModel industry) {
		Optional<IndustryModel> optionalIndustry = industryService.patchIndustry(id, industry);

		if (optionalIndustry.isPresent()) {
			return ResponseEntity.ok(optionalIndustry.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<IndustryModel> deleteIndustry(@PathVariable Integer id) {
		Optional<IndustryModel> optionalIndustry = industryService.deleteIndustry(id);

		if (optionalIndustry.isPresent()) {
			return ResponseEntity.ok(optionalIndustry.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}