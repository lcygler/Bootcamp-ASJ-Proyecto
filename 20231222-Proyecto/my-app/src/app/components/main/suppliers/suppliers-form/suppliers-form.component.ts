import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SupplierService } from 'src/app/services/supplier.service';

@Component({
  selector: 'app-suppliers-form',
  templateUrl: './suppliers-form.component.html',
  styleUrls: ['./suppliers-form.component.css'],
})
export class SuppliersFormComponent implements OnInit {
  supplier: any = {};

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private supplierService: SupplierService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('supplierId');

    if (id) {
      this.supplier = this.supplierService.getSupplierById(id);
    }
  }

  saveChanges() {
    if (this.supplier) {
      const route = this.router.url;

      if (route.includes('/suppliers/add')) {
        // Add a new supplier
        this.supplierService.createSupplier(this.supplier);
        alert('Supplier added successfully.');
      } else if (route.includes('/suppliers/edit')) {
        // Update existing supplier
        this.supplierService.updateSupplier(this.supplier);
        alert('Changes saved successfully.');
      }

      this.router.navigate(['/suppliers']);
    }
  }
}
