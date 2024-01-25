package com.asj.api.controllers.product;

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

import com.asj.api.models.product.CategoryModel;
import com.asj.api.services.product.CategoryService;

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/test")
	public String test() {
		return "Categories endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<CategoryModel>> getAllCategories() {
		return ResponseEntity.ok(categoryService.getAllCategories());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryModel> getCategoryById(@PathVariable Integer id) {
		Optional<CategoryModel> optionalCategory = categoryService.getCategoryById(id);

		if (optionalCategory.isPresent()) {
			return ResponseEntity.ok(optionalCategory.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<CategoryModel> createCategory(@RequestBody CategoryModel category) {
		CategoryModel createdCategory = categoryService.createCategory(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoryModel> updateCategory(@PathVariable Integer id, @RequestBody CategoryModel category) {
		Optional<CategoryModel> optionalCategory = categoryService.updateCategory(id, category);

		if (optionalCategory.isPresent()) {
			return ResponseEntity.ok(optionalCategory.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<CategoryModel> patchTask(@PathVariable Integer id, @RequestBody CategoryModel category) {
		Optional<CategoryModel> optionalCategory = categoryService.patchCategory(id, category);

		if (optionalCategory.isPresent()) {
			return ResponseEntity.ok(optionalCategory.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CategoryModel> deleteCategory(@PathVariable Integer id) {
		Optional<CategoryModel> optionalCategory = categoryService.deleteCategory(id);

		if (optionalCategory.isPresent()) {
			return ResponseEntity.ok(optionalCategory.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}