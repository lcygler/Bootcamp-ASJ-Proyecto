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

import com.asj.api.models.user.GenreModel;
import com.asj.api.services.user.GenreService;

@RestController
@RequestMapping("/genres")
@CrossOrigin
public class GenreController {

	@Autowired
	GenreService genreService;

	@GetMapping("/test")
	public String test() {
		return "Genres endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<GenreModel>> getAllGenres() {
		return ResponseEntity.ok(genreService.getAllGenres());
	}

	@GetMapping("/{id}")
	public ResponseEntity<GenreModel> getGenreById(@PathVariable Integer id) {
		Optional<GenreModel> optionalGenre = genreService.getGenreById(id);

		if (optionalGenre.isPresent()) {
			return ResponseEntity.ok(optionalGenre.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<GenreModel> createGenre(@RequestBody GenreModel genre) {
		GenreModel createdGenre = genreService.createGenre(genre);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdGenre);
	}

	@PutMapping("/{id}")
	public ResponseEntity<GenreModel> updateGenre(@PathVariable Integer id, @RequestBody GenreModel genre) {
		Optional<GenreModel> optionalGenre = genreService.updateGenre(id, genre);

		if (optionalGenre.isPresent()) {
			return ResponseEntity.ok(optionalGenre.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<GenreModel> patchTask(@PathVariable Integer id, @RequestBody GenreModel genre) {
		Optional<GenreModel> optionalGenre = genreService.patchGenre(id, genre);

		if (optionalGenre.isPresent()) {
			return ResponseEntity.ok(optionalGenre.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<GenreModel> deleteGenre(@PathVariable Integer id) {
		Optional<GenreModel> optionalGenre = genreService.deleteGenre(id);

		if (optionalGenre.isPresent()) {
			return ResponseEntity.ok(optionalGenre.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}