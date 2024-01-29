package com.asj.api.services.product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.models.product.ProductModel;
import com.asj.api.repositories.product.ProductRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@PersistenceContext
    private EntityManager entityManager;
	
	public List<ProductModel> getAllProducts() {
		return productRepository.findAll();
	}

	public List<ProductModel> getProductsBySupplier(Integer supplierId) {
		return productRepository.findBySupplierId(supplierId);
	}

	public Optional<ProductModel> getProductById(Integer id) {
		return productRepository.findById(id);
	}

	@Transactional
	public ProductModel createProduct(ProductModel product) {
		product.setSku(generateProductSku());
		product.setCreatedAt(LocalDateTime.now());
		product.setUpdatedAt(LocalDateTime.now());
		product.setIsDeleted(false);

		ProductModel createdProduct = productRepository.save(product);
		entityManager.refresh(createdProduct);
		
		return createdProduct;
		
		// ProductDTO productDTO = new ProductDTO();
		// BeanUtils.copyProperties(createdProduct, productDTO);
		// return productDTO;
	}

	@Transactional
	public Optional<ProductModel> updateProduct(Integer id, ProductModel product) {
		Optional<ProductModel> optionalProduct = Optional.empty();

		if (productRepository.existsById(id)) {
			product.setUpdatedAt(LocalDateTime.now());
			
			ProductModel updatedProduct = productRepository.save(product);
			entityManager.refresh(updatedProduct);
			
			optionalProduct = Optional.of(updatedProduct);
		}

		return optionalProduct;
	}

	@Transactional
	public Optional<ProductModel> patchProduct(Integer id, ProductModel product) {
		Optional<ProductModel> optionalProduct = getProductById(id);

		if (optionalProduct.isPresent()) {
			ProductModel existingProduct = optionalProduct.get();

			if (product.getName() != null) {
				existingProduct.setName(product.getName());
			}

			if (product.getDescription() != null) {
				existingProduct.setDescription(product.getDescription());
			}

			if (product.getImage() != null) {
				existingProduct.setImage(product.getImage());
			}

			if (product.getCategory() != null) {
				existingProduct.setCategory(product.getCategory());
			}

			if (product.getSupplier() != null) {
				existingProduct.setSupplier(product.getSupplier());
			}

			if (product.getIsDeleted() != null) {
				existingProduct.setIsDeleted(product.getIsDeleted());
			}

			existingProduct.setUpdatedAt(LocalDateTime.now());
			
			productRepository.save(existingProduct);
			entityManager.flush();
			entityManager.refresh(existingProduct);
			
			return Optional.of(existingProduct);
		} else {
			return Optional.empty();
		}
	}

	@Transactional
	public Optional<ProductModel> deleteProduct(Integer id) {
		Optional<ProductModel> optionalProduct = getProductById(id);

		if (optionalProduct.isPresent()) {
			productRepository.deleteById(id);
		}

		return optionalProduct;
	}

	public Integer getNextProductId() {
		Integer maxProductId = productRepository.getMaxProductId();

		if (maxProductId != null) {
			return maxProductId + 1;
		} else {
			return 1;
		}
	}

	private String generateProductSku() {
		return "SKU-" + getNextProductId();
	}

}