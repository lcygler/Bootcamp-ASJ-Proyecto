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
import { FormularioComponent as OrdenesFormularioComponent } from './components/ordenes/formulario/formulario.component';
import { ListadoComponent as OrdenesListadoComponent } from './components/ordenes/listado/listado.component';
import { OrdenesComponent } from './components/ordenes/ordenes.component';
import { FormularioComponent as ProductosFormularioComponent } from './components/productos/formulario/formulario.component';
import { ListadoComponent as ProductosListadoComponent } from './components/productos/listado/listado.component';
import { ProductosComponent } from './components/productos/productos.component';
import { FormularioComponent as ProveedoresFormularioComponent } from './components/proveedores/formulario/formulario.component';
import { ListadoComponent as ProveedoresListadoComponent } from './components/proveedores/listado/listado.component';
import { ProveedoresComponent } from './components/proveedores/proveedores.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    NavbarComponent,
    FooterComponent,
    SidebarComponent,
    AboutComponent,
    ProveedoresFormularioComponent,
    ProveedoresListadoComponent,
    ProductosFormularioComponent,
    ProductosListadoComponent,
    OrdenesFormularioComponent,
    OrdenesListadoComponent,
    ProveedoresComponent,
    ProductosComponent,
    OrdenesComponent,
  ],
  imports: [AppRoutingModule, BrowserModule, NgbModule, FormsModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
