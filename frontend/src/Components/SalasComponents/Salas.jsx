import { useEffect, useState } from "react"
import SalasCard from "./SalasCard"

export default function Salas({ carrito, setCarrito }) {

    const [salas, setSalas] = useState([])

    async function getSalas() {

        const salasList = await fetch("http://localhost:8091/api/sala", {
            headers: { "Content-Type": "application/json", Authorization: `Bearer ${localStorage.getItem("token")}` },
            method: "GET"
        })

        if (!salasList.ok) {
            console.log("GET /api/sala failed:", salasList.status, await salasList.text());
            return;
        }

        setSalas(await salasList.json())
    }

    useEffect(() => {
        getSalas()
    }, [])

    function addAlCarrito(sala) {
        if (carrito.some(i => i.kind === "producto" && i.id === sala.id)) return;
        setCarrito([...carrito, { kind: "producto", id: sala.id, nombre: sala.nombre }]);
    }

    function handleDeleted(id) {
        setSalas(prev => prev.filter(s => s.id !== id));
        setCarrito(prev => prev.filter(i => !(i.kind === "producto" && i.id === id)));
    }

    return (
        <div className="card-grid">
            {salas.map(s => <SalasCard key={s.id} id={s.id} sala={s} enCarrito={carrito.some(i => i.kind === "producto" && i.id === s.id)} onAdd={() => addAlCarrito(s)} onDeleted={handleDeleted} />)}
        </div>
    )
}
