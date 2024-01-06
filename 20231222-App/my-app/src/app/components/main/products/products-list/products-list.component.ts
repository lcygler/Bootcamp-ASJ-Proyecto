import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css'],
})
export class ProductsListComponent implements OnInit {
  productList: any[] = [];
  displayedProducts: any[] = [];

  itemsPerPage: number = 5;
  totalPages: number = 0;
  currentPage: number = 1;

  constructor(private router: Router, private productService: ProductService) {}

  ngOnInit() {
    this.updateList();
  }

  loadProducts() {
    this.productList = this.productService.getProducts();
  }

  addProduct() {
    this.router.navigate(['/products/add']);
  }

  editProduct(id: string) {
    this.router.navigate([`/products/edit/${id}`]);
  }

  deleteProduct(product: any) {
    const confirmDelete = window.confirm(
      'Are you sure you want to delete this product?'
    );

    if (confirmDelete) {
      this.productService.deleteProduct(product);
      this.updateList();
    }
  }

  updatePage(navigation: 'prev' | 'next' | 'first' | 'last'): void {
    switch (navigation) {
      case 'prev':
        if (this.currentPage > 1) {
          this.currentPage--;
        }
        break;
      case 'next':
        if (this.hasMoreItems()) {
          this.currentPage++;
        }
        break;
      case 'first':
        this.currentPage = 1;
        break;
      case 'last':
        this.currentPage = this.totalPages;
        break;
    }

    this.updateList();
  }

  hasMoreItems(): boolean {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    return startIndex + this.itemsPerPage < this.productList.length;
  }

  updateList(): void {
    this.loadProducts();

    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.displayedProducts = this.productList.slice(startIndex, endIndex);

    this.totalPages = Math.ceil(this.productList.length / this.itemsPerPage);
  }
}
