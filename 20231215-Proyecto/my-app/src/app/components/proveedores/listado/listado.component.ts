import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-proveedores-listado',
  templateUrl: './listado.component.html',
  styleUrls: ['./listado.component.css'],
})
export class ListadoComponent implements OnInit {
  proveedores: any[] = [];
  proveedoresVisibles: any[] = [];
  nuevoProveedor: any = {};

  elementosPorPagina = 5;
  totalPaginas: number = 0;
  paginaActual = 1;

  dummyData = [
    {
      codigo: '1',
      razonSocial: 'Dummy Corp 1',
      rubro: 'Technology',
      sitioWeb: 'www.dummy1.com',
      email: 'dummy1@email.com',
      telefono: '123-456-7890',
      calle: 'Main St',
      numero: '123',
      codigoPostal: '12345',
      localidad: 'Dummyville',
      provincia: 'Dummy State',
      pais: 'Dummy Country',
      cuit: '12345678901',
      condicionIva: 'Responsable Inscripto',
      nombreContacto: 'John',
      apellidoContacto: 'Doe',
      telefonoContacto: '987-654-3210',
      emailContacto: 'john.doe@email.com',
      rolContacto: 'Manager',
    },
    {
      codigo: '2',
      razonSocial: 'Dummy Corp 2',
      rubro: 'Technology',
      sitioWeb: 'www.dummy2.com',
      email: 'dummy2@email.com',
      telefono: '234-567-8901',
      calle: 'Market St',
      numero: '456',
      codigoPostal: '23456',
      localidad: 'Dummytown',
      provincia: 'Dummy State',
      pais: 'Dummy Country',
      cuit: '23456789012',
      condicionIva: 'Responsable Inscripto',
      nombreContacto: 'Jane',
      apellidoContacto: 'Smith',
      telefonoContacto: '876-543-2109',
      emailContacto: 'jane.smith@email.com',
      rolContacto: 'Coordinator',
    },
    {
      codigo: '3',
      razonSocial: 'Dummy Corp 3',
      rubro: 'Electronics',
      sitioWeb: 'www.dummy3.com',
      email: 'dummy3@email.com',
      telefono: '345-678-9012',
      calle: 'High St',
      numero: '789',
      codigoPostal: '34567',
      localidad: 'Dummy City',
      provincia: 'Dummy State',
      pais: 'Dummy Country',
      cuit: '34567890123',
      condicionIva: 'Responsable Inscripto',
      nombreContacto: 'Bob',
      apellidoContacto: 'Johnson',
      telefonoContacto: '765-432-1098',
      emailContacto: 'bob.johnson@email.com',
      rolContacto: 'Engineer',
    },
    {
      codigo: '4',
      razonSocial: 'Dummy Corp 4',
      rubro: 'Clothing',
      sitioWeb: 'www.dummy4.com',
      email: 'dummy4@email.com',
      telefono: '456-789-0123',
      calle: 'Oak St',
      numero: '1011',
      codigoPostal: '45678',
      localidad: 'Dummyville',
      provincia: 'Dummy State',
      pais: 'Dummy Country',
      cuit: '45678901234',
      condicionIva: 'Responsable Inscripto',
      nombreContacto: 'Alice',
      apellidoContacto: 'Williams',
      telefonoContacto: '654-321-0987',
      emailContacto: 'alice.williams@email.com',
      rolContacto: 'Designer',
    },
    {
      codigo: '5',
      razonSocial: 'Dummy Corp 5',
      rubro: 'Furniture',
      sitioWeb: 'www.dummy5.com',
      email: 'dummy5@email.com',
      telefono: '567-890-1234',
      calle: 'Maple St',
      numero: '1213',
      codigoPostal: '56789',
      localidad: 'Dummytown',
      provincia: 'Dummy State',
      pais: 'Dummy Country',
      cuit: '56789012345',
      condicionIva: 'Responsable Inscripto',
      nombreContacto: 'Charlie',
      apellidoContacto: 'Brown',
      telefonoContacto: '543-210-9876',
      emailContacto: 'charlie.brown@email.com',
      rolContacto: 'Sales Representative',
    },
    {
      codigo: '6',
      razonSocial: 'Dummy Corp 5',
      rubro: 'Furniture',
      sitioWeb: 'www.dummy5.com',
      email: 'dummy5@email.com',
      telefono: '567-890-1234',
      calle: 'Maple St',
      numero: '1213',
      codigoPostal: '56789',
      localidad: 'Dummytown',
      provincia: 'Dummy State',
      pais: 'Dummy Country',
      cuit: '56789012345',
      condicionIva: 'Responsable Inscripto',
      nombreContacto: 'Charlie',
      apellidoContacto: 'Brown',
      telefonoContacto: '543-210-9876',
      emailContacto: 'charlie.brown@email.com',
      rolContacto: 'Sales Representative',
    },
    {
      codigo: '7',
      razonSocial: 'Dummy Corp 5',
      rubro: 'Furniture',
      sitioWeb: 'www.dummy5.com',
      email: 'dummy5@email.com',
      telefono: '567-890-1234',
      calle: 'Maple St',
      numero: '1213',
      codigoPostal: '56789',
      localidad: 'Dummytown',
      provincia: 'Dummy State',
      pais: 'Dummy Country',
      cuit: '56789012345',
      condicionIva: 'Responsable Inscripto',
      nombreContacto: 'Charlie',
      apellidoContacto: 'Brown',
      telefonoContacto: '543-210-9876',
      emailContacto: 'charlie.brown@email.com',
      rolContacto: 'Sales Representative',
    },
    {
      codigo: '8',
      razonSocial: 'Dummy Corp 5',
      rubro: 'Furniture',
      sitioWeb: 'www.dummy5.com',
      email: 'dummy5@email.com',
      telefono: '567-890-1234',
      calle: 'Maple St',
      numero: '1213',
      codigoPostal: '56789',
      localidad: 'Dummytown',
      provincia: 'Dummy State',
      pais: 'Dummy Country',
      cuit: '56789012345',
      condicionIva: 'Responsable Inscripto',
      nombreContacto: 'Charlie',
      apellidoContacto: 'Brown',
      telefonoContacto: '543-210-9876',
      emailContacto: 'charlie.brown@email.com',
      rolContacto: 'Sales Representative',
    },
    {
      codigo: '9',
      razonSocial: 'Dummy Corp 5',
      rubro: 'Furniture',
      sitioWeb: 'www.dummy5.com',
      email: 'dummy5@email.com',
      telefono: '567-890-1234',
      calle: 'Maple St',
      numero: '1213',
      codigoPostal: '56789',
      localidad: 'Dummytown',
      provincia: 'Dummy State',
      pais: 'Dummy Country',
      cuit: '56789012345',
      condicionIva: 'Responsable Inscripto',
      nombreContacto: 'Charlie',
      apellidoContacto: 'Brown',
      telefonoContacto: '543-210-9876',
      emailContacto: 'charlie.brown@email.com',
      rolContacto: 'Sales Representative',
    },
    {
      codigo: '10',
      razonSocial: 'Dummy Corp 5',
      rubro: 'Furniture',
      sitioWeb: 'www.dummy5.com',
      email: 'dummy5@email.com',
      telefono: '567-890-1234',
      calle: 'Maple St',
      numero: '1213',
      codigoPostal: '56789',
      localidad: 'Dummytown',
      provincia: 'Dummy State',
      pais: 'Dummy Country',
      cuit: '56789012345',
      condicionIva: 'Responsable Inscripto',
      nombreContacto: 'Charlie',
      apellidoContacto: 'Brown',
      telefonoContacto: '543-210-9876',
      emailContacto: 'charlie.brown@email.com',
      rolContacto: 'Sales Representative',
    },
    {
      codigo: '11',
      razonSocial: 'Dummy Corp 5',
      rubro: 'Furniture',
      sitioWeb: 'www.dummy5.com',
      email: 'dummy5@email.com',
      telefono: '567-890-1234',
      calle: 'Maple St',
      numero: '1213',
      codigoPostal: '56789',
      localidad: 'Dummytown',
      provincia: 'Dummy State',
      pais: 'Dummy Country',
      cuit: '56789012345',
      condicionIva: 'Responsable Inscripto',
      nombreContacto: 'Charlie',
      apellidoContacto: 'Brown',
      telefonoContacto: '543-210-9876',
      emailContacto: 'charlie.brown@email.com',
      rolContacto: 'Sales Representative',
    },
    {
      codigo: '12',
      razonSocial: 'Dummy Corp 5',
      rubro: 'Furniture',
      sitioWeb: 'www.dummy5.com',
      email: 'dummy5@email.com',
      telefono: '567-890-1234',
      calle: 'Maple St',
      numero: '1213',
      codigoPostal: '56789',
      localidad: 'Dummytown',
      provincia: 'Dummy State',
      pais: 'Dummy Country',
      cuit: '56789012345',
      condicionIva: 'Responsable Inscripto',
      nombreContacto: 'Charlie',
      apellidoContacto: 'Brown',
      telefonoContacto: '543-210-9876',
      emailContacto: 'charlie.brown@email.com',
      rolContacto: 'Sales Representative',
    },
    {
      codigo: '13',
      razonSocial: 'Dummy Corp 5',
      rubro: 'Furniture',
      sitioWeb: 'www.dummy5.com',
      email: 'dummy5@email.com',
      telefono: '567-890-1234',
      calle: 'Maple St',
      numero: '1213',
      codigoPostal: '56789',
      localidad: 'Dummytown',
      provincia: 'Dummy State',
      pais: 'Dummy Country',
      cuit: '56789012345',
      condicionIva: 'Responsable Inscripto',
      nombreContacto: 'Charlie',
      apellidoContacto: 'Brown',
      telefonoContacto: '543-210-9876',
      emailContacto: 'charlie.brown@email.com',
      rolContacto: 'Sales Representative',
    },
    {
      codigo: '14',
      razonSocial: 'Dummy Corp 5',
      rubro: 'Furniture',
      sitioWeb: 'www.dummy5.com',
      email: 'dummy5@email.com',
      telefono: '567-890-1234',
      calle: 'Maple St',
      numero: '1213',
      codigoPostal: '56789',
      localidad: 'Dummytown',
      provincia: 'Dummy State',
      pais: 'Dummy Country',
      cuit: '56789012345',
      condicionIva: 'Responsable Inscripto',
      nombreContacto: 'Charlie',
      apellidoContacto: 'Brown',
      telefonoContacto: '543-210-9876',
      emailContacto: 'charlie.brown@email.com',
      rolContacto: 'Sales Representative',
    },
    {
      codigo: '15',
      razonSocial: 'Dummy Corp 5',
      rubro: 'Furniture',
      sitioWeb: 'www.dummy5.com',
      email: 'dummy5@email.com',
      telefono: '567-890-1234',
      calle: 'Maple St',
      numero: '1213',
      codigoPostal: '56789',
      localidad: 'Dummytown',
      provincia: 'Dummy State',
      pais: 'Dummy Country',
      cuit: '56789012345',
      condicionIva: 'Responsable Inscripto',
      nombreContacto: 'Charlie',
      apellidoContacto: 'Brown',
      telefonoContacto: '543-210-9876',
      emailContacto: 'charlie.brown@email.com',
      rolContacto: 'Sales Representative',
    },
  ];

  ngOnInit() {
    for (let data of this.dummyData) {
      this.proveedores.push(data);
    }

    this.actualizarLista();
  }

  agregar() {
    this.proveedores.push({});
    this.nuevoProveedor = {};
  }

  eliminar() {}

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
    const startIndex = (this.paginaActual - 1) * this.elementosPorPagina;
    const endIndex = startIndex + this.elementosPorPagina;
    this.proveedoresVisibles = this.proveedores.slice(startIndex, endIndex);

    this.totalPaginas = Math.ceil(
      this.proveedores.length / this.elementosPorPagina
    );
  }
}
