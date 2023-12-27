import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SupplierService } from 'src/app/services/supplier.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-suppliers-form',
  templateUrl: './suppliers-form.component.html',
  styleUrls: ['./suppliers-form.component.css'],
})
export class SuppliersFormComponent implements OnInit {
  supplier: any = {
    address: {},
    taxInformation: {},
    contactDetails: {},
  };
  editSupplier: boolean = false;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private supplierService: SupplierService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('supplierId');

    if (id) {
      this.getSupplierById(parseInt(id));
      this.editSupplier = this.isEditRoute();
    }
  }

  getSupplierById(id: number): void {
    if (id) {
      this.supplierService.getSupplierById(id).subscribe((res) => {
        this.supplier = res;
      });
    }
  }

  saveChanges(): void {
    if (this.supplier) {
      if (this.isEditRoute()) {
        // Update existing supplier
        this.supplierService
          .updateSupplier(this.supplier.id, this.supplier)
          .subscribe((res) => {
            this.toastService.showSuccessToast(
              'Proveedor modificado correctamente!'
            );
          });
      } else {
        // Add new supplier
        this.supplierService.createSupplier(this.supplier).subscribe((res) => {
          this.toastService.showSuccessToast(
            'Proveedor agregado correctamente!'
          );
        });
      }

      this.router.navigate(['/suppliers']);
    }
  }

  isEditRoute(): boolean {
    const route = this.router.url;
    return route.includes('/suppliers/edit');
  }
}
