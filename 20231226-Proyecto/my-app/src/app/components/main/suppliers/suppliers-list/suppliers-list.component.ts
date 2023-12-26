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
  filteredSuppliers: any[] = [];
  // displayedSuppliers: any[] = [];
  searchTerm: string = '';

  itemsPerPage: number = 5;
  totalPages: number = 1;
  currentPage: number = 1;

  constructor(
    private router: Router,
    private supplierService: SupplierService
  ) {}

  ngOnInit() {
    this.getSuppliers();
  }

  getSuppliers() {
    this.supplierService.getSuppliers().subscribe((res) => {
      this.supplierList = res;
      this.filterSuppliers();
      this.updateTotalPages();
    });
  }

  addSupplier() {
    this.router.navigate(['/suppliers/add']);
  }

  editSupplier(id: number) {
    this.router.navigate([`/suppliers/edit/${id}`]);
  }

  deleteSupplier(id: number) {
    const confirmDelete = confirm(
      'Are you sure you want to delete this supplier?'
    );

    if (confirmDelete) {
      this.supplierService.deleteSupplier(id).subscribe((res) => {
        this.getSuppliers();
      });
    }
  }

  filterSuppliers() {
    // const start = (this.currentPage - 1) * this.itemsPerPage;
    // const end = start + this.itemsPerPage;
    this.currentPage = 1;

    if (this.searchTerm != '') {
      this.filteredSuppliers = this.supplierList.filter((item) =>
        JSON.stringify(item)
          .toLowerCase()
          .includes(this.searchTerm.toLowerCase())
      );

      this.updateTotalPages();
      // this.filteredSuppliers = this.filteredSuppliers.slice(start, end);
      // this.displayedSuppliers = this.filteredSuppliers.slice(start, end);
    } else {
      this.filteredSuppliers = [...this.supplierList];
      this.updateTotalPages();
      // this.filteredSuppliers = this.supplierList.slice(start, end);
      // this.displayedSuppliers = this.supplierList.slice(start, end);
    }

    // return this.filteredSuppliers.slice(start, end);
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

    // this.filterSuppliers();
  }

  updateTotalPages(): void {
    // this.totalPages = Math.ceil(this.supplierList.length / this.itemsPerPage);

    this.totalPages = Math.ceil(
      this.filteredSuppliers.length / this.itemsPerPage
    );

    // this.totalPages = Math.max(
    //   1,
    //   Math.ceil(this.filteredSuppliers.length / this.itemsPerPage)
    // );
  }

  hasMoreItems(): boolean {
    const start = (this.currentPage - 1) * this.itemsPerPage;
    return start + this.itemsPerPage < this.filteredSuppliers.length;
    // return start + this.itemsPerPage < this.supplierList.length;
  }

  // hasMoreItems(): boolean {
  //   const start = (this.currentPage - 1) * this.itemsPerPage;
  //   // Calculate the end index based on the current page and items per page
  //   const end = start + this.itemsPerPage;
  //   // Check if there are more items in the current page
  //   return end < this.supplierList | paginate: this.currentPage:this.itemsPerPage:this.searchTerm?.length;
  // }
}
