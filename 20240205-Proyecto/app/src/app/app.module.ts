import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './components/footer/footer.component';
import { MainComponent } from './components/main/main.component';
import { NavbarComponent } from './components/navbar/navbar.component';

import { OrdersFormComponent } from './components/main/orders/orders-form/orders-form.component';
import { OrdersListComponent } from './components/main/orders/orders-list/orders-list.component';
import { ProductsFormComponent } from './components/main/products/products-form/products-form.component';
import { ProductsListComponent } from './components/main/products/products-list/products-list.component';
import { SuppliersFormComponent } from './components/main/suppliers/suppliers-form/suppliers-form.component';
import { SuppliersListComponent } from './components/main/suppliers/suppliers-list/suppliers-list.component';
import { DeleteModalComponent } from './components/shared/modals/delete-modal/delete-modal.component';

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HomeComponent } from './components/main/home/home.component';
import { LoginComponent } from './components/main/login/login.component';
import { ManagementComponent } from './components/main/management/management.component';
import { OrdersDetailComponent } from './components/main/orders/orders-detail/orders-detail.component';
import { ProductsDetailComponent } from './components/main/products/products-detail/products-detail.component';
import { SuppliersDetailComponent } from './components/main/suppliers/suppliers-detail/suppliers-detail.component';
import { AuthGuard } from './guards/auth.guard';
import { CategoryFilterPipe } from './pipes/filter/category-filter.pipe';
import { DeleteFilterPipe } from './pipes/filter/delete-filter.pipe';
import { SearchFilterPipe } from './pipes/filter/search-filter.pipe';
import { StatusFilterPipe } from './pipes/filter/status-filter.pipe';
import { SlicePipe } from './pipes/slice.pipe';
import { LocationSortPipe } from './pipes/sort/location-sort.pipe';
import { PriceSortPipe } from './pipes/sort/price-sort.pipe';
import { SupplierSortPipe } from './pipes/sort/supplier-sort.pipe';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    NavbarComponent,
    FooterComponent,
    SuppliersFormComponent,
    SuppliersListComponent,
    ProductsFormComponent,
    ProductsListComponent,
    OrdersFormComponent,
    OrdersListComponent,
    DeleteModalComponent,
    SearchFilterPipe,
    SlicePipe,
    HomeComponent,
    StatusFilterPipe,
    CategoryFilterPipe,
    DeleteFilterPipe,
    PriceSortPipe,
    LocationSortPipe,
    SupplierSortPipe,
    OrdersDetailComponent,
    ProductsDetailComponent,
    SuppliersDetailComponent,
    ManagementComponent,
    LoginComponent,
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
    FontAwesomeModule,
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent],
})
export class AppModule {}
