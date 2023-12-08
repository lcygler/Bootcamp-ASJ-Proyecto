document.addEventListener("DOMContentLoaded", function () {
  // Obtener código del ordenCompra de la URL
  const params = new URLSearchParams(window.location.search);
  const numeroOrdenCompra = params.get("numero");

  // Obtener ordenesCompra
  let ordenesCompra = JSON.parse(localStorage.getItem("ordenesCompra")) || [];

  // Buscar ordenCompra
  const ordenCompra = ordenesCompra.find((o) => o.numero === numeroOrdenCompra);

  // Rellenar formulario
  if (ordenCompra) {
    // Rellenar código (readonly)
    document.getElementById("numero").value = ordenCompra.numero;

    // Rellenar campos del formulario
    const propiedades = Object.keys(ordenCompra);
    for (const propiedad of propiedades) {
      if (propiedad !== "numero") {
        const input = document.getElementById(propiedad.toLowerCase());
        if (input) {
          input.value = ordenCompra[propiedad];
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
  // Obtener código del ordenCompra de la URL
  const params = new URLSearchParams(window.location.search);
  const numeroOrdenCompra = params.get("numero");

  // Obtener ordenesCompra
  let ordenesCompra = JSON.parse(localStorage.getItem("ordenesCompra")) || [];

  // Buscar ordenCompra
  const indice = ordenesCompra.findIndex((o) => o.numero === numeroOrdenCompra);

  // Actualizar valores
  const propiedades = Object.keys(ordenesCompra[indice]);
  for (const propiedad of propiedades) {
    if (propiedad !== "numero") {
      const input = document.getElementById(propiedad.toLowerCase());
      if (input) {
        ordenesCompra[indice][propiedad] = input.value;
      }
    }
  }

  localStorage.setItem("ordenesCompra", JSON.stringify(ordenesCompra));
  alert("La orden de compra se modificó correctamente.");
  window.location.href = "index.html";
}
