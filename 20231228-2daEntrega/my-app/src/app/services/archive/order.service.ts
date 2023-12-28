import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { data } from 'src/app/data/orders';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  orders = [...data];

  constructor(private http: HttpClient) {}

  public getOrders(): any[] {
    return this.orders;
  }

  public getOrderById(id: string): any | undefined {
    const order = this.orders.find((o) => o.id === id);
    return order;
  }

  public createOrder(order: any) {
    if (order) {
      const lastOrder = this.orders[this.orders.length - 1];
      const newId = lastOrder ? parseInt(lastOrder.id) + 1 : 1;
      order.id = newId;
      this.orders.push(order);
    }
  }

  public updateOrder(order: any): boolean {
    let isUpdated = false;
    const index = this.orders.indexOf(order);

    if (index !== -1) {
      this.orders[index] = order;
      isUpdated = true;
    }

    return isUpdated;
  }

  public deleteOrder(order: any): boolean {
    let isDeleted = false;
    const index = this.orders.indexOf(order);

    if (index !== -1) {
      this.orders.splice(index, 1);
      isDeleted = true;
    }

    return isDeleted;
  }
}
