import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './components/main/home/home.component';
import { LoginComponent } from './components/main/login/login.component';
import { ManagementComponent } from './components/main/management/management.component';
import { OrdersFormComponent } from './components/main/orders/orders-form/orders-form.component';
import { OrdersListComponent } from './components/main/orders/orders-list/orders-list.component';
import { ProductsDetailComponent } from './components/main/products/products-detail/products-detail.component';
import { ProductsFormComponent } from './components/main/products/products-form/products-form.component';
import { ProductsListComponent } from './components/main/products/products-list/products-list.component';
import { SuppliersDetailComponent } from './components/main/suppliers/suppliers-detail/suppliers-detail.component';
import { SuppliersFormComponent } from './components/main/suppliers/suppliers-form/suppliers-form.component';
import { SuppliersListComponent } from './components/main/suppliers/suppliers-list/suppliers-list.component';
import { AuthGuard } from './guards/auth.guard';

const suppliersRoutes: Routes = [
  { path: '', component: SuppliersListComponent },
  { path: 'add', component: SuppliersFormComponent },
  { path: 'edit/:supplierId', component: SuppliersFormComponent },
  { path: ':supplierId', component: SuppliersDetailComponent },
];

const productsRoutes: Routes = [
  { path: '', component: ProductsListComponent },
  { path: 'add', component: ProductsFormComponent },
  { path: 'edit/:productId', component: ProductsFormComponent },
  { path: ':productId', component: ProductsDetailComponent },
];

const ordersRoutes: Routes = [
  { path: '', component: OrdersListComponent },
  { path: 'add', component: OrdersFormComponent },
  { path: 'edit/:orderId', component: OrdersFormComponent },
  { path: ':orderId', component: OrdersFormComponent },
];

const managementRoutes: Routes = [
  { path: '', component: ManagementComponent },
  { path: 'categories/:id', component: ManagementComponent },
  { path: 'industries/:id', component: ManagementComponent },
];

const routes: Routes = [
  { path: 'home', canActivate: [AuthGuard], component: HomeComponent },
  { path: 'suppliers', canActivate: [AuthGuard], children: suppliersRoutes },
  { path: 'products', canActivate: [AuthGuard], children: productsRoutes },
  { path: 'orders', canActivate: [AuthGuard], children: ordersRoutes },
  { path: 'management', canActivate: [AuthGuard], children: managementRoutes },
  { path: 'login', component: LoginComponent },
  { path: '', pathMatch: 'full', redirectTo: 'home' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
