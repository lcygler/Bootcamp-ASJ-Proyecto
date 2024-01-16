import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { data } from 'src/app/data/suppliers';

@Injectable({
  providedIn: 'root',
})
export class SupplierService {
  suppliers = [...data];

  constructor(private http: HttpClient) {}

  public getSuppliers(): any[] {
    return this.suppliers;
  }

  public getSupplierById(id: string): any | undefined {
    const supplier = this.suppliers.find((s) => s.id === id);
    return supplier;
  }

  public createSupplier(supplier: any) {
    if (supplier) {
      const lastSupplier = this.suppliers[this.suppliers.length - 1];
      const newId = lastSupplier ? parseInt(lastSupplier.id) + 1 : 1;
      supplier.id = newId;
      this.suppliers.push(supplier);
    }
  }

  public updateSupplier(supplier: any): boolean {
    let isUpdated = false;
    const index = this.suppliers.indexOf(supplier);

    if (index !== -1) {
      this.suppliers[index] = supplier;
      isUpdated = true;
    }

    return isUpdated;
  }

  public deleteSupplier(supplier: any): boolean {
    let isDeleted = false;
    const index = this.suppliers.indexOf(supplier);

    if (index !== -1) {
      this.suppliers.splice(index, 1);
      isDeleted = true;
    }

    return isDeleted;
  }
}
