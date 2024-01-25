package com.asj.api.controllers.common;

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

import com.asj.api.models.common.ImageModel;
import com.asj.api.services.common.ImageService;

@RestController
@RequestMapping("/images")
@CrossOrigin
public class ImageController {

	@Autowired
	ImageService imageService;

	@GetMapping("/test")
	public String test() {
		return "Images endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<ImageModel>> getAllImages() {
		return ResponseEntity.ok(imageService.getAllImages());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ImageModel> getImageById(@PathVariable Integer id) {
		Optional<ImageModel> optionalImage = imageService.getImageById(id);

		if (optionalImage.isPresent()) {
			return ResponseEntity.ok(optionalImage.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<ImageModel> createImage(@RequestBody ImageModel image) {
		ImageModel createdImage = imageService.createImage(image);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdImage);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ImageModel> updateImage(@PathVariable Integer id, @RequestBody ImageModel image) {
		Optional<ImageModel> optionalImage = imageService.updateImage(id, image);

		if (optionalImage.isPresent()) {
			return ResponseEntity.ok(optionalImage.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ImageModel> patchTask(@PathVariable Integer id, @RequestBody ImageModel image) {
		Optional<ImageModel> optionalImage = imageService.patchImage(id, image);

		if (optionalImage.isPresent()) {
			return ResponseEntity.ok(optionalImage.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ImageModel> deleteImage(@PathVariable Integer id) {
		Optional<ImageModel> optionalImage = imageService.deleteImage(id);

		if (optionalImage.isPresent()) {
			return ResponseEntity.ok(optionalImage.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}