import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';
import { SupplierService } from 'src/app/services/supplier.service';
import { ToastService } from 'src/app/services/toast.service';
import { Product } from '../../../../models/IProduct';

@Component({
  selector: 'app-products-form',
  templateUrl: './products-form.component.html',
  styleUrls: ['./products-form.component.css'],
})
export class ProductsFormComponent implements OnInit {
  product: Product = {
    sku: '',
    category: '',
    name: '',
    description: '',
    price: null,
    isDeleted: false,
    supplierId: null,
  };
  productId: number | null = null;
  nextProductId: number | null = null;
  editProduct: boolean = false;
  supplierList: any[] = [];
  categoryList: any[] = [];

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private productService: ProductService,
    private supplierService: SupplierService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('productId');

    if (id) {
      this.productId = parseInt(id);
      this.getProductById(this.productId);
      this.editProduct = this.isEditRoute();
    }

    this.getSuppliers();
    this.getCategories();
  }

  getProductById(id: number): void {
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
    this.productService.getCategories().subscribe((res) => {
      this.categoryList = res;
    });
  }

  onSubmit(form: NgForm) {
    if (!form.valid) {
      console.error('Invalid form.');
      return;
    }

    this.productService.getNextProductId().subscribe((res) => {
      const formData = form.value;

      const product: Product = {
        sku: this.product.id
          ? `${this.product.sku}`
          : `${this.product.category.slice(0, 4).toUpperCase()}-${res}`,
        category: formData.category,
        name: formData.name,
        description: formData.description,
        price: formData.price,
        isDeleted: this.product.id ? this.product.isDeleted : false,
        supplierId: formData.supplierId,
      };

      if (this.isEditRoute()) {
        // Update existing product
        this.productService
          .updateProduct(this.product.id!, product)
          .subscribe((res) => {
            this.toastService.showSuccessToast(
              'Producto modificado correctamente!'
            );
          });
      } else {
        // Add new product
        this.productService.createProduct(product).subscribe((res) => {
          this.toastService.showSuccessToast(
            'Producto agregado correctamente!'
          );
        });
      }

      form.reset();
      this.router.navigate(['/products']);
    });
  }

  isEditRoute(): boolean {
    const route = this.router.url;
    return route.includes('/products/edit');
  }
}
