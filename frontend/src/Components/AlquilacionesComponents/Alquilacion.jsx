import { useEffect, useState } from "react"

export default function Alquilacion({ carrito, setCarrito }) {
    const [inicio, setInicio] = useState("");
    const [fin, setFin] = useState("");
    const [mensaje, setMensaje] = useState("");
    const [misReservas, setMisReservas] = useState([]);

    const productos = carrito.filter(i => i.kind === "producto");
    const personal = carrito.filter(i => i.kind === "personal");


    // Soy consciente: endpoint inseguro, da TODAS las reservas a cualquier persona que tenga el rol "ROLE_USER", solo se filtra en el frontend para mostrar unicamente las del usuario.

    async function getMisReservas() {
        const res = await fetch("http://localhost:8091/api/alquilacion", {
            headers: { "Content-Type": "application/json", Authorization: `Bearer ${localStorage.getItem("token")}` },
            method: "GET"
        });

        if (!res.ok) {
            setMensaje(`Error ${res.status}: ${await res.text()}`);
            return;
        }
        const todas = await res.json();
        const miCorreo = localStorage.getItem("userMail");
        setMisReservas(todas.filter(a => a.usuario?.correo === miCorreo));
    }

    useEffect(() => {
        getMisReservas();
    }, []);

    function quitar(item) {
        setCarrito(carrito.filter(i => !(i.kind === item.kind && i.id === item.id)));
    }

    async function confirmar() {
        setMensaje("");
        if (productos.length === 0) {
            setMensaje("Añade al menos un vehículo o sala al carrito.");
            return;
        }
        if (!inicio || !fin) {
            setMensaje("Elige fecha de inicio y de fin.");
            return;
        }

        const res = await fetch("http://localhost:8091/api/alquilacion", {
            headers: { "Content-Type": "application/json", Authorization: `Bearer ${localStorage.getItem("token")}` },
            method: "POST",
            body: JSON.stringify({
                productoIds: productos.map(p => p.id),
                personalIds: personal.map(p => p.id),
                fechaInicio: inicio,
                fechaFin: fin
            })
        });

        if (!res.ok) {
            setMensaje(`Error ${res.status}: ${await res.text()}`);
            return;
        }

        setMensaje("Reserva creada ✓");
        setCarrito([]);
        setInicio("");
        setFin("");
        getMisReservas();
    }

    return (
        <>
            <section className="card form-card">
                <h2>Mi reserva</h2>
                {carrito.length === 0 && <p>El carrito está vacío. Añade vehículos, salas o personal desde sus páginas.</p>}
                <ul className="cart-list">
                    {carrito.map(i => (
                        <li key={`${i.kind}-${i.id}`}>
                            <span>[{i.kind === "producto" ? "Producto" : "Personal"}] {i.nombre}</span>
                            <button className="plain-button" onClick={() => quitar(i)}>Quitar</button>
                        </li>
                    ))}
                </ul>
                <div className="form-row">
                    <label>Inicio <input type="datetime-local" value={inicio} onChange={e => setInicio(e.target.value)} /></label>
                    <label>Fin <input type="datetime-local" value={fin} onChange={e => setFin(e.target.value)} /></label>
                </div>
                <button className="accent-btn" onClick={confirmar}>Confirmar reserva</button>
                {mensaje && <p>{mensaje}</p>}
            </section>

            <section>
                <h2>Mis reservas</h2>
                <div className="card-grid">
                    {misReservas.map(a => (
                        <div className="card" key={a.id}>
                            <p>{a.productos?.map(p => p.nombre).join(", ")}</p>
                            <p>{a.fechaInicio?.replace("T", " ")} → {a.fechaFin?.replace("T", " ")}</p>
                            <p>{a.personal?.map(p => p.nombre).join(", ")}</p>
                        </div>
                    ))}
                </div>
                {misReservas.length === 0 && <p>Todavía no tienes reservas.</p>}
            </section>
        </>
    )
}
