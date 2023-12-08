document.addEventListener("DOMContentLoaded", function () {
  // Obtener código del producto de la URL
  const params = new URLSearchParams(window.location.search);
  const codigoProducto = params.get("codigo");

  // Obtener productos
  let productos = JSON.parse(localStorage.getItem("productos")) || [];

  // Buscar producto
  const producto = productos.find((p) => p.codigo === codigoProducto);

  // Rellenar formulario
  if (producto) {
    // Rellenar código (readonly)
    document.getElementById("codigo").value = producto.codigo;

    // Rellenar campos del formulario
    const propiedades = Object.keys(producto);
    for (const propiedad of propiedades) {
      if (propiedad !== "codigo") {
        const input = document.getElementById(propiedad.toLowerCase());
        if (input) {
          input.value = producto[propiedad];
        }
      }
    }
  }
});

// Cancelar edición
function cancelarEdicion() {
  const confirmed = window.confirm("¿Seguro que quieres cancelar los cambios?");
  if (confirmed) {
    window.location.href = "index.html";
  }
}

// Guardar cambios
function guardarCambios() {
  // Obtener código del producto de la URL
  const params = new URLSearchParams(window.location.search);
  const codigoProducto = params.get("codigo");

  // Obtener productos
  let productos = JSON.parse(localStorage.getItem("productos")) || [];

  // Buscar producto
  const indice = productos.findIndex((p) => p.codigo === codigoProducto);

  // Actualizar valores
  const propiedades = Object.keys(productos[indice]);
  for (const propiedad of propiedades) {
    if (propiedad !== "codigo") {
      const input = document.getElementById(propiedad.toLowerCase());
      if (input) {
        productos[indice][propiedad] = input.value;
      }
    }
  }

  localStorage.setItem("productos", JSON.stringify(productos));
  alert("El producto se modificó correctamente.");
  window.location.href = "index.html";
}
