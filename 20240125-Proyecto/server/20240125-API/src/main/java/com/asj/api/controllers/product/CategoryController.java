package com.asj.api.controllers.product;

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

import com.asj.api.models.product.CategoryModel;
import com.asj.api.services.product.CategoryService;
import com.asj.api.utils.Utils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/test")
	public String test() {
		return "Categories endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<CategoryModel>> getAllCategories() {
		try {
			return ResponseEntity.ok(categoryService.getAllCategories());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryModel> getCategoryById(@PathVariable Integer id) {
		try {
			Optional<CategoryModel> optionalCategory = categoryService.getCategoryById(id);

			if (optionalCategory.isPresent()) {
				return ResponseEntity.ok(optionalCategory.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryModel category, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = Utils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			CategoryModel createdCategory = categoryService.createCategory(category);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoryModel category,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = Utils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			Optional<CategoryModel> optionalCategory = categoryService.updateCategory(id, category);

			if (optionalCategory.isPresent()) {
				return ResponseEntity.ok(optionalCategory.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<CategoryModel> patchCategory(@PathVariable Integer id, @RequestBody CategoryModel category) {
		try {
			Optional<CategoryModel> optionalCategory = categoryService.patchCategory(id, category);

			if (optionalCategory.isPresent()) {
				return ResponseEntity.ok(optionalCategory.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CategoryModel> deleteCategory(@PathVariable Integer id) {
		try {
			Optional<CategoryModel> optionalCategory = categoryService.deleteCategory(id);

			if (optionalCategory.isPresent()) {
				return ResponseEntity.ok(optionalCategory.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
