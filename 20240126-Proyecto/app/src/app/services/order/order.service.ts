import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Status } from 'src/app/models/order/IStatus';
import { Order } from '../../models/order/IOrder';
import { OrderDetail } from '../../models/order/IOrderDetail';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  private API_URL = 'http://localhost:8080/orders';

  constructor(private http: HttpClient) {}

  public getOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.API_URL}`).pipe(
      map((orders: any) =>
        orders.sort((a: any, b: any) => {
          // Order by status (cancelled orders last)
          const statusOrder =
            this.getStatusOrder(b.status) - this.getStatusOrder(a.status);

          if (statusOrder !== 0) {
            return statusOrder;
          } else {
            // Order by issueDate and ID (more recent first)
            const dateA = new Date(a.issueDate).getTime();
            const dateB = new Date(b.issueDate).getTime();
            return dateB - dateA || b.id - a.id;
          }
        })
      ),
      catchError((error) => {
        console.error('Error fetching order list:', error);
        return throwError(() => error);
      })
    );
  }

  public getOrdersByRange(start: number, end: number): Observable<Order[]> {
    return this.http
      .get<Order[]>(`${this.API_URL}?_start=${start}&_end=${end}`)
      .pipe(
        catchError((error) => {
          console.error('Error fetching orders by range:', error);
          return throwError(() => error);
        })
      );
  }

  public getOrderById(id: number): Observable<Order> {
    return this.http.get<Order>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error fetching order by ID:', error);
        return throwError(() => error);
      })
    );
  }

  public getOrderDetails(id: number): Observable<OrderDetail[]> {
    return this.http.get<OrderDetail[]>(`${this.API_URL}/${id}/details`).pipe(
      catchError((error) => {
        console.error('Error fetching order items:', error);
        return throwError(() => error);
      })
    );
  }

  public createOrder(order: Order): Observable<Order> {
    return this.http.post<Order>(`${this.API_URL}`, order).pipe(
      catchError((error) => {
        console.error('Error creating order:', error);
        return throwError(() => error);
      })
    );
  }

  public updateOrder(id: number, order: Order): Observable<Order> {
    return this.http.patch<Order>(`${this.API_URL}/${id}`, order).pipe(
      catchError((error) => {
        console.error('Error updating order:', error);
        return throwError(() => error);
      })
    );
  }

  public deleteOrder(id: number): Observable<Order> {
    return this.http
      .patch<Order>(`${this.API_URL}/${id}`, { isActive: false })
      .pipe(
        catchError((error) => {
          console.error('Error updating order:', error);
          return throwError(() => error);
        })
      );
  }

  public hardDeleteOrder(id: number): Observable<Order> {
    return this.http.delete<Order>(`${this.API_URL}/${id}`).pipe(
      catchError((error) => {
        console.error('Error deleting order:', error);
        return throwError(() => error);
      })
    );
  }

  private getStatusOrder(status: Partial<Status>): number {
    switch (status?.name) {
      case 'Cancelado':
        return 0;
      default:
        return 1;
    }
  }
}
