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

import com.asj.api.models.user.GenreModel;
import com.asj.api.services.user.GenreService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/genres")
public class GenreController {

	@Autowired
	GenreService genreService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<GenreModel>> getAllGenres() {
		try {
			return ResponseEntity.ok(genreService.getAllGenres());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<GenreModel> getGenreById(@PathVariable Integer id) {
		try {
			Optional<GenreModel> optionalGenre = genreService.getGenreById(id);

			if (optionalGenre.isPresent()) {
				return ResponseEntity.ok(optionalGenre.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> createGenre(@Valid @RequestBody GenreModel genre, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = ValidationUtils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			GenreModel createdGenre = genreService.createGenre(genre);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdGenre);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateGenre(@PathVariable Integer id, @Valid @RequestBody GenreModel genre,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = ValidationUtils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			Optional<GenreModel> optionalGenre = genreService.updateGenre(id, genre);

			if (optionalGenre.isPresent()) {
				return ResponseEntity.ok(optionalGenre.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<GenreModel> patchGenre(@PathVariable Integer id, @RequestBody GenreModel genre) {
		try {
			Optional<GenreModel> optionalGenre = genreService.patchGenre(id, genre);

			if (optionalGenre.isPresent()) {
				return ResponseEntity.ok(optionalGenre.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<GenreModel> deleteGenre(@PathVariable Integer id) {
		try {
			Optional<GenreModel> optionalGenre = genreService.deleteGenre(id);

			if (optionalGenre.isPresent()) {
				return ResponseEntity.ok(optionalGenre.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage()); 
			return ResponseEntity.internalServerError().build();
		}
	}
}
