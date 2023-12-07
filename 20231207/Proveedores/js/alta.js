// Cancelar alta
function cancelarAlta() {
  const confirmed = window.confirm("¿Seguro que quieres cancelar el alta?");
  if (confirmed) {
    window.location.href = "index.html";
  }
}

// Guardar alta
function guardarAlta() {
  // Obtener proveedores
  let proveedores = JSON.parse(localStorage.getItem("proveedores")) || [];

  // Seleccionar formulario
  const formulario = document.getElementById("altaProveedorForm");

  // Crear proveedor
  const nuevoProveedor = {};

  // Asignar código de proveedor
  let nuevoCodigo = 1;
  if (proveedores.length > 0) {
    nuevoCodigo = parseInt(proveedores[proveedores.length - 1].codigo) + 1;
  }
  nuevoProveedor.codigo = nuevoCodigo;

  // Recorrer campos del formulario
  for (const element of formulario.elements) {
    console.log(element.id, element.value);
    if (element.tagName === "INPUT") {
      nuevoProveedor[element.id] = element.value;
    }
  }

  // Agregar nuevo proveedor
  proveedores.push(nuevoProveedor);
  localStorage.setItem("proveedores", JSON.stringify(proveedores));

  alert("Proveedor dado de alta correctamente.");
  window.location.href = "index.html";
}
