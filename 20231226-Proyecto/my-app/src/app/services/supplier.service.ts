import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { data } from 'src/app/data/suppliers';

@Injectable({
  providedIn: 'root',
})
export class SupplierService {
  private API_URL = 'http://localhost:3000/suppliers';
  suppliers = [...data]; //! borrar

  constructor(private http: HttpClient) {}

  public getSuppliers(): Observable<any> {
    return this.http.get(`${this.API_URL}`).pipe(
      catchError((error) => {
        console.error('Error fetching supplier list:', error);
        return throwError(() => error);
      })
    );
  }

  public getSupplierById2(id: number): Observable<any> {
    return this.http.get(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching supplier by ID:', error);
        return throwError(() => error);
      })
    );
  }

  public getSuppliersByRange(start: number, end: number): Observable<any> {
    return this.http.get(`${this.API_URL}?_start=${start}&_end=${end}`).pipe(
      catchError((error) => {
        console.error('Error fetching suppliers by range:', error);
        return throwError(() => error);
      })
    );
  }

  public createSupplier2(supplier: any) {
    return this.http.post(`${this.API_URL}`, supplier).pipe(
      catchError((error) => {
        console.error('Error creating supplier:', error);
        return throwError(() => error);
      })
    );
  }

  public updateSupplier2(id: number, data: any) {
    return this.http.patch(`${this.API_URL}/${id}`, data).pipe(
      catchError((error) => {
        console.error('Error updating supplier:', error);
        return throwError(() => error);
      })
    );
  }

  public deleteSupplier(id: number) {
    return this.http.delete(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error deleting supplier:', error);
        return throwError(() => error);
      })
    );
  }

  //! borrar
  public getSuppliers2(): any[] {
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

  public deleteSupplier2(supplier: any): boolean {
    let isDeleted = false;
    const index = this.suppliers.indexOf(supplier);

    if (index !== -1) {
      this.suppliers.splice(index, 1);
      isDeleted = true;
    }

    return isDeleted;
  }
}
