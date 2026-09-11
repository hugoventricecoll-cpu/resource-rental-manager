export default function PersonalCard({persona}){
    return(
        <div>
            <p> {persona.nombre} </p>
            <p> {persona.tipo} </p>
            <p> {(persona.disponible) ? "🟢 Disponible" : "🔴 No disponible"} </p>
        </div>
    )
}