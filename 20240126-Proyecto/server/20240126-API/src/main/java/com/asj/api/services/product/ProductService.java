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

	@Autowired
	CategoryService categoryService;

	public List<ProductModel> getAllProducts() {
		return productRepository.findAll();
	}

	public List<ProductModel> getProductsBySupplier(Integer supplierId) {
		return productRepository.findBySupplierId(supplierId);
	}

	public Optional<ProductModel> getProductById(Integer id) {
		return productRepository.findById(id);
	}

	public ProductModel createProduct(ProductModel product) {
		product.setSku(generateProductSku());
		product.setCreatedAt(LocalDateTime.now());
		product.setUpdatedAt(LocalDateTime.now());
		product.setIsDeleted(false);

		// ProductModel createdProduct = productRepository.save(product);
		// createdProduct.setCategory(categoryRepository.findById(createdProduct.getCategory().getId()).orElse(null));
		// createdProduct.setSupplier(supplierRepository.findById(savedProduct.getSupplier().getId()).orElse(null));

		// ProductDTO productDTO = new ProductDTO();
		// BeanUtils.copyProperties(createdProduct2, productDTO);
		// return productDTO;

		return productRepository.save(product);
	}

	public Optional<ProductModel> updateProduct(Integer id, ProductModel product) {
		Optional<ProductModel> optionalProduct = Optional.empty();

		if (productRepository.existsById(id)) {
			product.setUpdatedAt(LocalDateTime.now());
			optionalProduct = Optional.of(productRepository.save(product));
		}

		return optionalProduct;
	}

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