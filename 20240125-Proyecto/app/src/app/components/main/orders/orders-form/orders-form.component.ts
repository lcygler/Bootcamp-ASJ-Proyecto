import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderService } from 'src/app/services/order.service';
import { ProductService } from 'src/app/services/product.service';
import { SupplierService } from 'src/app/services/supplier.service';
import { ToastService } from 'src/app/services/toast.service';
import { Order } from '../../../../models/IOrder';

@Component({
  selector: 'app-orders-form',
  templateUrl: './orders-form.component.html',
  styleUrls: ['./orders-form.component.css'],
})
export class OrdersFormComponent implements OnInit {
  order: Order = {
    issueDate: null,
    deliveryDate: null,
    comments: '',
    total: null,
    isActive: false,
    supplierId: null,
  };

  orderId: number | null = null;
  nextOrderId: number | null = null;
  isAddView: boolean = false;
  todayDate: string = '';
  productAdded: boolean = false;

  supplierList: any[] = [];
  productList: any[] = [];
  categoryList: any[] = [];
  orderItemList: any[] = [];
  filteredProducts: any[] = [];

  selectedSupplier: any;
  selectedProduct: any;
  selectedQuantity: any;
  deleteMessage: string = '';
  productToDeleteId: number | null = null;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private orderService: OrderService,
    private supplierService: SupplierService,
    private productService: ProductService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('orderId');

    if (id) {
      this.orderId = parseInt(id);
      this.getOrderById(this.orderId);
      this.getOrderItems(this.orderId);
    }

    this.todayDate = this.getTodayDate();

    this.isAddView = this.isAddRoute();
    this.getSuppliers();
    this.getProducts();
    this.getNextOrderId();
  }

  getOrderById(id: number): void {
    if (id) {
      this.orderService.getOrderById(id).subscribe((res) => {
        this.order = res;
      });
    }
  }

  getOrderItems(id: number) {
    if (id) {
      this.orderService.getOrderItems(id).subscribe((res) => {
        this.orderItemList = res;
      });
    }
  }

  getNextOrderId(): void {
    this.orderService.getNextOrderId().subscribe((res) => {
      this.nextOrderId = res;
    });
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
    if (!form.valid) {
      console.error('Invalid form.');
      return;
    }

    if (!this.isAddView) return;

    const formData = form.value;

    const order: Order = {
      // issueDate: this.formatDate(formData.issueDate),
      issueDate: this.getTodayDate(),
      deliveryDate: formData.deliveryDate,
      comments: formData.comments,
      total: this.calculateTotal(),
      isActive: this.order.id ? this.order.isActive : true,
      supplierId: this.selectedSupplier,
    };

    // Add new order
    this.orderService.createOrder(order).subscribe((createdOrder: any) => {
      for (const item of this.orderItemList) {
        const orderItems = {
          orderId: createdOrder?.id,
          productId: item.product.id,
          quantity: item.quantity,
          subtotal: item.subtotal,
          supplierId: formData.supplierId,
        };

        this.orderService.createOrderItems(orderItems).subscribe((res) => {
          console.log(`Se creó OrderItem de producto #${item.product.id}`);
        });
      }

      this.toastService.showSuccessToast('Orden agregada correctamente!');
    });

    form.reset();
    this.router.navigate(['/orders']);
  }

  addProduct() {
    this.productService
      .getProductById(this.selectedProduct)
      .subscribe((res) => {
        const product = res;

        const existingProduct = this.orderItemList.find(
          (item) => item.product.id === product.id
        );

        if (existingProduct) {
          // Actualizar cantidad y subtotal
          existingProduct.quantity += this.selectedQuantity;
          existingProduct.subtotal = (
            existingProduct.product.price * existingProduct.quantity
          ).toFixed(2);
          this.toastService.showSuccessToast(
            'Cantidad actualizada correctamente!'
          );
        } else {
          // Agregar nuevo producto
          const newProduct = {
            // id: ?
            orderId: this.nextOrderId,
            productId: product.productId,
            quantity: this.selectedQuantity,
            subtotal: (product.price * this.selectedQuantity).toFixed(2),
            product: {
              id: product.id,
              sku: product.sku,
              category: product.category,
              name: product.name,
              description: product.description,
              price: product.price,
              supplierId: product.supplierId,
              isDeleted: product.isDeleted,
            },
          };

          this.orderItemList.push(newProduct);
          this.productAdded = true;
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

  removeProduct(): void {
    if (this.productToDeleteId) {
      const index = this.orderItemList.findIndex(
        (item) => item.product.id === this.productToDeleteId
      );

      if (index !== -1) {
        this.orderItemList.splice(index, 1);
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
    this.selectedSupplier = null;
    this.selectedProduct = null;
    this.selectedQuantity = null;
    this.productAdded = false;

    form.reset();
    form.control.markAsPristine();
    form.control.markAsUntouched();
    form.control.updateValueAndValidity();

    this.todayDate = this.getTodayDate();
    const formattedDate = this.formatDateAsc(this.todayDate);
    form.control.patchValue({ issueDate: formattedDate });
  }

  onSupplierChange(selectedSupplier: number): void {
    if (selectedSupplier) {
      this.productService
        .getProductsBySupplier(selectedSupplier)
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

  calculateTotal(): number {
    let total = 0;

    for (const item of this.orderItemList) {
      total += parseFloat(item.subtotal);
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

  isAddRoute(): boolean {
    const route = this.router.url;
    return route.includes('/orders/add');
  }
}
