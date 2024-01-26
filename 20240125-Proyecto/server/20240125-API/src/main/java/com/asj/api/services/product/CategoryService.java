package com.asj.api.services.product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asj.api.models.product.CategoryModel;
import com.asj.api.repositories.product.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public List<CategoryModel> getAllCategories() {
		return categoryRepository.findAll();
	}

	public Optional<CategoryModel> getCategoryById(Integer id) {
		return categoryRepository.findById(id);
	}

	public CategoryModel createCategory(CategoryModel category) {
		category.setCreatedAt(LocalDateTime.now());
		category.setUpdatedAt(LocalDateTime.now());

		return categoryRepository.save(category);
	}

	public Optional<CategoryModel> updateCategory(Integer id, CategoryModel category) {
		Optional<CategoryModel> optionalCategory = Optional.empty();

		if (categoryRepository.existsById(id)) {
			category.setUpdatedAt(LocalDateTime.now());
			optionalCategory = Optional.of(categoryRepository.save(category));
		}

		return optionalCategory;
	}

	public Optional<CategoryModel> patchCategory(Integer id, CategoryModel category) {
		Optional<CategoryModel> optionalCategory = getCategoryById(id);

		if (optionalCategory.isPresent()) {
			CategoryModel existingCategory = optionalCategory.get();

			if (category.getName() != null) {
				existingCategory.setName(category.getName());
			}
			
			existingCategory.setUpdatedAt(LocalDateTime.now());

			return Optional.of(categoryRepository.save(existingCategory));
		} else {
			return Optional.empty();
		}
	}

	public Optional<CategoryModel> deleteCategory(Integer id) {
		Optional<CategoryModel> optionalCategory = getCategoryById(id);

		if (optionalCategory.isPresent()) {
			categoryRepository.deleteById(id);
		}

		return optionalCategory;
	}

}