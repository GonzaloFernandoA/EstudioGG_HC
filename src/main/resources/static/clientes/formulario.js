/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


let contador = 1; // Para numerar las filas

const botonDeGuardar = document.getElementById("submitButton");
console.log(botonDeGuardar);

function agregarRegistro() {
    // Obtener el valor del input
    let texto = document.getElementById("inputTexto").value.trim();

    // Verificar que el input no esté vacío
    if (texto === "") {
        alert("Por favor, ingresa una compañia.");
        return;
    }

    // Obtener la tabla y crear una nueva fila
    let tabla = document.getElementById("tablaDatos").getElementsByTagName('tbody')[0];
    let nuevaFila = document.createElement("tr");

    // Crear celdas
    let celdaNumero = document.createElement("td");
    let celdaTexto = document.createElement("td");
    let celdaBoton = document.createElement("td");

    // Asignar valores
    celdaNumero.innerText = contador++; // Número autoincremental
    celdaTexto.innerText = texto; // Texto ingresado

    // Crear botón de eliminar
    let botonEliminar = document.createElement("button");
    botonEliminar.innerText = "Eliminar";
    botonEliminar.className = "btnEliminar";
    botonEliminar.onclick = function () {
        eliminarRegistro(nuevaFila);
    };

    // Agregar botón a la celda de acción
    celdaBoton.appendChild(botonEliminar);

    nuevaFila.appendChild(celdaNumero);
    nuevaFila.appendChild(celdaTexto);
    nuevaFila.appendChild(celdaBoton);

    // Agregar fila a la tabla
    tabla.appendChild(nuevaFila);

    // Limpiar el input
    document.getElementById("inputTexto").value = "";
}

function agregarCompaniasCliente(companias) {
    let tabla = document.getElementById("tablaDatos").getElementsByTagName('tbody')[0];

    companias.forEach(compania => {
        let nuevaFila = document.createElement("tr");

        let celdaNumero = document.createElement("td");
        let celdaTexto = document.createElement("td");
        let celdaBoton = document.createElement("td");

        celdaNumero.innerText = contador++; // Número autoincremental
        celdaTexto.innerText = compania; // Nombre de la compañía

        // No se agrega botón de eliminar a las compañías

        nuevaFila.appendChild(celdaNumero);
        nuevaFila.appendChild(celdaTexto);
        nuevaFila.appendChild(celdaBoton);
        tabla.appendChild(nuevaFila);
    });
}


async function actualizarCliente() {
    const dni = document.getElementById("dni").value;
    const apellido = document.getElementById("apellido").value;
    const nombre = document.getElementById("nombre").value;
    const centro = document.getElementById("centro").value;
    if (!dni) {
        alert("Debe buscar un cliente antes de actualizar.");
        return;
    }

    const cliente = {
        dni: dni,
        apellido: apellido,
        nombre: nombre,
        centro: centro,
        telefono,
    };

    try {
        const response = await fetch('/clientes/actualizar', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(cliente)
        });

        if (response.ok) {
            alert("Cliente actualizado correctamente.");
        } else {
            alert("Error al actualizar el cliente.");
        }
    } catch (error) {
        console.error("Error al realizar la solicitud PUT:", error);
        alert("Error al actualizar el cliente.");
    }
}


function limpiarFormulario() {
    window.location.reload();
}

async function verificarDni() {
    const dni = document.getElementById("dni").value;
    const loadingIndicator = document.getElementById("loadingIndicator");
    if (!dni) {
        alert("Por favor, ingrese un DNI válido.");
        return;
    }

    try {
        loadingIndicator.style.display = "inline";
        // Consultar al backend para obtener el cliente
        const clienteResponse = await fetch(`/clientes/buscar?dni=${dni}`);
        if (clienteResponse.ok) {
            const cliente = await clienteResponse.json();
            document.getElementById("nombre").value = cliente.nombre || "";
            document.getElementById("apellido").value = cliente.apellido || "";
            document.getElementById("centro").value = cliente.centro || "";
            document.getElementById("companias").value = cliente.companias || "";
            document.getElementById("telefono").value = cliente.telefono || "";

            document.getElementById("nombre").disabled = false;
            document.getElementById("apellido").disabled = false;
            document.getElementById("centro").disabled = false;
            document.getElementById("companias").disabled = false;
            document.getElementById("dni").disabled = true;
            document.getElementById("telefono").disabled = false;

            const submitButton = document.getElementById("submitButton");
            submitButton.textContent = "Actualizar";

        } else {
            alert("Cliente no encontrado.");
            return;
        }

        // Consultar al backend para obtener las historias clínicas
        const historyResponse = await fetch(`/history/${dni}`);
        const processes = await historyResponse.json();

        console.info(processes);

        // Mostrar procesos
        // Obtener el cuerpo de la tabla
        const processTableBody = document.querySelector("#processTable tbody");

        // Limpiar la tabla por si hay datos previos
        processTableBody.innerHTML = "";

        // Procesar cada proceso y sus elementos
        processes.forEach((process, index) => {
            const row = document.createElement("tr");



            const lesionesContent = process.lesiones && process.lesiones.length > 0
                    ? process.lesiones.join("<br>") // Une los elementos con <br> entre ellos
                    : "Sin lesiones"; // Texto predeterminado si no hay lesiones

            const lesionesContentD = process.lesionesD && process.lesionesD.length > 0
                    ? process.lesionesD.join("<br>") // Une los elementos con <br> entre ellos
                    : "Sin lesiones"; // Texto predeterminado si no hay lesiones

            const lesionesContentE = process.lesionesE && process.lesionesE.length > 0
                    ? process.lesionesE.join("<br>") // Une los elementos con <br> entre ellos
                    : "Sin lesiones"; // Texto predeterminado si no hay lesiones

            const lesionesContentP = process.lesionesP && process.lesionesP.length > 0
                    ? process.lesionesP.join("<br>") // Une los elementos con <br> entre ellos
                    : "Sin lesiones"; // Texto predeterminado si no hay lesiones


            row.innerHTML = `<td>${process.historia ? process.historia.fecha : "" }</td>
                        <td>${process.historia && process.historia.fechaCarga ? lesionesContent : "" }</td>
                        <td>${process.demanda && process.demanda.fechaCarga ? lesionesContentD : "" }</td>
                        <td>${process.estudios && process.estudios.fechaCarga ? lesionesContentE : ""}</td>
                        <td>${process.pericia && process.pericia.fechaCarga ? lesionesContentP : ""}</td>  `;

            processTableBody.appendChild(row);
        });
    } catch (error) {
        console.error("Error al consultar el backend:", error);
    } finally {
        // Ocultar el indicador de carga
        loadingIndicator.style.display = "none";
    }
}


