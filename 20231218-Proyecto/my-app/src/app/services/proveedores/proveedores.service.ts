import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { data } from 'src/app/data/proveedores';

@Injectable({
  providedIn: 'root',
})
export class ProveedoresService {
  proveedores = [...data];

  constructor(private http: HttpClient) {}

  public getProveedores(): any[] {
    return this.proveedores;
  }

  public getProveedorById(id: string): any | undefined {
    const proveedor = this.proveedores.find((p) => p.id === id);
    return proveedor;
  }

  public postProveedor(proveedor: any) {
    if (proveedor) {
      const ultimoProveedor = this.proveedores[this.proveedores.length - 1];
      const nuevoId = ultimoProveedor ? parseInt(ultimoProveedor.id) + 1 : 1;
      proveedor.id = nuevoId;
      this.proveedores.push(proveedor);
    }
  }

  public putProveedor(proveedor: any): boolean {
    let isUpdated = false;
    const index = this.proveedores.indexOf(proveedor);

    if (index !== -1) {
      this.proveedores[index] = proveedor;
      isUpdated = true;
    }

    return isUpdated;
  }

  public deleteProveedor(proveedor: any): boolean {
    let isDeleted = false;
    const index = this.proveedores.indexOf(proveedor);

    if (index !== -1) {
      this.proveedores.splice(index, 1);
      isDeleted = true;
    }

    return isDeleted;
  }
}
