export default function PersonalCard({ persona, enCarrito, onAdd }) {
    return (
        <div className="card">
            <p> {persona.nombre} </p>
            <p> {persona.tipo} </p>
            <button className="card-btn" onClick={onAdd} disabled={enCarrito}>
                {enCarrito ? "En el carrito ✓" : "Añadir +"}
            </button>
        </div>
    )
}
