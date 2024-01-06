document.addEventListener("DOMContentLoaded", function () {
  // Inicializar productos
  let productos = JSON.parse(localStorage.getItem("productos"));

  if (!productos) {
    const productos = [
      {
        codigo: "1",
        proveedor: "Proveedor 1",
        categoria: "Electrónicos",
        nombre: "Producto 1",
        descripcion: "Descripción del producto 1",
        precio: 19.99,
      },
      {
        codigo: "2",
        proveedor: "Proveedor 2",
        categoria: "Ropa",
        nombre: "Producto 2",
        descripcion: "Descripción del producto 2",
        precio: 29.99,
      },
      {
        codigo: "3",
        proveedor: "Proveedor 3",
        categoria: "Hogar",
        nombre: "Producto 3",
        descripcion: "Descripción del producto 3",
        precio: 39.99,
      },
      {
        codigo: "4",
        proveedor: "Proveedor 4",
        categoria: "Electrónicos",
        nombre: "Producto 4",
        descripcion: "Descripción del producto 4",
        precio: 49.99,
      },
      {
        codigo: "5",
        proveedor: "Proveedor 5",
        categoria: "Ropa",
        nombre: "Producto 5",
        descripcion: "Descripción del producto 5",
        precio: 59.99,
      },
      {
        codigo: "6",
        proveedor: "Proveedor 6",
        categoria: "Hogar",
        nombre: "Producto 6",
        descripcion: "Descripción del producto 6",
        precio: 69.99,
      },
      {
        codigo: "7",
        proveedor: "Proveedor 7",
        categoria: "Electrónicos",
        nombre: "Producto 7",
        descripcion: "Descripción del producto 7",
        precio: 79.99,
      },
      {
        codigo: "8",
        proveedor: "Proveedor 8",
        categoria: "Ropa",
        nombre: "Producto 8",
        descripcion: "Descripción del producto 8",
        precio: 89.99,
      },
      {
        codigo: "9",
        proveedor: "Proveedor 9",
        categoria: "Hogar",
        nombre: "Producto 9",
        descripcion: "Descripción del producto 9",
        precio: 99.99,
      },
      {
        codigo: "10",
        proveedor: "Proveedor 10",
        categoria: "Electrónicos",
        nombre: "Producto 10",
        descripcion: "Descripción del producto 10",
        precio: 109.99,
      },
    ];

    localStorage.setItem("productos", JSON.stringify(productos));
  }
});
