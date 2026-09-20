export default function CarCard({ nombre, kilometraje, matricula, plazas, enCarrito, onAdd, id, onDeleted }) {

    
    async function removeItem() {
        const item = await fetch("http://localhost:8091/api/vehiculos/" + `${id}`, {
            headers: { "Content-Type": "aplication/json", Authorization: `Bearer ${localStorage.getItem("token")}` },
            method: "DELETE",
        })
        if (!item.ok) { alert("ERR ERR"); return; }
        onDeleted?.(id);
    }

    return (
        <div className="card">
            <p> {nombre} </p>
            <p>{kilometraje} km</p>
            <p>{matricula}</p>
            <p>{plazas} plazas </p>
            <button className="card-btn" onClick={onAdd} disabled={enCarrito}>
                {enCarrito ? "En el carrito ✓" : "Añadir +"}
            </button>
            {localStorage.getItem("rol") === "ROLE_ADMIN" && <button className="delete-x" title="Borrar vehículo" aria-label="Borrar vehículo" onClick={removeItem}>X</button>}
        </div>
    )
}

