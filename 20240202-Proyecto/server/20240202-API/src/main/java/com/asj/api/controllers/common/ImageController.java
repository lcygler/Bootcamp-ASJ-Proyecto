package com.asj.api.controllers.common;

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
import com.asj.api.models.common.ImageModel;
import com.asj.api.services.common.ImageService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/images")
public class ImageController {

	@Autowired
	ImageService imageService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<ImageModel>> getAllImages() {
		try {
			return ResponseEntity.ok(imageService.getAllImages());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ImageModel> getImageById(@PathVariable Integer id) {
		try {
			Optional<ImageModel> optionalImage = imageService.getImageById(id);

			if (optionalImage.isPresent()) {
				return ResponseEntity.ok(optionalImage.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<Object> createImage(@Valid @RequestBody ImageModel image, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = ValidationUtils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			ImageModel createdImage = imageService.createImage(image);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdImage);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateImage(@PathVariable Integer id, @Valid @RequestBody ImageModel image,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = ValidationUtils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			Optional<ImageModel> optionalImage = imageService.updateImage(id, image);

			if (optionalImage.isPresent()) {
				return ResponseEntity.ok(optionalImage.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ImageModel> patchImage(@PathVariable Integer id, @RequestBody ImageModel image) {
		try {
			Optional<ImageModel> optionalImage = imageService.patchImage(id, image);

			if (optionalImage.isPresent()) {
				return ResponseEntity.ok(optionalImage.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteImage(@PathVariable Integer id) {
		try {
			Optional<ImageModel> optionalImage = imageService.deleteImage(id);

			if (optionalImage.isPresent()) {
				return ResponseEntity.ok(optionalImage.get());
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
