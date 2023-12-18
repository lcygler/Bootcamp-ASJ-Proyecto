import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { data } from 'src/app/data/ordenes';

@Injectable({
  providedIn: 'root',
})
export class OrdenesService {
  ordenes = [...data];

  constructor(private http: HttpClient) {}

  public getOrdenes(): any[] {
    return this.ordenes;
  }

  public getOrdenById(id: string): any | undefined {
    const orden = this.ordenes.find((p) => p.id === id);
    return orden;
  }

  public postOrden(orden: any) {
    if (orden) {
      const ultimoOrden = this.ordenes[this.ordenes.length - 1];
      const nuevoId = ultimoOrden ? parseInt(ultimoOrden.id) + 1 : 1;
      orden.id = nuevoId;
      this.ordenes.push(orden);
    }
  }

  public putOrden(orden: any): boolean {
    let isUpdated = false;
    const index = this.ordenes.indexOf(orden);

    if (index !== -1) {
      this.ordenes[index] = orden;
      isUpdated = true;
    }

    return isUpdated;
  }

  public deleteOrden(orden: any): boolean {
    let isDeleted = false;
    const index = this.ordenes.indexOf(orden);

    if (index !== -1) {
      this.ordenes.splice(index, 1);
      isDeleted = true;
    }

    return isDeleted;
  }
}
