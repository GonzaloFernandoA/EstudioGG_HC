/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

const verificarDni = async () => {
    const dni = document.getElementById("dni").value;

    try {
        const turnoResponse = await fetch(`/turnos/buscarTurno?dni=${dni}`);
        const clienteResponse = await fetch(`/clientes/buscar?dni=${dni}`);

        if (!clienteResponse.ok) {
            if (confirm("El cliente con este DNI no existe ¿Desea crearlo?")) {
                document.getElementById("botonBuscar").innerHTML = "Nuevo turno y cliente";

                document.getElementById("botonBuscar").disabled = true;
                document.getElementById("apellido").disabled = false;
                document.getElementById("nombre").disabled = false;
                document.getElementById("telefono").disabled = false;
                document.getElementById("dia").disabled = false;
                document.getElementById("hora").disabled = false;
                document.getElementById("clinica").disabled = false;
                document.getElementById("direccion").disabled = false;
                document.getElementById("sector").disabled = false;
                document.getElementById("submitButton").disabled = false;

                document.getElementById("submitButton").addEventListener('click', async () => {
                    // Acá se hace un fetch para crear un nuevo cliente
                })


            }
        } else {
            console.log(clienteResponse);
            if (turnoResponse.ok) {
                alert("El turno con este DNI ya existe");
            } else {
                if (confirm("El turno con este DNI no existe ¿Desea crearlo?")) {
                    const cliente = await clienteResponse.json();

                    document.getElementById("botonBuscar").disabled = true;
                    document.getElementById("botonBuscar").innerHTML = "Nuevo turno";

                    document.getElementById("apellido").disabled = false;
                    document.getElementById("apellido").value = cliente.apellido || "";

                    document.getElementById("nombre").disabled = false;
                    document.getElementById("nombre").value = cliente.nombre || "";

                    document.getElementById("telefono").disabled = false;
                    document.getElementById("telefono").value = cliente.telefono || "";

                    document.getElementById("dia").disabled = false;
                    document.getElementById("hora").disabled = false;
                    document.getElementById("clinica").disabled = false;
                    document.getElementById("direccion").disabled = false;
                    document.getElementById("sector").disabled = false;
                    document.getElementById("submitButton").disabled = false;
                }
            }
        }

    } catch (error) {
        console.log("Error al consultar el backend: ", error);
    }
}

const verificarFechaAlGuardar = () => {
    const fechaDeHoy = new Date();
    fechaDeHoy.setHours(0, 0, 0, 0);
    const fecha = new Date(document.getElementById("dia").value);

    if (document.getElementById("fecha").value && (fecha < fechaDeHoy)) {
        alert("El turno no puede estar en una fecha pasada");
        document.getElementById("submitButton").disabled = true;
    } else {
        document.getElementById("submitButton").disabled = false;
    }
}

const fechaDeTurno = document.getElementById("dia");
fechaDeTurno.addEventListener('blur', verificarFechaAlGuardar);

const boton = document.getElementById("submitButton");
boton.addEventListener('click', (e) => {
    verificarFechaAlGuardar();

    if (boton.disabled) {
        e.preventDefault();
    }
});

const limpiarFormulario = () => {
    window.location.reload();
}
