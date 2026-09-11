import { useEffect, useState } from "react"
import CarCard from "./CarCard"

export default function Vehiculos() {

    const [coches, setCoches] = useState([])

    async function getCoches() {

        const listaDeCoches = await fetch("http://localhost:8091/api/vehiculos", {
            headers: { "Content-Type": "application/json", Authorization: `Bearer ${localStorage.getItem("token")}` },
            method: "GET"
        })

        if (!listaDeCoches.ok) {console.log("Err")}

        setCoches(await listaDeCoches.json())
    }

    useEffect(() => {
        getCoches()
    }, [])

    return (
        <>
            {coches.map(p => <CarCard key={p.id} disponible={p.disponible} nombre={p.nombre} kilometraje={p.kilometraje} matricula={p.matricula} plazas={p.plazas}/> )}
        </>
    )
}