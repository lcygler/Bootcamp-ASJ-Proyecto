import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './components/footer/footer.component';
import { AboutComponent } from './components/main/about/about.component';
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
import { CategoryFilterPipe } from './pipes/category-filter.pipe';
import { DeleteFilterPipe } from './pipes/delete-filter.pipe';
import { SearchFilterPipe } from './pipes/search-filter.pipe';
import { SlicePipe } from './pipes/slice.pipe';
import { StatusFilterPipe } from './pipes/status-filter.pipe';
import { PriceFilterPipe } from './pipes/price-filter.pipe';
import { LocationFilterPipe } from './pipes/location-filter.pipe';
import { SupplierFilterPipe } from './pipes/supplier-filter.pipe';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    NavbarComponent,
    FooterComponent,
    AboutComponent,
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
    PriceFilterPipe,
    LocationFilterPipe,
    SupplierFilterPipe,
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
    FontAwesomeModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
