import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProveedoresService } from 'src/app/services/proveedores/proveedores.service';

@Component({
  selector: 'app-proveedores-proveedores-form',
  templateUrl: './proveedores-form.component.html',
  styleUrls: ['./proveedores-form.component.css'],
})
export class ProveedoresFormComponent {
  proveedor: any = {};

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private ProveedoresService: ProveedoresService
  ) {}

  ngOnInit(): void {
    const id = this.obtenerId();

    if (id) {
      this.proveedor = this.ProveedoresService.getProveedorById(id);
    }
  }

  obtenerId(): string | null {
    return this.route.snapshot.paramMap.get('idProveedor');
  }

  guardarProveedor() {
    if (this.proveedor) {
      const ruta = this.router.url;

      if (ruta.includes('/proveedores/alta')) {
        // Agregar nuevo proveedor
        this.ProveedoresService.postProveedor(this.proveedor);
        alert('Se agregó el proveedor con éxito.');
      } else if (ruta.includes('/proveedores/editar')) {
        // Actualizar proveedor existente
        this.ProveedoresService.putProveedor(this.proveedor);
        alert('Los cambios se guardaron con éxito.');
      }

      this.router.navigate(['/proveedores']);
    }
  }
}
