import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AboutComponent } from './components/about/about.component';
import { FormularioComponent as OrdenesFormularioComponent } from './components/ordenes/formulario/formulario.component';
import { OrdenesComponent } from './components/ordenes/ordenes.component';
import { FormularioComponent as ProductosFormularioComponent } from './components/productos/formulario/formulario.component';
import { ProductosComponent } from './components/productos/productos.component';
import { FormularioComponent as ProveedoresFormularioComponent } from './components/proveedores/formulario/formulario.component';
import { ProveedoresComponent } from './components/proveedores/proveedores.component';

const routes: Routes = [
  { path: 'proveedores', component: ProveedoresComponent },
  { path: 'proveedores/alta', component: ProveedoresFormularioComponent },
  // { path: 'proveedores/editar/:idProveedor', component: ProveedoresFormularioComponent },

  { path: 'productos', component: ProductosComponent },
  { path: 'productos/alta', component: ProductosFormularioComponent },
  // { path: 'proveedores/editar/:idProducto', component: ProductosFormularioComponent },

  { path: 'ordenes', component: OrdenesComponent },
  { path: 'ordenes/alta', component: OrdenesFormularioComponent },
  // { path: 'proveedores/editar/:idOrdenes', component: OrdenesFormularioComponent },

  { path: 'projects', pathMatch: 'full', redirectTo: 'projects/todolist' },
  { path: 'about', component: AboutComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
