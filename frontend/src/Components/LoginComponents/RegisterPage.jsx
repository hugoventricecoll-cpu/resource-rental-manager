import { useNavigate } from "react-router-dom"

export default function RegisterPage({ login, setLogin }) {

    const navigate = useNavigate()

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
        localStorage.setItem("userMail", mail)

        const me = await fetch("http://localhost:8091/api/auth/me", {
            headers: { "Content-Type": "application/json", Authorization: `Bearer ${token}` },
            method: "GET"
        })
        if (me.ok) {
            localStorage.setItem("userId", await me.text())
        }

        navigate("/hub")
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
            <div className='switchAuth'>
                Already have an account? <button onClick={makeItlog} className='plain-button' > Login </button>
            </div>
        </>
    )
}