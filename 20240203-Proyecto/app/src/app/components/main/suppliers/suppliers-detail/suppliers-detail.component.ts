import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Supplier } from 'src/app/models/supplier/ISupplier';
import { ToastService } from 'src/app/services/common/toast.service';
import { SupplierService } from 'src/app/services/supplier/supplier.service';

@Component({
  selector: 'app-suppliers-detail',
  templateUrl: './suppliers-detail.component.html',
  styleUrls: ['./suppliers-detail.component.css'],
})
export class SuppliersDetailComponent implements OnInit {
  supplier: Supplier = {
    code: '',
    businessName: '',
    industry: {},
    website: '',
    email: '',
    phone: '',
    address: {
      state: {
        country: {},
      },
    },
    taxInformation: {
      vatCondition: {},
    },
    contactDetails: {},
  };

  supplierId: number | null = null;
  restoreMessage: string = '';
  active = 1;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private supplierService: SupplierService,
    private location: Location,
    private toastService: ToastService
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('supplierId');

    if (id) {
      this.supplierId = parseInt(id);
      this.getSupplierById(this.supplierId);
    }
  }

  getSupplierById(id: number) {
    if (id) {
      this.supplierService.getSupplierById(id).subscribe((res) => {
        this.supplier = res;
      });
    }
  }

  editSupplier() {
    this.router.navigate([`/suppliers/edit/${this.supplierId}`]);
  }

  navigateBack() {
    this.location.back();
  }

  confirmRestore() {
    this.restoreMessage = `<div>¿Está seguro de que desea restaurar el proveedor?</div>
    <div class="mt-2">${this.supplier.businessName} <small>(Código: ${this.supplier.code})</small></div>`;
  }

  restoreSupplier() {
    this.supplierService.restoreSupplier(this.supplierId!).subscribe((res) => {
      this.toastService.showSuccessToast('Proveedor restaurado correctamente!');
      this.getSupplierById(this.supplierId!);
    });
  }
}
