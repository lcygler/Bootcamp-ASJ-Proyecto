import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductosService } from 'src/app/services/productos/productos.service';

@Component({
  selector: 'app-productos-lista',
  templateUrl: './productos-lista.component.html',
  styleUrls: ['./productos-lista.component.css'],
})
export class ProductosListaComponent implements OnInit {
  productos: any[] = [];
  productosMostrados: any[] = [];

  elementosPorPagina = 5;
  totalPaginas: number = 0;
  paginaActual = 1;

  constructor(
    private router: Router,
    private ProductosService: ProductosService
  ) {}

  ngOnInit() {
    this.actualizarLista();
  }

  cargarProductos() {
    this.productos = this.ProductosService.getProductos();
  }

  agregarProducto() {
    this.router.navigate(['/productos/alta']);
  }

  editarProducto(id: string) {
    this.router.navigate([`/productos/editar/${id}`]);
  }

  eliminarProducto(proveedor: any) {
    const confirm = window.confirm(
      '¿Seguro que desea eliminar este proveedor?'
    );

    if (confirm) {
      this.ProductosService.deleteProducto(proveedor);
      this.actualizarLista();
    }
  }

  actualizarPagina(
    navegacion: 'anterior' | 'siguiente' | 'primera' | 'ultima'
  ): void {
    switch (navegacion) {
      case 'anterior':
        if (this.paginaActual > 1) {
          this.paginaActual--;
        }
        break;
      case 'siguiente':
        if (this.hayMasElementos()) {
          this.paginaActual++;
        }
        break;
      case 'primera':
        this.paginaActual = 1;
        break;
      case 'ultima':
        this.paginaActual = this.totalPaginas;
        break;
    }

    this.actualizarLista();
  }

  hayMasElementos(): boolean {
    const startIndex = (this.paginaActual - 1) * this.elementosPorPagina;
    return startIndex + this.elementosPorPagina < this.productos.length;
  }

  actualizarLista(): void {
    this.cargarProductos();

    const startIndex = (this.paginaActual - 1) * this.elementosPorPagina;
    const endIndex = startIndex + this.elementosPorPagina;
    this.productosMostrados = this.productos.slice(startIndex, endIndex);

    this.totalPaginas = Math.ceil(
      this.productos.length / this.elementosPorPagina
    );
  }
}
