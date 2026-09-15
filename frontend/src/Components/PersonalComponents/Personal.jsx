import { useEffect, useState } from "react"
import PersonalCard from "./PersonalCard"

export default function Personal({ carrito, setCarrito }) {

    const [personal, setPersonal] = useState([])

    async function getPersonal() {

        const personalList = await fetch("http://localhost:8091/api/personal", {
            headers: { "Content-Type": "application/json", Authorization: `Bearer ${localStorage.getItem("token")}` },
            method: "GET"
        })

        if (!personalList.ok) {
            console.log("GET /api/personal failed:", personalList.status, await personalList.text());
            return;
        }

        setPersonal(await personalList.json())
    }

    useEffect(() => {
        getPersonal()
    }, [])

    function addAlCarrito(persona) {
        if (carrito.some(i => i.kind === "personal" && i.id === persona.id)) return;
        setCarrito([...carrito, { kind: "personal", id: persona.id, nombre: persona.nombre }]);
    }

    return (
        <div className="card-grid">
            {personal.map(p => <PersonalCard key={p.id} persona={p}
                enCarrito={carrito.some(i => i.kind === "personal" && i.id === p.id)}
                onAdd={() => addAlCarrito(p)} />)}
        </div>
    )
}
