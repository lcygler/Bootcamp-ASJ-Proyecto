package com.asj.api.controllers.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.asj.api.models.product.CategoryModel;
import com.asj.api.models.product.ProductModel;
import com.asj.api.services.product.CategoryService;
import com.asj.api.services.product.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/test")
	public String test() {
		return "Products endpoint OK";
	}

	@GetMapping
	public ResponseEntity<List<ProductModel>> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@GetMapping(params = "supplierId") // Invoke method only if request has query param
    public List<ProductModel> getProductsBySupplierId(@RequestParam Integer supplierId) {
        return productService.getProductsBySupplierId(supplierId);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductModel> getProductById(@PathVariable Integer id) {
		Optional<ProductModel> optionalProduct = productService.getProductById(id);

		if (optionalProduct.isPresent()) {
			return ResponseEntity.ok(optionalProduct.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/categories")
    public ResponseEntity<List<CategoryModel>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

	@PostMapping
	public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModel product) {
		ProductModel createdProduct = productService.createProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductModel> updateProduct(@PathVariable Integer id, @RequestBody ProductModel product) {
		Optional<ProductModel> optionalProduct = productService.updateProduct(id, product);

		if (optionalProduct.isPresent()) {
			return ResponseEntity.ok(optionalProduct.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ProductModel> patchTask(@PathVariable Integer id, @RequestBody ProductModel product) {
		Optional<ProductModel> optionalProduct = productService.patchProduct(id, product);

		if (optionalProduct.isPresent()) {
			return ResponseEntity.ok(optionalProduct.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ProductModel> deleteProduct(@PathVariable Integer id) {
		Optional<ProductModel> optionalProduct = productService.deleteProduct(id);

		if (optionalProduct.isPresent()) {
			return ResponseEntity.ok(optionalProduct.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}