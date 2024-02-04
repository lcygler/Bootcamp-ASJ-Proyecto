import { Component, OnInit } from '@angular/core';
import {
  faFileLines,
  faTags,
  faUsers,
} from '@fortawesome/free-solid-svg-icons';
import { Order } from 'src/app/models/order/IOrder';
import { Product } from 'src/app/models/product/IProduct';
import { Supplier } from 'src/app/models/supplier/ISupplier';
import { OrderService } from 'src/app/services/order/order.service';
import { ProductService } from 'src/app/services/product/product.service';
import { SupplierService } from 'src/app/services/supplier/supplier.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  orderList: Order[] = [];
  supplierList: Supplier[] = [];
  productList: Product[] = [];

  faUsers = faUsers;
  faTags = faTags;
  faFileLines = faFileLines;

  constructor(
    private orderService: OrderService,
    private supplierService: SupplierService,
    private productService: ProductService
  ) {}

  ngOnInit() {
    this.getSuppliers();
    this.getProducts();
    this.getOrders();
  }

  getSuppliers() {
    this.supplierService.getSuppliers().subscribe((res) => {
      this.supplierList = res.filter((s) => !s.isDeleted);
    });
  }

  getProducts() {
    this.productService.getProducts().subscribe((res) => {
      this.productList = res.filter(
        (p) => !p.supplier.isDeleted && !p.isDeleted
      );
    });
  }

  getOrders() {
    this.orderService.getOrders().subscribe((res) => {
      this.orderList = res;
    });
  }
}
