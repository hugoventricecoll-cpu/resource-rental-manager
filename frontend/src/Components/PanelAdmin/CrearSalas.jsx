export default function CrearSalas() {

    async function crearSala(s) {
        s.preventDefault()

        const res = await fetch("http://localhost:8091/api/sala", {
            headers: {"Content-Type": "application/json", Authorization: `Bearer ${localStorage.getItem("token")}`},
            method: "POST",
            body: JSON.stringify({nombre: s.target.elements.nombre.value, aforo: s.target.elements.aforo.value, ubicacion: s.target.elements.ubicacion.value})
        });

        (!res.ok) ? console.log("Err creating sala") : console.log("Sala was succesfully created")

        return
    }

    return (
        <>
            <form onSubmit={crearSala}>
                <div>
                    <p> Nombre </p>
                    <input name="nombre" />
                    <p> Aforo </p>
                    <input name="aforo" />
                    <p> Ubicación </p>
                    <input name="ubicacion" />
                </div>
                <button> Crear </button>
            </form>
        </>
    )
}