import { Link, Outlet, useLocation } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHouse, faCar, faDoorOpen, faUsers, faCalendarCheck, faTicket, faPerson } from "@fortawesome/free-solid-svg-icons";
import { useEffect, useState } from "react";

export default function Hub() {
    const location = useLocation()
    const isHome = location.pathname === "/hub"
    const [userRol, setRol] = useState(null)

    useEffect(() => {
        async function getRol() {
            const usuario = await fetch("http://localhost:8091/api/auth/me", {
                headers: { "Content-Type": "application/json", Authorization: `Bearer ${localStorage.getItem("token")}` },
                method: "GET"
            })

            const mensaje = await usuario.json()

            const rol = mensaje.authorities[0].authority

            setRol(rol)
            localStorage.setItem("rol", rol)
        }

        getRol()
    }, [])

    return (
        <>
            <nav className="navbar">
                <Link to={"/hub"}><FontAwesomeIcon icon={faHouse} className="nav-icon" /> Inicio </Link>
                <Link to={"/hub/Vehiculos"}><FontAwesomeIcon icon={faCar} className="nav-icon" /> Vehiculos </Link>
                <Link to={"/hub/Salas"}><FontAwesomeIcon icon={faDoorOpen} className="nav-icon" /> Salas </Link>
                <Link to={"/hub/Personal"}><FontAwesomeIcon icon={faUsers} className="nav-icon" /> Personal </Link>
                <Link to={"/hub/Alquilacion"}><FontAwesomeIcon icon={faTicket} className="nav-icon" /> Reserva </Link>
                {userRol === 'ROLE_ADMIN' && <Link to={"/hub/AdminPanel"}><FontAwesomeIcon icon={faPerson} className="nav-icon" />  AdminPanel </Link>}
            </nav>

            <main className="content">
                {isHome && (
                    <>
                        <section className="hero">
                            <div className="hero-text">
                                <p className="hero-kicker"><FontAwesomeIcon icon={faCalendarCheck} /> Panel principal</p>
                                <h1>Gestor de Logística</h1>
                                <p>Organiza tus eventos: reserva vehículos y salas, y asigna el personal necesario, todo desde un mismo lugar.</p>
                            </div>
                            <img
                                className="hero-img"
                                src="https://images.unsplash.com/photo-1519167758481-83f550bb49b3?auto=format&fit=crop&w=800&q=60"
                                alt="Sala preparada para un evento"
                                loading="lazy"
                            />
                        </section>
                        <h2 className="dash-title">¿Qué quieres gestionar hoy?</h2>
                        <div className="dash-grid">
                            <Link to={"/hub/Vehiculos"} className="dash-card">
                                <img
                                    src="https://images.unsplash.com/photo-1449965408869-eaa3f722e40d?auto=format&fit=crop&w=800&q=60"
                                    alt="Coche en carretera"
                                    loading="lazy"
                                />
                                <div className="dash-card-body">
                                    <FontAwesomeIcon icon={faCar} />
                                    <h2>Vehículos</h2>
                                    <p>Consulta la flota: kilometraje, matrícula, plazas y disponibilidad de cada coche.</p>
                                    <span>Ver vehículos →</span>
                                </div>
                            </Link>
                            <Link to={"/hub/Salas"} className="dash-card">
                                <img
                                    src="https://images.unsplash.com/photo-1519167758481-83f550bb49b3?auto=format&fit=crop&w=800&q=60"
                                    alt="Salón de eventos"
                                    loading="lazy"
                                />
                                <div className="dash-card-body">
                                    <FontAwesomeIcon icon={faDoorOpen} />
                                    <h2>Salas</h2>
                                    <p>Explora las salas disponibles: ubicación, aforo y estado de cada espacio para tus eventos.</p>
                                    <span>Ver salas →</span>
                                </div>
                            </Link>
                            <Link to={"/hub/Personal"} className="dash-card">
                                <img
                                    src="https://images.unsplash.com/photo-1522071820081-009f0129c71c?auto=format&fit=crop&w=800&q=60"
                                    alt="Equipo de trabajo"
                                    loading="lazy"
                                />
                                <div className="dash-card-body">
                                    <FontAwesomeIcon icon={faUsers} />
                                    <h2>Personal</h2>
                                    <p>Revisa el equipo: chóferes y personal de apoyo, con su rol y disponibilidad.</p>
                                    <span>Ver personal →</span>
                                </div>
                            </Link>
                        </div>
                    </>
                )}
                <Outlet />
            </main>
        </>
    )
}
