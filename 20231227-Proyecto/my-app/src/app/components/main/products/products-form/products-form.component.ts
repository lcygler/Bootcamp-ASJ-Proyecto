import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-products-form',
  templateUrl: './products-form.component.html',
  styleUrls: ['./products-form.component.css'],
})
export class ProductsFormComponent implements OnInit {
  product: any = {};

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private productService: ProductService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('productId');

    if (id) {
      this.product = this.productService.getProductById(id);
    }
  }

  saveChanges() {
    if (this.product) {
      const route = this.router.url;

      if (route.includes('/products/add')) {
        // Add new product
        this.productService.createProduct(this.product);
        alert('Product added successfully.');
      } else if (route.includes('/products/edit')) {
        // Update existing product
        this.productService.updateProduct(this.product);
        alert('Changes saved successfully.');
      }

      this.router.navigate(['/products']);
    }
  }
}
