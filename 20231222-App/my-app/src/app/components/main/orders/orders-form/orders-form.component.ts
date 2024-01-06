import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-orders-form',
  templateUrl: './orders-form.component.html',
  styleUrls: ['./orders-form.component.css'],
})
export class OrdersFormComponent implements OnInit {
  order: any = {};

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private orderService: OrderService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('orderId');

    if (id) {
      this.order = this.orderService.getOrderById(id);
    }
  }

  saveChanges() {
    if (this.order) {
      const route = this.router.url;

      if (route.includes('/orders/add')) {
        // Add new order
        this.orderService.createOrder(this.order);
        alert('Order added successfully.');
      } else if (route.includes('/orders/edit')) {
        // Update existing order
        this.orderService.updateOrder(this.order);
        alert('Changes saved successfully.');
      }

      this.router.navigate(['/orders']);
    }
  }
}
