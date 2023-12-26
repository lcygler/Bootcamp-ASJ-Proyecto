import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-orders-list',
  templateUrl: './orders-list.component.html',
  styleUrls: ['./orders-list.component.css'],
})
export class OrdersListComponent implements OnInit {
  orderList: any[] = [];
  displayedOrders: any[] = [];

  itemsPerPage: number = 5;
  totalPages: number = 0;
  currentPage: number = 1;

  constructor(private router: Router, private orderService: OrderService) {}

  ngOnInit() {
    this.updateList();
  }

  getOrders() {
    this.orderList = this.orderService.getOrders();
  }

  addOrder() {
    this.router.navigate(['/orders/add']);
  }

  editOrder(id: string) {
    this.router.navigate([`/orders/edit/${id}`]);
  }

  deleteOrder(order: any) {
    const confirmDelete = window.confirm(
      'Are you sure you want to delete this order?'
    );

    if (confirmDelete) {
      this.orderService.deleteOrder(order);
      this.updateList();
    }
  }

  updatePage(navigation: 'prev' | 'next' | 'first' | 'last'): void {
    switch (navigation) {
      case 'prev':
        if (this.currentPage > 1) {
          this.currentPage--;
        }
        break;
      case 'next':
        if (this.hasMoreItems()) {
          this.currentPage++;
        }
        break;
      case 'first':
        this.currentPage = 1;
        break;
      case 'last':
        this.currentPage = this.totalPages;
        break;
    }

    this.updateList();
  }

  hasMoreItems(): boolean {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    return startIndex + this.itemsPerPage < this.orderList.length;
  }

  updateList(): void {
    this.getOrders();

    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.displayedOrders = this.orderList.slice(startIndex, endIndex);

    this.totalPages = Math.ceil(this.orderList.length / this.itemsPerPage);
  }
}
