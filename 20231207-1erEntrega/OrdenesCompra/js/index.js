document.addEventListener("DOMContentLoaded", function () {
  // Obtener ordenesCompra
  let ordenesCompra = JSON.parse(localStorage.getItem("ordenesCompra")) || [];

  // Seleccionar contenedor
  const tableBody = document.querySelector("tbody");

  // Botón agregar ordenCompra
  const agregarBtn = document.getElementById("agregarBtn");
  agregarBtn.addEventListener("click", () => (window.location.href = "alta.html"));

  // Número de ordenesCompra por página
  const ordenesCompraPorPagina = 5;

  // Botones de paginación
  const primeraBtn = document.getElementById("primeraBtn");
  const ultimaBtn = document.getElementById("ultimaBtn");
  const anteriorBtn = document.getElementById("anteriorBtn");
  const siguienteBtn = document.getElementById("siguienteBtn");
  const pagina1 = document.getElementById("pagina1");
  const pagina2 = document.getElementById("pagina2");
  const pagina3 = document.getElementById("pagina3");

  // Variables páginas
  let paginaActual = 1;
  let paginasEnTotal;

  // Renderizar ordenesCompra
  renderizarOrdenesCompra(paginaActual);

  function renderizarOrdenesCompra(pagina) {
    // Limpiar contenido tabla
    tableBody.innerHTML = "";

    // Calcular índices paginado
    const inicio = (pagina - 1) * ordenesCompraPorPagina;
    const fin = inicio + ordenesCompraPorPagina;

    // Obtener ordenesCompra
    const ordenesCompraPagina = ordenesCompra.slice(inicio, fin);

    // Iterar ordenesCompra
    ordenesCompraPagina.forEach((ordenCompra) => {
      // Crear fila
      const row = document.createElement("tr");

      // Iterar propiedades
      for (const prop in ordenCompra) {
        // Crear celda
        const cell = document.createElement("td");
        cell.textContent = ordenCompra[prop];

        // Agregar celda a la fila
        row.appendChild(cell);
      }

      // Crear celda para acciones
      const actionsCell = document.createElement("td");

      const editButton = document.createElement("button");
      editButton.classList.add("btn", "btn-primary", "mx-2");
      editButton.textContent = "Editar";
      editButton.addEventListener("click", () => editarOrdenCompra(ordenCompra));

      const deleteButton = document.createElement("button");
      deleteButton.classList.add("btn", "btn-danger");
      deleteButton.textContent = "Eliminar";
      deleteButton.addEventListener("click", () => eliminarOrdenCompra(ordenCompra));

      // Agregar botones a la celda
      actionsCell.appendChild(editButton);
      actionsCell.appendChild(deleteButton);

      // Agregar la celda a la fila
      row.appendChild(actionsCell);

      // Agregar fila a la tabla
      tableBody.appendChild(row);
    });

    // Actualizar estado de los botones de paginación
    actualizarPaginacion(pagina);
  }

  // Actualizar paginación
  function actualizarPaginacion(pagina) {
    const totalPaginas = Math.ceil(ordenesCompra.length / ordenesCompraPorPagina);
    const rango = 3;

    // Desactivar/activar botones
    primeraBtn.classList.toggle("disabled", pagina === 1);
    ultimaBtn.classList.toggle("disabled", pagina === totalPaginas);
    anteriorBtn.classList.toggle("disabled", pagina === 1);
    siguienteBtn.classList.toggle("disabled", pagina === totalPaginas);

    // Calcular rango
    let inicioRango = Math.max(1, totalPaginas - rango + 1);
    let finRango = totalPaginas;

    // Ajustar rango
    if (pagina <= totalPaginas - rango) {
      inicioRango = Math.max(1, pagina);
      finRango = inicioRango + rango - 1;
    }

    // Iterar paginación
    for (let i = 1; i <= rango; i++) {
      const paginaElement = document.getElementById(`pagina${i}`);
      const paginaNumero = inicioRango + i - 1;

      // Limpiar clases de Bootstrap
      paginaElement.classList.remove("active", "page-item", "disabled");

      // Agregar clases de Bootstrap
      paginaElement.classList.add("page-item");
      if (pagina === paginaNumero) {
        paginaElement.classList.add("active");
      }

      // Actualizar número de página
      const linkElement = paginaElement.querySelector(".page-link");
      linkElement.textContent = paginaNumero;
    }

    // Actualizar páginas
    paginaActual = pagina;
    paginasEnTotal = totalPaginas;
  }

  // Cambiar página
  function cambiarPagina(pagina) {
    const totalPaginas = Math.ceil(ordenesCompra.length / ordenesCompraPorPagina);
    pagina = Math.max(1, Math.min(pagina, totalPaginas));
    renderizarOrdenesCompra(pagina);
  }

  // Eventos botones paginación
  primeraBtn.addEventListener("click", () => cambiarPagina(1));
  ultimaBtn.addEventListener("click", () => cambiarPagina(paginasEnTotal));
  anteriorBtn.addEventListener("click", () => cambiarPagina(paginaActual - 1));
  siguienteBtn.addEventListener("click", () => cambiarPagina(paginaActual + 1));
  pagina1.addEventListener("click", () => cambiarPagina(1));
  pagina2.addEventListener("click", () => cambiarPagina(2));
  pagina3.addEventListener("click", () => cambiarPagina(3));

  // Editar ordenCompra
  function editarOrdenCompra(ordenCompra) {
    window.location.href = `editar.html?numero=${ordenCompra.numero}`;
  }

  // Eliminar ordenCompra
  function eliminarOrdenCompra(ordenCompra) {
    const confirmed = window.confirm("¿Seguro que quieres eliminar este ordenCompra?");

    if (confirmed) {
      const index = ordenesCompra.findIndex((p) => p.numero === ordenCompra.numero);
      ordenesCompra.splice(index, 1);
      localStorage.setItem("ordenesCompra", JSON.stringify(ordenesCompra));
      renderizarOrdenesCompra(1);
      alert("El ordenCompra se eliminó correctamente.");
    }
  }
});
