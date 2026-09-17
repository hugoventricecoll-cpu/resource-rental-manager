export default function CrearVehiculos() {

    async function crearCoche(c){
        c.preventDefault()

        const res = await fetch("http://localhost:8091/api/vehiculos", {
            headers: {"Content-Type": "application/json", Authorization: `Bearer ${localStorage.getItem("token")}`},
            method: "POST",
            body: JSON.stringify({nombre: c.target.elements.nombre.value, kilometraje: parseInt(c.target.elements.kilometraje.value), matricula: c.target.elements.matricula.value, plazas: parseInt(c.target.elements.plazas.value)})
        });

        (!res.ok) ? console.log("Err creating car") : console.log("Car was succesfully created")

        return
    }

    return (
        <>
            <form onSubmit={crearCoche}>
                <div>
                    <p> Nombre </p>
                    <input name="nombre"/>
                    <p> Kilometraje </p>
                    <input name="kilometraje"/>
                </div>
                <div>
                    <p> Matricula </p>
                    <input name="matricula"/>
                    <p> Plazas </p>
                    <input type="number"name="plazas"/>
                </div>
                <button> Crear </button>
            </form>
        </>
    )
}