import { useState, useEffect } from "react";

export default function BorrarReserva() {

    const [reservas, setReservas] = useState([])

    async function getReservas() {
        const res = await fetch("http://localhost:8091/api/alquilacion", {
            headers: { "Content-Type": "application/json", Authorization: `Bearer ${localStorage.getItem("token")}` },
            method: "GET"
        });

        if (!res.ok) return;
        const todas = await res.json();

        setReservas(todas);
    }

    useEffect(() => {
        getReservas();
    }, []);

    async function deleteReserva(id) {
        const dRes = await fetch("http://localhost:8091/api/alquilacion/" + `${id}`, {
            headers: { "Content-Type": "application/json", Authorization: `Bearer ${localStorage.getItem("token")}` },
            method: "DELETE"
        })

        if (!dRes.ok) {
            alert("ERR ERR");
            return
        }

        setReservas(reservas.filter(r => r.id !== id))
    }

    return (
        <>
            {reservas.map(a => (
                <div className="card" key={a.id}>
                    <p>{a.productos?.map(p => p.nombre).join(", ")}</p>
                    <p>{a.fechaInicio?.replace("T", " ")} → {a.fechaFin?.replace("T", " ")}</p>
                    <p>{a.personal?.map(p => p.nombre).join(", ")}</p>
                    <button className="delete-x" title="Borrar reserva" aria-label="Borrar reserva" onClick={(() => deleteReserva(a.id))}>X</button>
                </div>
            ))}
        </>
    )
}