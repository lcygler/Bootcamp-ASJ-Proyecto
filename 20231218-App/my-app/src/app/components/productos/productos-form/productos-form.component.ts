import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductosService } from 'src/app/services/productos/productos.service';

@Component({
  selector: 'app-productos-productos-form',
  templateUrl: './productos-form.component.html',
  styleUrls: ['./productos-form.component.css'],
})
export class ProductosFormComponent {
  producto: any = {};

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private ProductosService: ProductosService
  ) {}

  ngOnInit(): void {
    const id = this.obtenerId();

    if (id) {
      this.producto = this.ProductosService.getProductoById(id);
    }
  }

  obtenerId(): string | null {
    return this.route.snapshot.paramMap.get('idProducto');
  }

  guardarProducto() {
    if (this.producto) {
      const ruta = this.router.url;

      if (ruta.includes('/productos/alta')) {
        // Agregar nuevo producto
        this.ProductosService.postProducto(this.producto);
        alert('Se agregó el producto con éxito.');
      } else if (ruta.includes('/productos/editar')) {
        // Actualizar producto existente
        this.ProductosService.putProducto(this.producto);
        alert('Los cambios se guardaron con éxito.');
      }

      this.router.navigate(['/productos']);
    }
  }
}
