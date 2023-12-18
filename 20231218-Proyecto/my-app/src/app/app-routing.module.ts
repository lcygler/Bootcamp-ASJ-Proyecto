import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AboutComponent } from './components/about/about.component';
import { OrdenesFormComponent } from './components/ordenes/ordenes-form/ordenes-form.component';
import { OrdenesComponent } from './components/ordenes/ordenes.component';
import { ProductosFormComponent } from './components/productos/productos-form/productos-form.component';
import { ProductosComponent } from './components/productos/productos.component';
import { ProveedoresFormComponent } from './components/proveedores/proveedores-form/proveedores-form.component';
import { ProveedoresComponent } from './components/proveedores/proveedores.component';

const proveedoresRoutes: Routes = [
  { path: '', component: ProveedoresComponent },
  { path: 'alta', component: ProveedoresFormComponent },
  { path: 'editar/:idProveedor', component: ProveedoresFormComponent },
];

const productosRoutes: Routes = [
  { path: '', component: ProductosComponent },
  { path: 'alta', component: ProductosFormComponent },
  { path: 'editar/:idProducto', component: ProductosFormComponent },
];

const ordenesRoutes: Routes = [
  { path: '', component: OrdenesComponent },
  { path: 'alta', component: OrdenesFormComponent },
  { path: 'editar/:idOrden', component: OrdenesFormComponent },
];

const routes: Routes = [
  { path: 'proveedores', children: proveedoresRoutes },
  { path: 'productos', children: productosRoutes },
  { path: 'ordenes', children: ordenesRoutes },
  { path: 'about', component: AboutComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
