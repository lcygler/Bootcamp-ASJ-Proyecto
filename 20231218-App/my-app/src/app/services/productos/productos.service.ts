import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { data } from 'src/app/data/productos';

@Injectable({
  providedIn: 'root',
})
export class ProductosService {
  productos = [...data];

  constructor(private http: HttpClient) {}

  public getProductos(): any[] {
    return this.productos;
  }

  public getProductoById(id: string): any | undefined {
    const producto = this.productos.find((p) => p.id === id);
    return producto;
  }

  public postProducto(producto: any) {
    if (producto) {
      const ultimoProducto = this.productos[this.productos.length - 1];
      const nuevoId = ultimoProducto ? parseInt(ultimoProducto.id) + 1 : 1;
      producto.id = nuevoId;
      this.productos.push(producto);
    }
  }

  public putProducto(producto: any): boolean {
    let isUpdated = false;
    const index = this.productos.indexOf(producto);

    if (index !== -1) {
      this.productos[index] = producto;
      isUpdated = true;
    }

    return isUpdated;
  }

  public deleteProducto(producto: any): boolean {
    let isDeleted = false;
    const index = this.productos.indexOf(producto);

    if (index !== -1) {
      this.productos.splice(index, 1);
      isDeleted = true;
    }

    return isDeleted;
  }
}
