import { useEffect, useState } from "react"
import SalasCard from "./SalasCard"

export default function Salas() {

    const [salas, setSalas] = useState([])

    async function getSalas() {

        const salasList = await fetch("http://localhost:8091/api/sala", {
            headers: {"Content-Type": "application/json", Authorization: `Bearer ${localStorage.getItem("token")}`},
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

    return (
        <>
            {salas.map(s => <SalasCard key={s.id} sala={s} />)}
        </>
    )
}