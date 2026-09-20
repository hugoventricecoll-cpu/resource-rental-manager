import { useEffect, useState } from "react"
import CarCard from "./CarCard"

export default function Vehiculos({ carrito, setCarrito }) {

    const [coches, setCoches] = useState([])

    async function getCoches() {

        const listaDeCoches = await fetch("http://localhost:8091/api/vehiculos", {
            headers: { "Content-Type": "application/json", Authorization: `Bearer ${localStorage.getItem("token")}` },
            method: "GET"
        })

        if (!listaDeCoches.ok) {
            console.log("GET /api/vehiculos failed:", listaDeCoches.status, await listaDeCoches.text());
            return;
        }

        setCoches(await listaDeCoches.json())
    }

    useEffect(() => {
        getCoches()
    }, [])

    function addAlCarrito(coche) {
        if (carrito.some(i => i.kind === "producto" && i.id === coche.id)) return;
        setCarrito([...carrito, { kind: "producto", id: coche.id, nombre: coche.nombre }]);
    }

    function handleDeleted(id) {
        setCoches(prev => prev.filter(c => c.id !== id));
        setCarrito(prev => prev.filter(i => !(i.kind === "producto" && i.id === id)));
    }

    return (
        <div className="card-grid">
            {coches.map(p => <CarCard key={p.id} id={p.id} nombre={p.nombre} kilometraje={p.kilometraje} matricula={p.matricula} plazas={p.plazas} enCarrito={carrito.some(i => i.kind === "producto" && i.id === p.id)} onAdd={() => addAlCarrito(p)} onDeleted={handleDeleted} />)}
        </div>
    )
}
