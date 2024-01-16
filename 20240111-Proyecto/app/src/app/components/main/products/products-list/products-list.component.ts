import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css'],
})
export class ProductsListComponent implements OnInit {
  productList: any[] = [];
  filteredProducts: any[] = [];
  searchTerm: string = '';
  productToDeleteId: number | null = null;
  deleteMessage: string = '';

  itemsPerPage: number = 5;
  totalPages: number = 1;
  currentPage: number = 1;

  constructor(
    private router: Router,
    private productService: ProductService,
    private toastService: ToastService
  ) {}

  ngOnInit() {
    this.getProducts();
  }

  getProducts() {
    this.productService.getProducts().subscribe((res) => {
      this.productList = res;
      this.filterProducts();
      this.updateTotalPages();
    });
  }

  addProduct() {
    this.router.navigate(['/products/add']);
  }

  editProduct(id: number) {
    this.router.navigate([`/products/edit/${id}`]);
  }

  confirmDelete(id: number) {
    this.deleteMessage = `¿Está seguro de que desea eliminar el producto #${id}?`;
    this.productToDeleteId = id;
  }

  deleteProduct() {
    if (this.productToDeleteId) {
      this.productService
        .deleteProduct(this.productToDeleteId)
        .subscribe((res) => {
          this.getProducts();
          this.productToDeleteId = null;
          this.toastService.showSuccessToast(
            'Proveedor eliminado correctamente!'
          );
        });
    }
  }

  filterProducts() {
    this.currentPage = 1;

    if (this.searchTerm) {
      this.filteredProducts = this.productList.filter((item) =>
        JSON.stringify(item)
          .toLowerCase()
          .includes(this.searchTerm.toLowerCase())
      );
      this.updateTotalPages();
    } else {
      this.filteredProducts = [...this.productList];
      this.updateTotalPages();
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
  }

  updateTotalPages(): void {
    const minPages = 1;

    this.totalPages = Math.max(
      minPages,
      Math.ceil(this.filteredProducts.length / this.itemsPerPage)
    );
  }

  hasMoreItems(): boolean {
    const start = (this.currentPage - 1) * this.itemsPerPage;
    return start + this.itemsPerPage < this.filteredProducts.length;
  }

  calculateRange(): string {
    const start = (this.currentPage - 1) * this.itemsPerPage + 1;
    const end = Math.min(
      this.currentPage * this.itemsPerPage,
      this.filteredProducts.length
    );
    return `Mostrando ${start} - ${end} de ${this.filteredProducts.length} productos`;
  }
}
