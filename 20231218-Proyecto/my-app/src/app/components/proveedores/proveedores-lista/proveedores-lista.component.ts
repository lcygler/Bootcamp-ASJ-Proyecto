import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProveedoresService } from 'src/app/services/proveedores/proveedores.service';

@Component({
  selector: 'app-proveedores-proveedores-lista',
  templateUrl: './proveedores-lista.component.html',
  styleUrls: ['./proveedores-lista.component.css'],
})
export class ProveedoresListaComponent implements OnInit {
  proveedores: any[] = [];
  proveedoresMostrados: any[] = [];

  elementosPorPagina = 5;
  totalPaginas: number = 0;
  paginaActual = 1;

  constructor(
    private router: Router,
    private ProveedoresService: ProveedoresService
  ) {}

  ngOnInit() {
    // this.ProveedoresService.getProveedores().subscribe((data: any) => {});
    this.actualizarLista();
  }

  cargarProveedores() {
    this.proveedores = this.ProveedoresService.getProveedores();
  }

  agregarProveedor() {
    this.router.navigate(['/proveedores/alta']);
  }

  editarProveedor(id: string) {
    this.router.navigate([`/proveedores/editar/${id}`]);
  }

  eliminarProveedor(proveedor: any) {
    // this.ProveedoresService.deleteProveedor(id);
    const confirm = window.confirm(
      '¿Seguro que desea eliminar este proveedor?'
    );

    if (confirm) {
      this.ProveedoresService.deleteProveedor(proveedor);
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
    return startIndex + this.elementosPorPagina < this.proveedores.length;
  }

  actualizarLista(): void {
    this.cargarProveedores();

    const startIndex = (this.paginaActual - 1) * this.elementosPorPagina;
    const endIndex = startIndex + this.elementosPorPagina;
    this.proveedoresMostrados = this.proveedores.slice(startIndex, endIndex);

    this.totalPaginas = Math.ceil(
      this.proveedores.length / this.elementosPorPagina
    );
  }
}
