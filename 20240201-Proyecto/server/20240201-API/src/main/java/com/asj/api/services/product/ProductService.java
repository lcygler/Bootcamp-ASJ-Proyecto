package com.asj.api.services.product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesExistException;
import com.asj.api.exceptions.InvalidIdentifierException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.product.ProductModel;
import com.asj.api.repositories.order.OrderDetailRepository;
import com.asj.api.repositories.product.ProductRepository;
import com.asj.api.services.common.ImageService;
import com.asj.api.services.supplier.SupplierService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryService categoryService;

	@Autowired
	ImageService imageService;

	@Autowired
	SupplierService supplierService;

	@Autowired
	OrderDetailRepository orderDetailRepository;

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
		if (!isSkuUnique(product.getSku())) {
			throw new UniqueViolationException("SKU must be unique");
		}

		validateCategory(product.getCategory().getId());
		validateSupplier(product.getSupplier().getId());

		if (product.getImage() != null) {
			validateImage(product.getImage().getId());
		}

		product.setCreatedAt(LocalDateTime.now());
		product.setUpdatedAt(LocalDateTime.now());
		product.setIsDeleted(false);

		ProductModel createdProduct = productRepository.save(product);
		entityManager.refresh(createdProduct);

		return createdProduct;
	}

	@Transactional
	public Optional<ProductModel> updateProduct(Integer id, ProductModel product) {
		if (!isSkuUniqueAndIdNot(product.getSku(), id)) {
			throw new UniqueViolationException("SKU must be unique");
		}

		validateCategory(product.getCategory().getId());
		validateSupplier(product.getSupplier().getId());

		if (product.getImage() != null) {
			validateImage(product.getImage().getId());
		}

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

			if (product.getSku() != null) {
				if (!isSkuUniqueAndIdNot(product.getSku(), id)) {
					throw new UniqueViolationException("SKU must be unique");
				}
				existingProduct.setSku(product.getSku());
			}

			if (product.getName() != null) {
				existingProduct.setName(product.getName());
			}

			if (product.getDescription() != null) {
				existingProduct.setDescription(product.getDescription());
			}

			if (product.getImage() != null) {
				validateImage(product.getImage().getId());
				existingProduct.setImage(product.getImage());
			}

			if (product.getCategory() != null) {
				validateCategory(product.getCategory().getId());
				existingProduct.setCategory(product.getCategory());
			}

			if (product.getSupplier() != null) {
				validateSupplier(product.getSupplier().getId());
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
		if (orderDetailRepository.countByProduct(id) > 0) {
			throw new AssociatedEntitiesExistException("Deletion failed: Associated entities found");
		}

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

	private boolean isSkuUnique(String sku) {
		return !productRepository.existsBySku(sku);
	}

	private boolean isSkuUniqueAndIdNot(String sku, Integer id) {
		return !productRepository.existsBySkuAndIdNot(sku, id);
	}

	private void validateCategory(Integer id) {
		if (!categoryService.isIdValid(id)) {
			throw new InvalidIdentifierException("Category is not valid");
		}
	}

	private void validateImage(Integer id) {
		if (!imageService.isIdValid(id)) {
			throw new InvalidIdentifierException("Image is not valid");
		}
	}

	private void validateSupplier(Integer id) {
		if (!supplierService.isIdValid(id)) {
			throw new InvalidIdentifierException("Supplier is not valid");
		}
	}

	public boolean isIdValid(Integer id) {
		return productRepository.existsById(id);
	}

}