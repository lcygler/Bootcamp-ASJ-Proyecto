document.addEventListener("DOMContentLoaded", function () {
  // Obtener proveedores
  let proveedores = JSON.parse(localStorage.getItem("proveedores")) || [];

  // Seleccionar contenedor
  const tableBody = document.querySelector("tbody");

  // Botón agregar proveedor
  const agregarBtn = document.getElementById("agregarBtn");
  agregarBtn.addEventListener("click", () => (window.location.href = "alta.html"));

  // Número de proveedores por página
  const proveedoresPorPagina = 5;

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

  // Renderizar proveedores
  renderizarProveedores(paginaActual);

  function renderizarProveedores(pagina) {
    // Limpiar contenido tabla
    tableBody.innerHTML = "";

    // Calcular índices paginado
    const inicio = (pagina - 1) * proveedoresPorPagina;
    const fin = inicio + proveedoresPorPagina;

    // Obtener proveedores
    const proveedoresPagina = proveedores.slice(inicio, fin);

    // Iterar proveedores
    proveedoresPagina.forEach((proveedor) => {
      // Crear fila
      const row = document.createElement("tr");

      // Iterar propiedades
      for (const prop in proveedor) {
        // Crear celda
        const cell = document.createElement("td");
        cell.textContent = proveedor[prop];

        // Agregar celda a la fila
        row.appendChild(cell);
      }

      // Crear celda para acciones
      const actionsCell = document.createElement("td");
      actionsCell.classList.add("d-flex");

      const editButton = document.createElement("button");
      editButton.classList.add("btn", "btn-primary", "mx-2");
      editButton.textContent = "Editar";
      editButton.addEventListener("click", () => editarProveedor(proveedor));

      const deleteButton = document.createElement("button");
      deleteButton.classList.add("btn", "btn-danger");
      deleteButton.textContent = "Eliminar";
      deleteButton.addEventListener("click", () => eliminarProveedor(proveedor));

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
    const totalPaginas = Math.ceil(proveedores.length / proveedoresPorPagina);
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
    const totalPaginas = Math.ceil(proveedores.length / proveedoresPorPagina);
    pagina = Math.max(1, Math.min(pagina, totalPaginas));
    renderizarProveedores(pagina);
  }

  // Eventos botones paginación
  primeraBtn.addEventListener("click", () => cambiarPagina(1));
  ultimaBtn.addEventListener("click", () => cambiarPagina(paginasEnTotal));
  anteriorBtn.addEventListener("click", () => cambiarPagina(paginaActual - 1));
  siguienteBtn.addEventListener("click", () => cambiarPagina(paginaActual + 1));
  pagina1.addEventListener("click", () => cambiarPagina(1));
  pagina2.addEventListener("click", () => cambiarPagina(2));
  pagina3.addEventListener("click", () => cambiarPagina(3));

  // Editar proveedor
  function editarProveedor(proveedor) {
    window.location.href = `editar.html?codigo=${proveedor.codigo}`;
  }

  // Eliminar proveedor
  function eliminarProveedor(proveedor) {
    const confirmed = window.confirm("¿Seguro que quieres eliminar este proveedor?");

    if (confirmed) {
      const index = proveedores.findIndex((p) => p.codigo === proveedor.codigo);
      proveedores.splice(index, 1);
      localStorage.setItem("proveedores", JSON.stringify(proveedores));
      renderizarProveedores(1);
      alert("El proveedor se eliminó correctamente.");
    }
  }
});
