import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { productCategories } from '../data/product-categories';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private API_URL = 'http://localhost:3000/products';

  constructor(private http: HttpClient) {}

  public getProducts(): Observable<any> {
    return this.http.get(`${this.API_URL}?_expand=supplier`).pipe(
      map((products: any) =>
        products
          .filter((product: any) => !product.isDeleted)
          .sort((a: any, b: any) => a.name.localeCompare(b.name))
      ),
      catchError((error) => {
        console.error('Error fetching product list:', error);
        return throwError(() => error);
      })
    );
  }

  public getProductsByRange(start: number, end: number): Observable<any> {
    return this.http.get(`${this.API_URL}?_start=${start}&_end=${end}`).pipe(
      map((products: any) =>
        products
          .filter((product: any) => !product.isDeleted)
          .sort((a: any, b: any) => a.name.localeCompare(b.name))
      ),
      catchError((error) => {
        console.error('Error fetching products by range:', error);
        return throwError(() => error);
      })
    );
  }

  public getProductById(id: number): Observable<any> {
    return this.http.get(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching product by ID:', error);
        return throwError(() => error);
      })
    );
  }

  public createProduct(product: any) {
    return this.http.post(`${this.API_URL}`, product).pipe(
      catchError((error) => {
        console.error('Error creating product:', error);
        return throwError(() => error);
      })
    );
  }

  public updateProduct(id: number, data: any) {
    return this.http.patch(`${this.API_URL}/${id}`, data).pipe(
      catchError((error) => {
        console.error('Error updating product:', error);
        return throwError(() => error);
      })
    );
  }

  public deleteProduct(id: number) {
    return this.http.patch(`${this.API_URL}/${id}`, { isDeleted: true }).pipe(
      catchError((error) => {
        console.error('Error updating product:', error);
        return throwError(() => error);
      })
    );
  }

  public deleteProductPermanently(id: number) {
    return this.http.delete(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error deleting product:', error);
        return throwError(() => error);
      })
    );
  }

  public getNextProductId(): Observable<number> {
    return this.http.get(`${this.API_URL}?_sort=id&_order=desc&_limit=1`).pipe(
      map((products: any) => {
        if (products && products.length > 0) {
          return products[0].id + 1;
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

  public getCategories(): any[] {
    return productCategories;
  }
}
