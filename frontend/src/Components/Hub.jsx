import { Link, Outlet } from "react-router-dom";

export default function Hub() {
    return(
    <>
        <nav> 
            <Link to={"/hub"} > Inicio </Link>
            <Link to={"/hub/Vehiculos"}> Vehiculos </Link>
            <Link to={"/hub/Salas"}> Salas </Link>
            <Link to={"/hub/Personal"}> Personal </Link>
        </nav>

        <Outlet />
    </>
    )
}