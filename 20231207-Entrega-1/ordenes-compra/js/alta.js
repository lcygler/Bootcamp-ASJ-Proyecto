// Cancelar alta
function cancelarAlta() {
  const confirmed = window.confirm("¿Seguro que quieres cancelar el alta?");
  if (confirmed) {
    window.location.href = "index.html";
  }
}

// Guardar alta
function guardarAlta() {
  // Obtener ordenesCompra
  let ordenesCompra = JSON.parse(localStorage.getItem("ordenesCompra")) || [];

  // Seleccionar formulario
  const formulario = document.getElementById("altaOrdenCompraForm");

  // Crear ordenCompra
  const nuevoOrdenCompra = {};

  // Asignar código de ordenCompra
  let nuevoNumero = 1;
  if (ordenesCompra.length > 0) {
    nuevoNumero = parseInt(ordenesCompra[ordenesCompra.length - 1].numero) + 1;
  }
  nuevoOrdenCompra.numero = nuevoNumero;

  // Recorrer campos del formulario
  for (const element of formulario.elements) {
    console.log(element.id, element.value);
    if (element.tagName === "INPUT") {
      nuevoOrdenCompra[element.id] = element.value;
    }
  }

  // Agregar nuevo ordenCompra
  ordenesCompra.push(nuevoOrdenCompra);
  localStorage.setItem("ordenesCompra", JSON.stringify(ordenesCompra));

  alert("Orden de compra generada correctamente.");
  window.location.href = "index.html";
}
