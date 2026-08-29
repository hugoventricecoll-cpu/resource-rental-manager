export default function RegisterPage({ login, setLogin }) {

    function makeItlog() {
        setLogin(true)
    }

    async function registerFunc(e) {
        e.preventDefault()
        const nombre = e.target.nombre.value
        const apellido = e.target.apellido.value
        const mail = e.target.mail.value
        const numeroTel = e.target.numeroTel.value
        const password = e.target.password.value

        const usuario = await fetch("http://localhost:8091/api/auth/register", {
            headers: { "Content-Type": "application/json" },
            method: "POST",
            body: JSON.stringify({
                nombre: nombre,
                apellido: apellido,
                correo: mail,
                numeroTel: numeroTel,
                password: password
            })
        })

        if (!usuario.ok) {
            const mensaje = await usuario.text()
            console.log("Error:", usuario.status, mensaje)
            return
        }

        const token = await usuario.text()

        localStorage.setItem("token", token)

        return usuario;
    }

    async function test() {
        const token = localStorage.getItem("token")

        const respuesta = await fetch("http://localhost:8091/api/vehiculos", {
            headers: {
                "Authorization": `Bearer ${token}`
            }
        })

        const datos = await respuesta.json()

        console.log(datos)
    }


    return (
        <>
            <div className='topText'>
                <h2>
                    Register
                </h2>
                <p>
                    Introduce your mail and password to create an account
                </p>
            </div>
            <div className='loginForm'>
                <form onSubmit={registerFunc}>
                    <div>
                        <p> Name </p>
                        <input name='nombre' />
                    </div>
                    <div>
                        <p> Last name </p>
                        <input name='apellido' />
                    </div>
                    <div>
                        <p> Mail </p>
                        <input name='mail' />
                    </div>
                    <div>
                        <p> Phone number </p>
                        <input name='numeroTel' />
                    </div>
                    <div>
                        <p> Password </p>
                        <input type='password' name='password' />
                    </div>
                    <button> Register </button>
                </form>
            </div>
            <div className='haveAcc?'>
                Already have an account? <button onClick={makeItlog} className='plain-button' > Login </button>
            </div>
            <button onClick={test}> test </button>
        </>
    )
}