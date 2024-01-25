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

import com.asj.api.models.address.CountryModel;
import com.asj.api.services.address.CountryService;

@RestController
@RequestMapping("/countries")
@CrossOrigin
public class CountryController {

	@Autowired
	CountryService countryService;

	@GetMapping("/test")
	public String test() {
		return "Countries endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<CountryModel>> getAllCountries() {
		return ResponseEntity.ok(countryService.getAllCountries());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CountryModel> getCountryById(@PathVariable Integer id) {
		Optional<CountryModel> optionalCountry = countryService.getCountryById(id);

		if (optionalCountry.isPresent()) {
			return ResponseEntity.ok(optionalCountry.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<CountryModel> createCountry(@RequestBody CountryModel country) {
		CountryModel createdCountry = countryService.createCountry(country);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCountry);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CountryModel> updateCountry(@PathVariable Integer id, @RequestBody CountryModel country) {
		Optional<CountryModel> optionalCountry = countryService.updateCountry(id, country);

		if (optionalCountry.isPresent()) {
			return ResponseEntity.ok(optionalCountry.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<CountryModel> patchTask(@PathVariable Integer id, @RequestBody CountryModel country) {
		Optional<CountryModel> optionalCountry = countryService.patchCountry(id, country);

		if (optionalCountry.isPresent()) {
			return ResponseEntity.ok(optionalCountry.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CountryModel> deleteCountry(@PathVariable Integer id) {
		Optional<CountryModel> optionalCountry = countryService.deleteCountry(id);

		if (optionalCountry.isPresent()) {
			return ResponseEntity.ok(optionalCountry.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}