import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Image } from 'src/app/models/common/IImage';

import { Category } from 'src/app/models/product/ICategory';
import { Product } from 'src/app/models/product/IProduct';
import { Supplier } from 'src/app/models/supplier/ISupplier';
import { ImageService } from 'src/app/services/common/image.service';

import { ToastService } from 'src/app/services/common/toast.service';
import { CategoryService } from 'src/app/services/product/category.service';
import { ProductService } from 'src/app/services/product/product.service';
import { SupplierService } from 'src/app/services/supplier/supplier.service';

@Component({
  selector: 'app-products-form',
  templateUrl: './products-form.component.html',
  styleUrls: ['./products-form.component.css'],
})
export class ProductsFormComponent implements OnInit {
  product: Product = {
    sku: '',
    name: '',
    description: '',
    price: null,
    image: {},
    category: {},
    supplier: {},
  };

  supplierList: Supplier[] = [];
  categoryList: Category[] = [];

  productId: number | null = null;
  isEditView: boolean = false;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private productService: ProductService,
    private supplierService: SupplierService,
    private toastService: ToastService,
    private categoryService: CategoryService,
    private location: Location,
    private imageService: ImageService
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('productId');

    if (id) {
      this.productId = parseInt(id);
      this.getProductById(this.productId);
      this.isEditView = this.isEditRoute();
    }

    this.getSuppliers();
    this.getCategories();
  }

  getProductById(id: number) {
    if (id) {
      this.productService.getProductById(id).subscribe((res) => {
        this.product = res;
      });
    }
  }

  getSuppliers() {
    this.supplierService.getSuppliers().subscribe((res) => {
      this.supplierList = res;
    });
  }

  getCategories() {
    this.categoryService.getCategories().subscribe((res) => {
      this.categoryList = res;
    });
  }

  onSubmit(form: NgForm) {
    if (form.invalid) {
      console.error('Form contains errors.');
      return;
    }

    const formData = form.value;

    const product: Product = {
      name: formData.name.trim(),
      description: formData.description.trim(),
      price: formData.price,
      category: { id: formData.category },
      supplier: { id: formData.supplier },
    };

    const image: Image = {
      url: formData.image,
    };

    if (this.isEditRoute()) {
      // Update image
      this.imageService
        .updateImage(this.product.image?.id!, image)
        .subscribe((res) => {
          // Update product
          this.productService
            .updateProduct(this.product.id!, product)
            .subscribe((res) => {
              this.toastService.showSuccessToast(
                'Producto modificado correctamente!'
              );
              form.reset();
              this.navigateToProducts();
            });
        });
    } else {
      // Add image
      this.imageService.createImage(image).subscribe((res) => {
        product.image = { id: res.id };

        // Add product
        this.productService.createProduct(product).subscribe((res) => {
          this.toastService.showSuccessToast(
            'Producto agregado correctamente!'
          );
          form.reset();
          this.navigateToProducts();
        });
      });
    }
  }

  navigateToProducts() {
    this.location.back();
    // this.router.navigate(['/products']);
  }

  navigateBack() {
    this.location.back();
  }

  isEditRoute(): boolean {
    const route = this.router.url;
    return route.includes('/products/edit');
  }
}
