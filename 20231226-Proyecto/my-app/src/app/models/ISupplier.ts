export interface ISupplier {
  id: number;
  razonSocial: string;
  rubro: string;
  sitioWeb: string;
  email: string;
  telefono: string;
  direccion: {
    calle: string;
    numero: string;
    codigoPostal: string;
    localidad: string;
    provincia: string;
    pais: string;
  };
  cuit: string;
  condicionIva: string;
  contacto: {
    nombre: string;
    apellido: string;
    telefono: string;
    email: string;
    rol: string;
  };
}
