export default function SalasCard({ sala, enCarrito, onAdd, id, onDeleted }) {

    async function removeItem() {
        const item = await fetch("http://localhost:8091/api/sala/" + `${id}`, {
            headers: { "Content-Type": "aplication/json", Authorization: `Bearer ${localStorage.getItem("token")}` },
            method: "DELETE",
        })
        if (!item.ok) { alert("ERR ERR"); return; }
        onDeleted?.(id);
    }

    return (
        <div className="card">
            <p> {sala.nombre}</p>
            <p>Aforo: {sala.aforo} </p>
            <p>{sala.ubicacion} </p>
            <button className="card-btn" onClick={onAdd} disabled={enCarrito}>
                {enCarrito ? "En el carrito ✓" : "Añadir +"}
            </button>
            {localStorage.getItem("rol") === "ROLE_ADMIN" && <button className="delete-x" title="Borrar sala" aria-label="Borrar sala" onClick={removeItem}>X</button>}
        </div>
    )
}