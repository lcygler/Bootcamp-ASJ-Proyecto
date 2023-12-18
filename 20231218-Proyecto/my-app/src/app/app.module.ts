import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AboutComponent } from './components/about/about.component';
import { FooterComponent } from './components/footer/footer.component';
import { MainComponent } from './components/main/main.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';

import { OrdenesFormComponent } from './components/ordenes/ordenes-form/ordenes-form.component';
import { OrdenesListaComponent } from './components/ordenes/ordenes-lista/ordenes-lista.component';
import { OrdenesComponent } from './components/ordenes/ordenes.component';

import { ProductosFormComponent } from './components/productos/productos-form/productos-form.component';
import { ProductosListaComponent } from './components/productos/productos-lista/productos-lista.component';
import { ProductosComponent } from './components/productos/productos.component';

import { ProveedoresFormComponent } from './components/proveedores/proveedores-form/proveedores-form.component';
import { ProveedoresListaComponent } from './components/proveedores/proveedores-lista/proveedores-lista.component';
import { ProveedoresComponent } from './components/proveedores/proveedores.component';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    NavbarComponent,
    FooterComponent,
    SidebarComponent,
    AboutComponent,
    ProveedoresFormComponent,
    ProveedoresListaComponent,
    ProductosFormComponent,
    ProductosListaComponent,
    OrdenesFormComponent,
    OrdenesListaComponent,
    ProveedoresComponent,
    ProductosComponent,
    OrdenesComponent,
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
