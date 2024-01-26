package com.asj.api.dto;

import java.time.LocalDateTime;

import com.asj.api.models.common.ImageModel;
import com.asj.api.models.product.CategoryModel;
import com.asj.api.models.supplier.SupplierModel;

public class ProductDTO {

	private Integer id;
	private String sku;
	private String name;
	private String description;
	private Double price;
	private ImageModel image;
	private CategoryModel category;
	private SupplierModel supplier;
	private Boolean isDeleted;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public ProductDTO() {
	}

	public ProductDTO(Integer id, String sku, String name, String description, Double price, ImageModel image,
			CategoryModel category, SupplierModel supplier, Boolean isDeleted, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.sku = sku;
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
		this.category = category;
		this.supplier = supplier;
		this.isDeleted = isDeleted;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", sku=" + sku + ", name=" + name + ", description=" + description + ", price="
				+ price + ", image=" + image + ", category=" + category + ", supplier=" + supplier + ", isDeleted="
				+ isDeleted + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
