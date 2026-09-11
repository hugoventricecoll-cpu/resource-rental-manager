import { useEffect, useState } from "react"
import PersonalCard from "./PersonalCard"

export default function Personal() {

    const [personal, setPersonal] = useState([])

    async function getPersonal() {

        const personalList = await fetch("http://localhost:8091/api/personal", {
            headers: { "Content-Type": "application/json", Authorization: `Bearer ${localStorage.getItem("token")}` },
            method: "GET"
        })

        if (!personalList.ok) { console.log("Err") }
        setPersonal(await personalList.json())
    }

    useEffect(() => {
        getPersonal()
    }, [])

    return (
        <>
            {personal.map(p => <PersonalCard key={p.id} persona={p} />)}
        </>
    )
}