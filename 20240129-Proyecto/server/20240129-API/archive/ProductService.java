/*
package com.asj.api.archive;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asj.api.models.common.ImageModel;
import com.asj.api.models.product.CategoryModel;
import com.asj.api.models.product.ProductModel;
import com.asj.api.models.supplier.SupplierModel;
import com.asj.api.repositories.product.ProductRepository;
import com.asj.api.services.common.ImageService;
import com.asj.api.services.supplier.SupplierService;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryService categoryService;

	@Autowired
	SupplierService supplierService;

	@Autowired
	ImageService imageService;
	
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
		product = updateCategory(product);
		product = updateSupplier(product);
		product = updateImage(product);
		product.setCreatedAt(LocalDateTime.now());
		product.setUpdatedAt(LocalDateTime.now());
		product.setIsDeleted(false);

		return productRepository.save(product);
	}

	public Optional<ProductModel> updateProduct(Integer id, ProductModel product) {
		Optional<ProductModel> optionalProduct = Optional.empty();

		if (productRepository.existsById(id)) {
			product = updateCategory(product);
			product = updateSupplier(product);
			product = updateImage(product);
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
				product = updateImage(product);
				existingProduct.setImage(product.getImage());
			}

			if (product.getCategory() != null) {
				product = updateCategory(product);
				existingProduct.setCategory(product.getCategory());
			}

			if (product.getSupplier() != null) {
				product = updateSupplier(product);
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
	
	private ProductModel updateCategory(ProductModel product) {
		Integer id = product.getCategory().getId();
		CategoryModel category = categoryService.getCategoryById(id).get();
		product.setCategory(category);
		
		return product;
	}
	
	private ProductModel updateSupplier(ProductModel product) {
		Integer id = product.getSupplier().getId();
		SupplierModel supplier = supplierService.getSupplierById(id).get();
		product.setSupplier(supplier);
		
		return product;
	}
	
	private ProductModel updateImage(ProductModel product) {
		Integer id = product.getImage().getId();
		ImageModel image = imageService.getImageById(id).get();
		product.setImage(image);
		
		return product;
	}

}
*/