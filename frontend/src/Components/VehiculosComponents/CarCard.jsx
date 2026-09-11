export default function CarCard({disponible, nombre, kilometraje, matricula, plazas}){
    return(
        <div>
            <p> {nombre} </p>
            <p>{kilometraje}</p>
            <p>{matricula}</p>
            <p>{plazas} </p>
            <p>{(disponible === true) ? "🟢 Disponible" : "🔴 No disponible"} </p>
        </div>
    )
}