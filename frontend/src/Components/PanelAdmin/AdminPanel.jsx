import { useEffect } from "react"
import CrearVehiculos from "./CrearVehiculos"
import CrearSalas from "./CrearSalas"
import CrearPersonal from "./CrearPersonal"

export default function AdminPanel() {

    return (
        <>
            <h1> Admin Panel </h1>
            <div>
                <div>
                    <h2> Crear Vehiculos </h2>
                    <CrearVehiculos />
                </div>
                <div>
                    <h2> Crear Salas </h2>
                    <CrearSalas />
                </div>
                <div>
                    <h2> Añadir personal </h2>
                    <CrearPersonal />
                </div>
            </div>
        </>
    )
}