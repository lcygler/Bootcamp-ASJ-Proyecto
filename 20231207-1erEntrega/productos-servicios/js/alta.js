// Cancelar alta
function cancelarAlta() {
  const confirmed = window.confirm("¿Seguro que quieres cancelar el alta?");
  if (confirmed) {
    window.location.href = "index.html";
  }
}

// Guardar alta
function guardarAlta() {
  // Obtener productos
  let productos = JSON.parse(localStorage.getItem("productos")) || [];

  // Seleccionar formulario
  const formulario = document.getElementById("altaProductoForm");

  // Crear producto
  const nuevoProducto = {};

  // Asignar código de producto
  let nuevoCodigo = 1;
  if (productos.length > 0) {
    nuevoCodigo = parseInt(productos[productos.length - 1].codigo) + 1;
  }
  nuevoProducto.codigo = nuevoCodigo;

  // Recorrer campos del formulario
  for (const element of formulario.elements) {
    console.log(element.id, element.value);
    if (element.tagName === "INPUT") {
      nuevoProducto[element.id] = element.value;
    }
  }

  // Agregar nuevo producto
  productos.push(nuevoProducto);
  localStorage.setItem("productos", JSON.stringify(productos));

  alert("Producto dado de alta correctamente.");
  window.location.href = "index.html";
}
