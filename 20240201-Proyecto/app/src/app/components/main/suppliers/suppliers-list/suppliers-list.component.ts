import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faInfo } from '@fortawesome/free-solid-svg-icons';
import { ToastService } from 'src/app/services/common/toast.service';
import { SupplierService } from 'src/app/services/supplier/supplier.service';

@Component({
  selector: 'app-suppliers-list',
  templateUrl: './suppliers-list.component.html',
  styleUrls: ['./suppliers-list.component.css'],
})
export class SuppliersListComponent implements OnInit {
  supplierList: any[] = [];
  filteredSuppliers: any[] = [];
  deleteFilter: boolean = false;
  supplierSort: string = 'Default';
  locationSort: string = 'Default';
  searchTerm: string = '';
  supplierToDeleteId: number | null = null;
  deleteMessage: string = '';

  itemsPerPage: number = 5;
  totalPages: number = 1;
  currentPage: number = 1;

  faInfo = faInfo;

  constructor(
    private router: Router,
    private supplierService: SupplierService,
    private toastService: ToastService
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

  openSupplier(id: number) {
    this.router.navigate([`/suppliers/${id}`]);
  }

  editSupplier(id: number) {
    this.router.navigate([`/suppliers/edit/${id}`]);
  }

  confirmDelete(id: number) {
    this.deleteMessage = `¿Está seguro de que desea eliminar al proveedor #${id}?`;
    this.supplierToDeleteId = id;
  }

  deleteSupplier() {
    if (this.supplierToDeleteId) {
      this.supplierService
        .deleteSupplier(this.supplierToDeleteId)
        .subscribe((res) => {
          this.getSuppliers();
          this.supplierToDeleteId = null;
          this.toastService.showSuccessToast(
            'Proveedor eliminado correctamente!'
          );
        });
    }
  }

  filterSuppliers() {
    this.currentPage = 1;

    if (this.searchTerm) {
      this.filteredSuppliers = this.supplierList.filter((item) =>
        JSON.stringify(item)
          .toLowerCase()
          .includes(this.searchTerm.toLowerCase())
      );
      this.updateTotalPages();
    } else {
      this.filteredSuppliers = [...this.supplierList];
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
      Math.ceil(this.filteredSuppliers.length / this.itemsPerPage)
    );
  }

  hasMoreItems(): boolean {
    const start = (this.currentPage - 1) * this.itemsPerPage;
    return start + this.itemsPerPage < this.filteredSuppliers.length;
  }

  calculateRange(): string {
    const start = (this.currentPage - 1) * this.itemsPerPage + 1;
    const end = Math.min(
      this.currentPage * this.itemsPerPage,
      this.filteredSuppliers.length
    );
    return `Mostrando ${start} - ${end} de ${this.filteredSuppliers.length} proveedores`;
  }

  onCheckboxChange(event: any) {
    const checked = event.target.checked;

    if (checked) {
      this.deleteFilter = true;
    } else {
      this.deleteFilter = false;
    }
  }

  restoreSupplier(id: number) {
    this.supplierService.restoreSupplier(id).subscribe((res) => {
      this.toastService.showSuccessToast('Proveedor restaurado correctamente!');

      this.supplierService.getSuppliers().subscribe((res) => {
        this.supplierList = res;
        this.filterSuppliers();
        this.updateTotalPages();

        const hasDeletedSuppliers = this.supplierList.some(
          (supplier) => supplier.isDeleted
        );

        if (!hasDeletedSuppliers) {
          this.deleteFilter = false;
        }
      });
    });
  }

  hasDeletedSuppliers() {
    return this.supplierList.some((supplier) => supplier.isDeleted);
  }

  updateSupplierFilter() {
    switch (this.supplierSort) {
      case 'Default':
        this.supplierSort = 'Ascending';
        break;
      case 'Ascending':
        this.supplierSort = 'Descending';
        break;
      default:
        this.supplierSort = 'Default';
        break;
    }
  }

  updateLocationFilter() {
    switch (this.locationSort) {
      case 'Default':
        this.locationSort = 'Ascending';
        break;
      case 'Ascending':
        this.locationSort = 'Descending';
        break;
      default:
        this.locationSort = 'Default';
        break;
    }
  }
}
