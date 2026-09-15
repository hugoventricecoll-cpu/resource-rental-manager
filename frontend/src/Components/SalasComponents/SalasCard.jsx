export default function SalasCard({ sala, enCarrito, onAdd }) {
    return (
        <div className="card">
            <p> {sala.nombre}</p>
            <p>Aforo: {sala.aforo} </p>
            <p>{sala.ubicacion} </p>
            <button className="card-btn" onClick={onAdd} disabled={enCarrito}>
                {enCarrito ? "En el carrito ✓" : "Añadir +"}
            </button>
        </div>
    )
}
