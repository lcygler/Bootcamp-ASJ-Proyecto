package com.asj.api.controllers.product;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asj.api.models.product.ProductModel;
import com.asj.api.services.product.ProductService;
import com.asj.api.utils.Utils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/test")
	public String test() {
		return "Products endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<ProductModel>> getAllProducts() {
		try {
			return ResponseEntity.ok(productService.getAllProducts());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping(params = "supplierId") // [GET] /products?supplierId={id}
	public ResponseEntity<List<ProductModel>> getProductsBySupplier(@RequestParam Integer supplierId) {
		try {
			List<ProductModel> products = productService.getProductsBySupplier(supplierId);
			return ResponseEntity.ok(products);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductModel> getProductById(@PathVariable Integer id) {
		try {
			Optional<ProductModel> optionalProduct = productService.getProductById(id);

			if (optionalProduct.isPresent()) {
				return ResponseEntity.ok(optionalProduct.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> createProduct2(@Valid @RequestBody ProductModel product, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = Utils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			ProductModel createdProduct = productService.createProduct(product);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("El SKU ya existe");
		} catch (Exception e) {
			// e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable Integer id, @Valid @RequestBody ProductModel product,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = Utils.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}

			Optional<ProductModel> optionalProduct = productService.updateProduct(id, product);

			if (optionalProduct.isPresent()) {
				return ResponseEntity.ok(optionalProduct.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ProductModel> patchProduct(@PathVariable Integer id, @RequestBody ProductModel product) {
		try {
			Optional<ProductModel> optionalProduct = productService.patchProduct(id, product);

			if (optionalProduct.isPresent()) {
				return ResponseEntity.ok(optionalProduct.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ProductModel> deleteProduct(@PathVariable Integer id) {
		try {
			Optional<ProductModel> optionalProduct = productService.deleteProduct(id);

			if (optionalProduct.isPresent()) {
				return ResponseEntity.ok(optionalProduct.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}