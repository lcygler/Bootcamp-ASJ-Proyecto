package com.asj.api.services.product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asj.api.models.product.ProductModel;
import com.asj.api.repositories.product.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public List<ProductModel> getAllProducts() {
		return productRepository.findAll();
	}

	public List<ProductModel> getProductsBySupplierId(Integer supplierId) {
        return productRepository.findBySupplierId(supplierId);
    }
	
	public Optional<ProductModel> getProductById(Integer id) {
		return productRepository.findById(id);
	}

	public ProductModel createProduct(ProductModel product) {
		LocalDateTime currentTimestamp = LocalDateTime.now();
		product.setCreatedAt(currentTimestamp);
		product.setUpdatedAt(currentTimestamp);

		return productRepository.save(product);
	}

	public Optional<ProductModel> updateProduct(Integer id, ProductModel product) {
		Optional<ProductModel> optionalProduct = Optional.empty();

		if (productRepository.existsById(id)) {
			product.setId(id);
			product.setUpdatedAt(LocalDateTime.now());
			optionalProduct = Optional.of(productRepository.save(product));
		}

		return optionalProduct;
	}

	public Optional<ProductModel> patchProduct(Integer id, ProductModel product) {
		Optional<ProductModel> optionalProduct = getProductById(id);

		if (optionalProduct.isPresent()) {
			ProductModel existingProduct = optionalProduct.get();

			if (product.getSku() != null) {
				existingProduct.setSku(product.getSku());
			}

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

			if (product.isDeleted() != null) {
				existingProduct.setDeleted(product.isDeleted());
			}

			existingProduct.setUpdatedAt(LocalDateTime.now());

			return Optional.of(productRepository.save(existingProduct));
		} else {
			return Optional.empty();
		}
	}

	public Optional<ProductModel> deleteProduct(Integer id) {
		Optional<ProductModel> optionalProduct = getProductById(id);

		if (optionalProduct.isPresent()) {
			productRepository.deleteById(id);
		}

		return optionalProduct;
	}

}