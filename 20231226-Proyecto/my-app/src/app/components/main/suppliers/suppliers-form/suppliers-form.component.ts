import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SupplierService } from 'src/app/services/supplier.service';
import { ToastService } from 'src/app/services/toast.service';
import { Supplier } from '../../../../models/ISupplier';

@Component({
  selector: 'app-suppliers-form',
  templateUrl: './suppliers-form.component.html',
  styleUrls: ['./suppliers-form.component.css'],
})
export class SuppliersFormComponent implements OnInit {
  supplier: Supplier = {
    businessName: '',
    industry: '',
    website: '',
    email: '',
    phone: '',
    address: {
      street: '',
      number: '',
      postalCode: '',
      city: '',
      state: '',
      country: '',
    },
    taxInformation: {
      cuit: '',
      vatCondition: '',
    },
    contactDetails: {
      firstName: '',
      lastName: '',
      phone: '',
      email: '',
      role: '',
    },
  };
  supplierId: number | null = null;
  editSupplier: boolean = false;

  // supplier: any = {
  //   address: {},
  //   taxInformation: {},
  //   contactDetails: {},
  // };

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private supplierService: SupplierService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('supplierId');

    if (id) {
      this.supplierId = parseInt(id);
      this.getSupplierById(this.supplierId);
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

  onSubmit(form: NgForm) {
    if (!this.validateForm()) return;

    const formData = form.value;
    const supplier: Supplier = {
      businessName: formData.businessName,
      industry: formData.industry,
      website: formData.website,
      email: formData.email,
      phone: formData.phone,
      address: {
        street: formData.street,
        number: formData.number,
        postalCode: formData.postalCode,
        city: formData.city,
        state: formData.state,
        country: formData.country,
      },
      taxInformation: {
        cuit: formData.cuit,
        vatCondition: formData.vatCondition,
      },
      contactDetails: {
        firstName: formData.firstName,
        lastName: formData.lastName,
        phone: formData.contactPhone,
        email: formData.contactEmail,
        role: formData.role,
      },
    };

    if (this.isEditRoute()) {
      // Update existing supplier
      this.supplierService
        .updateSupplier(this.supplier.id!, supplier)
        .subscribe((res) => {
          this.toastService.showSuccessToast(
            'Proveedor modificado correctamente!'
          );
        });
    } else {
      // Add new supplier
      this.supplierService.createSupplier(supplier).subscribe((res) => {
        this.toastService.showSuccessToast('Proveedor agregado correctamente!');
      });
    }

    this.router.navigate(['/suppliers']);
  }

  private validateForm(): boolean {
    return true;
  }

  private resetForm(form: NgForm) {
    form.reset();
  }

  /*
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
  */

  isEditRoute(): boolean {
    const route = this.router.url;
    return route.includes('/suppliers/edit');
  }
}
