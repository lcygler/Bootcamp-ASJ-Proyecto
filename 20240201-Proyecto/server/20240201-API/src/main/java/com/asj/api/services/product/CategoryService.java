package com.asj.api.services.product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesExistException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.product.CategoryModel;
import com.asj.api.repositories.product.CategoryRepository;
import com.asj.api.repositories.product.ProductRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ProductRepository productRepository;

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
		if (!isNameUnique(category.getName())) {
			throw new UniqueViolationException("Name must be unique");
		}

		category.setCreatedAt(LocalDateTime.now());
		category.setUpdatedAt(LocalDateTime.now());

		CategoryModel createdCategory = categoryRepository.save(category);
		entityManager.refresh(createdCategory);

		return createdCategory;
	}

	@Transactional
	public Optional<CategoryModel> updateCategory(Integer id, CategoryModel category) {
		if (!isNameUniqueAndIdNot(category.getName(), id)) {
			throw new UniqueViolationException("Name must be unique");
		}

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
				if (!isNameUniqueAndIdNot(category.getName(), id)) {
					throw new UniqueViolationException("Name must be unique");
				}
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
		if (productRepository.countByCategory(id) > 0) {
			throw new AssociatedEntitiesExistException("Deletion failed: Associated entities found");
		}
		
		Optional<CategoryModel> optionalCategory = getCategoryById(id);

		if (optionalCategory.isPresent()) {
			categoryRepository.deleteById(id);
		}

		return optionalCategory;
	}

	private boolean isNameUnique(String name) {
		return !categoryRepository.existsByName(name);
	}

	private boolean isNameUniqueAndIdNot(String name, Integer id) {
		return !categoryRepository.existsByNameAndIdNot(name, id);
	}

	public boolean isIdValid(Integer id) {
		return categoryRepository.existsById(id);
	}

}
