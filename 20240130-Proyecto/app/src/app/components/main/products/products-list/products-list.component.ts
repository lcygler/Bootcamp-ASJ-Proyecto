import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Category } from 'src/app/models/product/ICategory';
import { Product } from 'src/app/models/product/IProduct';
import { ToastService } from 'src/app/services/common/toast.service';
import { CategoryService } from 'src/app/services/product/category.service';
import { ProductService } from 'src/app/services/product/product.service';

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css'],
})
export class ProductsListComponent implements OnInit {
  productList: Product[] = [];
  filteredProducts: Product[] = [];
  categoryList: Category[] = [];
  categoryFilter: string = 'Categoría';
  deleteFilter: boolean = false;
  priceFilter: number = 0;
  searchTerm: string = '';
  productToDeleteId: number | null = null;
  deleteMessage: string = '';

  itemsPerPage: number = 5;
  totalPages: number = 1;
  currentPage: number = 1;

  constructor(
    private router: Router,
    private productService: ProductService,
    private toastService: ToastService,
    private categoryService: CategoryService
  ) {}

  ngOnInit() {
    this.getProducts();
    this.getCategories();
  }

  getProducts() {
    this.productService.getProducts().subscribe((res) => {
      this.productList = res.filter((product) => !product.supplier.isDeleted);
      this.filterProducts();
      this.updateTotalPages();
    });
  }

  getCategories() {
    this.categoryService.getCategories().subscribe((res) => {
      this.categoryList = res;
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

  updatePage(navigation: 'prev' | 'next' | 'first' | 'last') {
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

  updateTotalPages() {
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

  updateCategoryFilter(category: string) {
    this.categoryFilter = category;
  }

  updatePriceFilter() {
    switch (this.priceFilter) {
      case 0:
        this.priceFilter = 1; // Ascending
        break;
      case 1:
        this.priceFilter = 2; // Descending
        break;
      default:
        this.priceFilter = 0; // Default
        break;
    }
  }

  onCheckboxChange(event: any) {
    const checked = event.target.checked;

    if (checked) {
      this.deleteFilter = true;
    } else {
      this.deleteFilter = false;
    }
  }

  restoreProduct(id: number) {
    this.productService.restoreProduct(id).subscribe((res) => {
      this.toastService.showSuccessToast('Producto restaurado correctamente!');

      this.productService.getProducts().subscribe((res) => {
        this.productList = res.filter((product) => !product.supplier.isDeleted);
        this.filterProducts();
        this.updateTotalPages();

        const hasDeletedProducts = this.productList.some(
          (product) => product.isDeleted
        );

        if (!hasDeletedProducts) {
          this.deleteFilter = false;
        }
      });
    });
  }

  hasDeletedProducts() {
    return this.productList.some((product) => product.isDeleted);
  }
}
