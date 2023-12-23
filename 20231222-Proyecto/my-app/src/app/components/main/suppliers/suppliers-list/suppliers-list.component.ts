import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SupplierService } from 'src/app/services/supplier.service';

@Component({
  selector: 'app-suppliers-list',
  templateUrl: './suppliers-list.component.html',
  styleUrls: ['./suppliers-list.component.css'],
})
export class SuppliersListComponent implements OnInit {
  supplierList: any[] = [];
  displayedSuppliers: any[] = [];

  itemsPerPage: number = 5;
  totalPages: number = 0;
  currentPage: number = 1;

  constructor(
    private router: Router,
    private supplierService: SupplierService
  ) {}

  ngOnInit() {
    this.updateList();
  }

  loadSuppliers() {
    this.supplierList = this.supplierService.getSuppliers();
  }

  addSupplier() {
    this.router.navigate(['/suppliers/add']);
  }

  editSupplier(id: string) {
    this.router.navigate([`/suppliers/edit/${id}`]);
  }

  deleteSupplier(supplier: any) {
    const confirmDelete = window.confirm(
      'Are you sure you want to delete this supplier?'
    );

    if (confirmDelete) {
      this.supplierService.deleteSupplier(supplier);
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
    return startIndex + this.itemsPerPage < this.supplierList.length;
  }

  updateList(): void {
    this.loadSuppliers();

    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.displayedSuppliers = this.supplierList.slice(startIndex, endIndex);

    this.totalPages = Math.ceil(this.supplierList.length / this.itemsPerPage);
  }
}
