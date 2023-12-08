document.addEventListener("DOMContentLoaded", function () {
  const altaFormulario = document.getElementById("altaProveedorForm");

  const propiedades = [
    "razonSocial",
    "rubro",
    "sitioWeb",
    "email",
    "telefono",
    "calle",
    "numero",
    "codigoPostal",
    "localidad",
    "provincia",
    "pais",
    "cuit",
    "condicionIva",
    "nombreContacto",
    "apellidoContacto",
    "telefonoContacto",
    "emailContacto",
    "rolContacto",
  ];

  // Rellenar campos
  for (const propiedad of propiedades) {
    const div = document.createElement("div");
    div.classList.add("mb-3");

    const label = document.createElement("label");
    label.setAttribute("for", propiedad.toLowerCase());
    label.classList.add("form-label");
    label.textContent = propiedad;

    const input = document.createElement("input");
    input.setAttribute("type", "text");
    input.classList.add("form-control");
    input.setAttribute("id", propiedad.toLowerCase());

    // Agregar elementos al formulario
    div.appendChild(label);
    div.appendChild(input);
    altaFormulario.appendChild(div);
  }
});

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

  // Crear proveedor
  const nuevoProveedor = {};
  const propiedades = [
    "razonSocial",
    "rubro",
    "sitioWeb",
    "email",
    "telefono",
    "calle",
    "numero",
    "codigoPostal",
    "localidad",
    "provincia",
    "pais",
    "cuit",
    "condicionIva",
    "nombreContacto",
    "apellidoContacto",
    "telefonoContacto",
    "emailContacto",
    "rolContacto",
  ];

  for (const propiedad of propiedades) {
    const input = document.getElementById(propiedad.toLowerCase());
    if (input) {
      nuevoProveedor[propiedad] = input.value;
    }
  }

  // Asignar código de proveedor
  const ultimoProveedor = proveedores[proveedores.length - 1];
  const nuevoCodigo = ultimoProveedor ? parseInt(ultimoProveedor.codigo) + 1 : 1;
  nuevoProveedor.codigo = nuevoCodigo;

  // Agregar nuevo proveedor
  proveedores.push(nuevoProveedor);
  localStorage.setItem("proveedores", JSON.stringify(proveedores));

  alert("Proveedor dado de alta correctamente.");
  window.location.href = "index.html";
}
