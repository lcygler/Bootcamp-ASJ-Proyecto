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

import com.asj.api.models.address.CountryModel;
import com.asj.api.services.address.CountryService;
import com.asj.api.utils.Utils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/countries")
public class CountryController {

	@Autowired
	CountryService countryService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<CountryModel>> getAllCountries() {
		try {
			return ResponseEntity.ok(countryService.getAllCountries());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<CountryModel> getCountryById(@PathVariable Integer id) {
		try {
			Optional<CountryModel> optionalCountry = countryService.getCountryById(id);

			if (optionalCountry.isPresent()) {
				return ResponseEntity.ok(optionalCountry.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> createCountry(@Valid @RequestBody CountryModel country, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = Utils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			CountryModel createdCountry = countryService.createCountry(country);
			return ResponseEntity.status(HttpStatus.CREATED).body("Country created successfully");
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCountry(@PathVariable Integer id, @Valid @RequestBody CountryModel country,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = Utils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			Optional<CountryModel> optionalCountry = countryService.updateCountry(id, country);

			if (optionalCountry.isPresent()) {
				return ResponseEntity.ok(optionalCountry.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<CountryModel> patchCountry(@PathVariable Integer id, @RequestBody CountryModel country) {
		try {
			Optional<CountryModel> optionalCountry = countryService.patchCountry(id, country);

			if (optionalCountry.isPresent()) {
				return ResponseEntity.ok(optionalCountry.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CountryModel> deleteCountry(@PathVariable Integer id) {
		try {
			Optional<CountryModel> optionalCountry = countryService.deleteCountry(id);

			if (optionalCountry.isPresent()) {
				return ResponseEntity.ok(optionalCountry.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
