export default function SalasCard({sala}) {
    return(
    <div>
        <p> {sala.nombre}</p>
        <p>{sala.tipo} </p>
        <p>{sala.disponible}</p>
        <p>{(sala.disponible === true) ? "🟢 Disponible" : "🔴 No disponible"}</p>
    </div>
    )
}