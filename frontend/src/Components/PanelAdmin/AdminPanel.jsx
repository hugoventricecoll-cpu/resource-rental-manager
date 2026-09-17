import CrearVehiculos from "./CrearVehiculos"
import CrearSalas from "./CrearSalas"
import CrearPersonal from "./CrearPersonal"

export default function AdminPanel() {

    return (
        <main className="admin-panel">
            <h1>Admin Panel</h1>
            <div className="admin-panel-grid">
                <section className="admin-section">
                    <h2> Crear Vehiculos </h2>
                    <CrearVehiculos />
                </section>
                <section className="admin-section">
                    <h2> Crear Salas </h2>
                    <CrearSalas />
                </section>
                <section className="admin-section">
                    <h2> Añadir personal </h2>
                    <CrearPersonal />
                </section>
            </div>
        </main>
    )
}