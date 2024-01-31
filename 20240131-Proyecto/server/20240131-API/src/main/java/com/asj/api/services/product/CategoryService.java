package com.asj.api.services.product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.models.product.CategoryModel;
import com.asj.api.repositories.product.CategoryRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<CategoryModel> getAllCategories() {
		return categoryRepository.findAll();
	}

	public Optional<CategoryModel> getCategoryById(Integer id) {
		return categoryRepository.findById(id);
	}

	@Transactional
	public CategoryModel createCategory(CategoryModel category) {
		category.setCreatedAt(LocalDateTime.now());
		category.setUpdatedAt(LocalDateTime.now());

		CategoryModel createdCategory = categoryRepository.save(category);
		entityManager.refresh(createdCategory);

		return createdCategory;
	}

	@Transactional
	public Optional<CategoryModel> updateCategory(Integer id, CategoryModel category) {
		Optional<CategoryModel> optionalCategory = Optional.empty();

		if (categoryRepository.existsById(id)) {
			category.setUpdatedAt(LocalDateTime.now());

			CategoryModel updatedCategory = categoryRepository.save(category);
			entityManager.refresh(updatedCategory);

			optionalCategory = Optional.of(updatedCategory);
		}

		return optionalCategory;
	}

	@Transactional
	public Optional<CategoryModel> patchCategory(Integer id, CategoryModel category) {
		Optional<CategoryModel> optionalCategory = getCategoryById(id);

		if (optionalCategory.isPresent()) {
			CategoryModel existingCategory = optionalCategory.get();

			if (category.getName() != null) {
				existingCategory.setName(category.getName());
			}

			existingCategory.setUpdatedAt(LocalDateTime.now());

			categoryRepository.save(existingCategory);
			entityManager.flush();
			entityManager.refresh(existingCategory);

			return Optional.of(existingCategory);
		} else {
			return Optional.empty();
		}
	}

	@Transactional
	public Optional<CategoryModel> deleteCategory(Integer id) {
		Optional<CategoryModel> optionalCategory = getCategoryById(id);

		if (optionalCategory.isPresent()) {
			categoryRepository.deleteById(id);
		}

		return optionalCategory;
	}

}