import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { faCalendar } from '@fortawesome/free-regular-svg-icons';
import { forkJoin } from 'rxjs';

import { Order } from 'src/app/models/order/IOrder';
import { OrderDetail } from 'src/app/models/order/IOrderDetail';
import { Category } from 'src/app/models/product/ICategory';
import { Product } from 'src/app/models/product/IProduct';
import { Supplier } from 'src/app/models/supplier/ISupplier';

import { ToastService } from 'src/app/services/common/toast.service';
import { OrderDetailService } from 'src/app/services/order/order-detail.service';
import { OrderService } from 'src/app/services/order/order.service';
import { ProductService } from 'src/app/services/product/product.service';
import { SupplierService } from 'src/app/services/supplier/supplier.service';

@Component({
  selector: 'app-orders-form',
  templateUrl: './orders-form.component.html',
  styleUrls: ['./orders-form.component.css'],
})
export class OrdersFormComponent implements OnInit {
  order: Order = {
    number: '',
    issueDate: null,
    deliveryDate: null,
    comments: '',
    total: null,
    supplier: {},
  };

  orderId: number | null = null;
  nextOrderId: number | null = null;
  isAddView: boolean = false;
  isProductAdded: boolean = false;
  todayDate: string = '';

  supplierList: Supplier[] = [];
  productList: Product[] = [];
  categoryList: Category[] = [];
  orderDetailList: OrderDetail[] = [];
  filteredProducts: Product[] = [];

  selectedSupplierId: number | null = null;
  selectedProductId: number | null = null;
  selectedQuantity: number | null = null;
  productToDeleteId: number | null = null;
  deleteMessage: string = '';

  faCalendar = faCalendar;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private orderService: OrderService,
    private supplierService: SupplierService,
    private productService: ProductService,
    private toastService: ToastService,
    private orderDetailService: OrderDetailService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('orderId');

    if (id) {
      this.orderId = parseInt(id);
      this.getOrderById(this.orderId);
      this.getOrderDetails(this.orderId);
    }

    this.todayDate = this.getTodayDate();
    this.isAddView = this.isAddRoute();
    this.getSuppliers();
    this.getProducts();
  }

  getOrderById(id: number): void {
    if (id) {
      this.orderService.getOrderById(id).subscribe((res) => {
        this.order = res;
      });
    }
  }

  getOrderDetails(id: number) {
    if (id) {
      this.orderService.getOrderDetails(id).subscribe((res) => {
        this.orderDetailList = res;
      });
    }
  }

  getSuppliers() {
    this.supplierService.getSuppliers().subscribe((res) => {
      this.supplierList = res;
    });
  }

  getProducts() {
    this.productService.getProducts().subscribe((res) => {
      this.productList = res;
    });
  }

  onSubmit(form: NgForm) {
    if (!this.isAddView) return;

    if (form.invalid) {
      console.error('Form contains errors.');
      return;
    }

    const formData = form.value;

    const order: Order = {
      issueDate: this.getTodayDate() + 'T00:00:00',
      deliveryDate: formData.deliveryDate + 'T00:00:00',
      comments: formData.comments.trim(),
      total: this.calculateTotal(),
      supplier: { id: this.selectedSupplierId! }, // { id: formData.supplier }
    };

    // Add new order
    this.orderService.createOrder(order).subscribe((createdOrder: Order) => {
      let createdOrderDetails = 0;

      // Add order details
      for (const orderDetail of this.orderDetailList) {
        const newOrderDetail: OrderDetail = {
          order: { id: createdOrder.id },
          product: { id: orderDetail.product.id },
          quantity: orderDetail.quantity,
          price: orderDetail.product.price!,
        };

        this.orderDetailService
          .createOrderDetail(newOrderDetail)
          .subscribe((res) => {
            // console.log(`Producto #${orderDetail.product.id} agregado a la orden`);
            createdOrderDetails++;

            if (createdOrderDetails === this.orderDetailList.length) {
              this.toastService.showSuccessToast(
                'Orden agregada correctamente!'
              );
              form.reset();
              this.navigateToOrders();
            }
          });
      }
    });
  }

  addProduct() {
    if (!this.selectedProductId) {
      return;
    }

    this.productService
      .getProductById(this.selectedProductId)
      .subscribe((product) => {
        const existingProduct = this.orderDetailList.find(
          (orderDetail) => orderDetail.product.id === product.id
        );

        if (existingProduct) {
          existingProduct.quantity += this.selectedQuantity!;

          this.toastService.showSuccessToast(
            'Cantidad actualizada correctamente!'
          );
        } else {
          const orderDetail: OrderDetail = {
            product: product,
            quantity: this.selectedQuantity!,
            price: product.price!,
          };

          this.orderDetailList.push(orderDetail);
          this.isProductAdded = true;
          this.toastService.showSuccessToast(
            'Producto agregado correctamente!'
          );
        }
      });
  }

  confirmDelete(id: number) {
    this.deleteMessage = `¿Está seguro de que desea remover el producto #${id}?`;
    this.productToDeleteId = id;
  }

  removeProduct() {
    if (this.productToDeleteId) {
      const index = this.orderDetailList.findIndex(
        (orderDetail) => orderDetail.product.id === this.productToDeleteId
      );

      if (index !== -1) {
        this.orderDetailList.splice(index, 1);
        this.toastService.showSuccessToast('Producto removido correctamente!');
      } else {
        console.warn(
          `Producto con ID ${this.productToDeleteId} no encontrado.`
        );
      }

      this.deleteMessage = '';
      this.productToDeleteId = null;
    }
  }

  resetForm(form: NgForm) {
    this.selectedSupplierId = null;
    this.selectedProductId = null;
    this.selectedQuantity = null;
    this.isProductAdded = false;

    form.reset();
    form.control.markAsPristine();
    form.control.markAsUntouched();
    form.control.updateValueAndValidity();

    this.todayDate = this.getTodayDate();
    const formattedDate = this.formatDateAsc(this.todayDate);
    form.control.patchValue({ issueDate: formattedDate });
  }

  onSupplierChange(selectedSupplierId: number) {
    if (selectedSupplierId) {
      this.productService
        .getProductsBySupplier(selectedSupplierId)
        .subscribe((res) => {
          this.filteredProducts = res;
        });
    } else {
      this.filteredProducts = [];
    }
  }

  getTodayDate(): string {
    const currentDate = new Date();
    const year = currentDate.getFullYear();
    const month = ('0' + (currentDate.getMonth() + 1)).slice(-2);
    const day = ('0' + currentDate.getDate()).slice(-2);
    return `${year}-${month}-${day}`;
  }

  getMinDate(): string {
    const currentDate = new Date(this.todayDate);
    currentDate.setDate(currentDate.getDate() + 2);

    const year = currentDate.getFullYear();
    const month = ('0' + (currentDate.getMonth() + 1)).slice(-2);
    const day = ('0' + currentDate.getDate()).slice(-2);

    return `${year}-${month}-${day}`;
  }

  getMaxDate(): string {
    const currentDate = new Date(this.todayDate);
    currentDate.setDate(currentDate.getDate() + 365);

    const year = currentDate.getFullYear();
    const month = ('0' + (currentDate.getMonth() + 1)).slice(-2);
    const day = ('0' + currentDate.getDate()).slice(-2);

    return `${year}-${month}-${day}`;
  }

  isDeliveryDateValid() {
    const deliveryDate = new Date(this.order.deliveryDate!);
    const minDate = new Date(this.getMinDate());
    const maxDate = new Date(this.getMaxDate());

    return deliveryDate >= minDate && deliveryDate <= maxDate;
  }

  calculateTotal(): number {
    let total = 0;

    for (const item of this.orderDetailList) {
      total += item.price * item.quantity;
    }

    return total;
  }

  formatDateDesc(dateString: string): string {
    const parts = dateString.split('-');
    if (parts.length === 3) {
      const [day, month, year] = parts;
      return `${year}-${month}-${day}`;
    } else {
      return dateString;
    }
  }

  formatDateAsc(dateString: string): string {
    const parts = dateString.split('-');
    if (parts.length === 3) {
      const [year, month, day] = parts;
      return `${day}/${month}/${year}`;
    } else {
      return dateString;
    }
  }

  navigateToOrders() {
    this.router.navigate(['/orders']);
  }

  isAddRoute(): boolean {
    const route = this.router.url;
    return route.includes('/orders/add');
  }

  getStatusClass(status: string): string {
    switch (status) {
      case 'Cancelado':
        return 'order-cancelled';
      case 'En Proceso':
        return 'order-in-progress';
      case 'En Camino':
        return 'order-on-its-way';
      case 'Entregado':
        return 'order-delivered';
      default:
        return 'order-in-progress';
    }
  }
}