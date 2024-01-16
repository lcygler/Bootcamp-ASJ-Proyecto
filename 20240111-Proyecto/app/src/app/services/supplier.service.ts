import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class SupplierService {
  private API_URL = 'http://localhost:3000/suppliers';

  constructor(private http: HttpClient) {}

  public getSuppliers(): Observable<any> {
    return this.http.get(`${this.API_URL}`).pipe(
      map((suppliers: any) =>
        suppliers.filter((supplier: any) => !supplier.isDeleted)
      ),
      catchError((error) => {
        console.error('Error fetching supplier list:', error);
        return throwError(() => error);
      })
    );
  }

  public getSuppliersByRange(start: number, end: number): Observable<any> {
    return this.http.get(`${this.API_URL}?_start=${start}&_end=${end}`).pipe(
      map((suppliers: any) =>
        suppliers.filter((supplier: any) => !supplier.isDeleted)
      ),
      catchError((error) => {
        console.error('Error fetching suppliers by range:', error);
        return throwError(() => error);
      })
    );
  }

  public getSupplierById(id: number): Observable<any> {
    return this.http.get(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching supplier by ID:', error);
        return throwError(() => error);
      })
    );
  }

  public createSupplier(supplier: any) {
    return this.http.post(`${this.API_URL}`, supplier).pipe(
      catchError((error) => {
        console.error('Error creating supplier:', error);
        return throwError(() => error);
      })
    );
  }

  public updateSupplier(id: number, data: any) {
    return this.http.patch(`${this.API_URL}/${id}`, data).pipe(
      catchError((error) => {
        console.error('Error updating supplier:', error);
        return throwError(() => error);
      })
    );
  }

  public deleteSupplier(id: number) {
    return this.http.patch(`${this.API_URL}/${id}`, { isDeleted: true }).pipe(
      catchError((error) => {
        console.error('Error updating supplier:', error);
        return throwError(() => error);
      })
    );
  }

  public deleteSupplierPermanently(id: number) {
    return this.http.delete(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error deleting supplier:', error);
        return throwError(() => error);
      })
    );
  }

  public getNextSupplierId(): Observable<number> {
    return this.http.get(`${this.API_URL}?_sort=id&_order=desc&_limit=1`).pipe(
      map((suppliers: any) => {
        if (suppliers && suppliers.length > 0) {
          return suppliers[0].id + 1;
        } else {
          return 1;
        }
      }),
      catchError((error) => {
        console.error('Error fetching latest supplier:', error);
        return throwError(() => error);
      })
    );
  }

  public getVatConditions(): Observable<any> {
    return this.http.get(`${this.API_URL}/vat-conditions`);
  }
}
