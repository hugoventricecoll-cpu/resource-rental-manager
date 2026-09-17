export default function CrearPersonal() {

	async function crearPersonal(p) {
        p.preventDefault()

		const res = await fetch("http://localhost:8091/api/personal", {
			headers: {"Content-Type": "application/json", Authorization: `Bearer ${localStorage.getItem("token")}`},
			method: "POST",
			body: JSON.stringify({ nombre: p.target.elements.nombre.value, tipo: p.target.elements.tipo.value })
		});

		(!res.ok) ? console.log("Err creating personal") : console.log("Personal was succesfully created")

		return
	}

	return (
		<>
			<form onSubmit={crearPersonal}>
				<div>
					<p> Nombre </p>
					<input name="nombre" />
					<p> Tipo </p>
					<select name="tipo">
                        <option value="CHOFER"> CHOFER </option>
                        <option value="LIMPIEZA"> LIMPIEZA </option>
                        <option value="SEGURIDAD"> SEGURIDAD </option>
                    </select>
				</div>
				<button> Crear </button>
			</form>
		</>
	)
}
