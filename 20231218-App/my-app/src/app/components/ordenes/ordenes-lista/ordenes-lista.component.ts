import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OrdenesService } from 'src/app/services/ordenes/ordenes.service';

@Component({
  selector: 'app-ordenes-lista',
  templateUrl: './ordenes-lista.component.html',
  styleUrls: ['./ordenes-lista.component.css'],
})
export class OrdenesListaComponent implements OnInit {
  ordenes: any[] = [];
  ordenesMostradas: any[] = [];

  elementosPorPagina = 5;
  totalPaginas: number = 0;
  paginaActual = 1;

  constructor(private router: Router, private OrdenesService: OrdenesService) {}

  ngOnInit() {
    this.actualizarLista();
  }

  cargarOrdenes() {
    this.ordenes = this.OrdenesService.getOrdenes();
  }

  agregarOrden() {
    this.router.navigate(['/ordenes/alta']);
  }

  editarOrden(id: string) {
    this.router.navigate([`/ordenes/editar/${id}`]);
  }

  eliminarOrden(orden: any) {
    const confirm = window.confirm('¿Seguro que desea eliminar este orden?');

    if (confirm) {
      this.OrdenesService.deleteOrden(orden);
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
    return startIndex + this.elementosPorPagina < this.ordenes.length;
  }

  actualizarLista(): void {
    this.cargarOrdenes();

    const startIndex = (this.paginaActual - 1) * this.elementosPorPagina;
    const endIndex = startIndex + this.elementosPorPagina;
    this.ordenesMostradas = this.ordenes.slice(startIndex, endIndex);

    this.totalPaginas = Math.ceil(
      this.ordenes.length / this.elementosPorPagina
    );
  }
}
