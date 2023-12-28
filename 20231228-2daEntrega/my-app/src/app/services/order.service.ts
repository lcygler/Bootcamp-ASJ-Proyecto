import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  private API_URL = 'http://localhost:3000/orders';

  constructor(private http: HttpClient) {}

  public getOrders(): Observable<any> {
    return this.http.get(`${this.API_URL}?_expand=supplier`).pipe(
      map((orders: any) =>
        orders.sort((a: any, b: any) => {
          if (b.isActive - a.isActive !== 0) {
            // Ordenar por isActive
            return b.isActive - a.isActive;
          } else {
            // Sino ordenar por id
            return a.id - b.id;
          }
        })
      ),
      catchError((error) => {
        console.error('Error fetching order list:', error);
        return throwError(() => error);
      })
    );
  }

  public getOrdersByRange(start: number, end: number): Observable<any> {
    return this.http
      .get(`${this.API_URL}?_start=${start}&_end=${end}&_expand=supplier`)
      .pipe(
        map((orders: any) =>
          orders.sort((a: any, b: any) => {
            if (b.isActive - a.isActive !== 0) {
              // Ordenar por isActive
              return b.isActive - a.isActive;
            } else {
              // Sino ordenar por id
              return a.id - b.id;
            }
          })
        ),
        catchError((error) => {
          console.error('Error fetching orders by range:', error);
          return throwError(() => error);
        })
      );
  }

  public getOrderById(id: number): Observable<any> {
    return this.http.get(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching order by ID:', error);
        return throwError(() => error);
      })
    );
  }

  public createOrder(order: any) {
    return this.http.post(`${this.API_URL}`, order).pipe(
      catchError((error) => {
        console.error('Error creating order:', error);
        return throwError(() => error);
      })
    );
  }

  public updateOrder(id: number, data: any) {
    return this.http.patch(`${this.API_URL}/${id}`, data).pipe(
      catchError((error) => {
        console.error('Error updating order:', error);
        return throwError(() => error);
      })
    );
  }

  public deleteOrder(id: number) {
    return this.http.patch(`${this.API_URL}/${id}`, { isActive: false }).pipe(
      catchError((error) => {
        console.error('Error updating order:', error);
        return throwError(() => error);
      })
    );
  }

  public deleteOrderPermanently(id: number) {
    return this.http.delete(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error deleting order:', error);
        return throwError(() => error);
      })
    );
  }

  public getNextOrderId(): Observable<number> {
    return this.http.get(`${this.API_URL}?_sort=id&_order=desc&_limit=1`).pipe(
      map((orders: any) => {
        if (orders && orders.length > 0) {
          return orders[0].id + 1;
        } else {
          return 1;
        }
      }),
      catchError((error) => {
        console.error('Error fetching latest order:', error);
        return throwError(() => error);
      })
    );
  }
}
