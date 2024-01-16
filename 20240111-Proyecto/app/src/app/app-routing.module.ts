import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AboutComponent } from './components/main/about/about.component';
import { HomeComponent } from './components/main/home/home.component';
import { OrdersFormComponent } from './components/main/orders/orders-form/orders-form.component';
import { OrdersListComponent } from './components/main/orders/orders-list/orders-list.component';
import { ProductsFormComponent } from './components/main/products/products-form/products-form.component';
import { ProductsListComponent } from './components/main/products/products-list/products-list.component';
import { SuppliersFormComponent } from './components/main/suppliers/suppliers-form/suppliers-form.component';
import { SuppliersListComponent } from './components/main/suppliers/suppliers-list/suppliers-list.component';

const suppliersRoutes: Routes = [
  { path: '', component: SuppliersListComponent },
  { path: 'add', component: SuppliersFormComponent },
  { path: 'edit/:supplierId', component: SuppliersFormComponent },
];

const productsRoutes: Routes = [
  { path: '', component: ProductsListComponent },
  { path: 'add', component: ProductsFormComponent },
  { path: 'edit/:productId', component: ProductsFormComponent },
];

const ordersRoutes: Routes = [
  { path: '', component: OrdersListComponent },
  { path: 'add', component: OrdersFormComponent },
  { path: ':orderId', component: OrdersFormComponent },
];

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'suppliers', children: suppliersRoutes },
  { path: 'products', children: productsRoutes },
  { path: 'orders', children: ordersRoutes },
  { path: '', pathMatch: 'full', redirectTo: 'home' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
