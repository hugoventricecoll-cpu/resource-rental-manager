import { useEffect } from "react"

export default function PersonalCard({ persona, enCarrito, Add, id, onDeleted }) {

    async function removeItem() {
        const item = await fetch("http://localhost:8091/api/personal/" + `${id}`, {
            headers: {"Content-Type": "aplication/json", Authorization: `Bearer ${localStorage.getItem("token")}`},
            method: "DELETE",
        })
        if (!item.ok) { alert("ERR ERR"); return; }
        onDeleted?.(id);
    }

    return (
        <div className="card">
            <p> {persona.nombre} </p>
            <p> {persona.tipo} </p>
            <button className="card-btn" onClick={Add}  disabled={enCarrito}>
                {enCarrito ? "En el carrito ✓" : "Añadir +"}
            </button>
            {localStorage.getItem("rol") === "ROLE_ADMIN" && <button className="delete-x" title="Borrar personal" aria-label="Borrar personal" onClick={removeItem}>X</button>}
        </div>
    )
}
