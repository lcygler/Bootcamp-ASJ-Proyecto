document.addEventListener("DOMContentLoaded", function () {
  // Obtener código del proveedor de la URL
  const params = new URLSearchParams(window.location.search);
  const codigoProveedor = params.get("codigo");

  // Obtener proveedores
  let proveedores = JSON.parse(localStorage.getItem("proveedores")) || [];

  // Buscar proveedor
  const proveedor = proveedores.find((p) => p.codigo === codigoProveedor);

  // Rellenar formulario
  if (proveedor) {
    // Rellenar código (readonly)
    document.getElementById("codigo").value = proveedor.codigo;

    // Rellenar campos del formulario
    const propiedades = Object.keys(proveedor);
    for (const propiedad of propiedades) {
      if (propiedad !== "codigo") {
        const input = document.getElementById(propiedad.toLowerCase());
        if (input) {
          input.value = proveedor[propiedad];
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
  // Obtener código del proveedor de la URL
  const params = new URLSearchParams(window.location.search);
  const codigoProveedor = params.get("codigo");

  // Obtener proveedores
  let proveedores = JSON.parse(localStorage.getItem("proveedores")) || [];

  // Buscar proveedor
  const indice = proveedores.findIndex((p) => p.codigo === codigoProveedor);

  // Actualizar valores
  const propiedades = Object.keys(proveedores[indice]);
  for (const propiedad of propiedades) {
    if (propiedad !== "codigo") {
      const input = document.getElementById(propiedad.toLowerCase());
      if (input) {
        proveedores[indice][propiedad] = input.value;
      }
    }
  }

  localStorage.setItem("proveedores", JSON.stringify(proveedores));
  alert("El proveedor se modificó correctamente.");
  window.location.href = "index.html";
}
