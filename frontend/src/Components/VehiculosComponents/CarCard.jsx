export default function CarCard({ nombre, kilometraje, matricula, plazas, enCarrito, onAdd }) {
    return (
        <div className="card">
            <p> {nombre} </p>
            <p>{kilometraje} km</p>
            <p>{matricula}</p>
            <p>{plazas} plazas </p>
            <button className="card-btn" onClick={onAdd} disabled={enCarrito}>
                {enCarrito ? "En el carrito ✓" : "Añadir +"}
            </button>
        </div>
    )
}
