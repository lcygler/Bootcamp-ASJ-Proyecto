import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OrderService } from 'src/app/services/order.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-orders-list',
  templateUrl: './orders-list.component.html',
  styleUrls: ['./orders-list.component.css'],
})
export class OrdersListComponent implements OnInit {
  orderList: any[] = [];
  filteredOrders: any[] = [];
  searchTerm: string = '';
  orderToDeleteId: number | null = null;
  deleteMessage: string = '';

  itemsPerPage: number = 5;
  totalPages: number = 1;
  currentPage: number = 1;

  constructor(
    private router: Router,
    private orderService: OrderService,
    private toastService: ToastService
  ) {}

  ngOnInit() {
    this.getOrders();
  }

  getOrders() {
    this.orderService.getOrders().subscribe((res) => {
      this.orderList = res;
      this.filterOrders();
      this.updateTotalPages();
    });
  }

  addOrder() {
    this.router.navigate(['/orders/add']);
  }

  openOrder(id: number) {
    this.router.navigate([`/orders/${id}`]);
  }

  confirmDelete(id: number) {
    this.deleteMessage = `¿Está seguro de que desea cancelar la orden #${id}?`;
    this.orderToDeleteId = id;
  }

  deleteOrder() {
    if (this.orderToDeleteId) {
      this.orderService.deleteOrder(this.orderToDeleteId).subscribe((res) => {
        this.getOrders();
        this.orderToDeleteId = null;
        this.toastService.showSuccessToast('Orden cancelada correctamente!');
      });
    }
  }

  filterOrders() {
    this.currentPage = 1;

    if (this.searchTerm) {
      this.filteredOrders = this.orderList.filter((item) =>
        JSON.stringify(item)
          .toLowerCase()
          .includes(this.searchTerm.toLowerCase())
      );
      this.updateTotalPages();
    } else {
      this.filteredOrders = [...this.orderList];
      this.updateTotalPages();
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
  }

  updateTotalPages(): void {
    const minPages = 1;

    this.totalPages = Math.max(
      minPages,
      Math.ceil(this.filteredOrders.length / this.itemsPerPage)
    );
  }

  hasMoreItems(): boolean {
    const start = (this.currentPage - 1) * this.itemsPerPage;
    return start + this.itemsPerPage < this.filteredOrders.length;
  }

  calculateRange(): string {
    const start = (this.currentPage - 1) * this.itemsPerPage + 1;
    const end = Math.min(
      this.currentPage * this.itemsPerPage,
      this.filteredOrders.length
    );
    return `Mostrando ${start} - ${end} de ${this.filteredOrders.length} ordenes`;
  }
}
