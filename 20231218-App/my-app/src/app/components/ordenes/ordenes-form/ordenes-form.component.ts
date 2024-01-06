import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrdenesService } from 'src/app/services/ordenes/ordenes.service';

@Component({
  selector: 'app-ordenes-ordenes-form',
  templateUrl: './ordenes-form.component.html',
  styleUrls: ['./ordenes-form.component.css'],
})
export class OrdenesFormComponent {
  orden: any = {};

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private OrdenesService: OrdenesService
  ) {}

  ngOnInit(): void {
    const id = this.obtenerId();

    if (id) {
      this.orden = this.OrdenesService.getOrdenById(id);
    }
  }

  obtenerId(): string | null {
    return this.route.snapshot.paramMap.get('idOrden');
  }

  guardarOrden() {
    if (this.orden) {
      const ruta = this.router.url;

      if (ruta.includes('/ordenes/alta')) {
        // Agregar nuevo orden
        this.OrdenesService.postOrden(this.orden);
        alert('Se agregó la orden con éxito.');
      } else if (ruta.includes('/ordenes/editar')) {
        // Actualizar orden existente
        this.OrdenesService.putOrden(this.orden);
        alert('Los cambios se guardaron con éxito.');
      }

      this.router.navigate(['/ordenes']);
    }
  }
}
