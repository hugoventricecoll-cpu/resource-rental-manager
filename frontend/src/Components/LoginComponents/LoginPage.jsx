import { useNavigate } from "react-router-dom"

export default function LoginPage({login, setLogin}) {

    const navigate = useNavigate()

    function makeItlog(){
        setLogin(false)
    }

    async function loginFunc(e){
        e.preventDefault()

        const mail = e.target.mail.value
        const password = e.target.password.value

        const usuario = await fetch("http://localhost:8091/api/auth/login",{
            headers: { "Content-Type": "application/json" },
            method: "POST",
            body: JSON.stringify({correo: mail, password: password})
        })

        if (!usuario.ok) {
            const mensaje = await usuario.text()
            console.log("Error:", usuario.status, mensaje)
            return
        }

        const token = await usuario.text()

        console.log(token)

        localStorage.setItem("token", token)

        navigate("/hub")
    }

    return (
        <>
            <div className='topText'>
                <h2>
                    Login
                </h2>
                <p>
                    Introduce your mail and password to login
                </p>
            </div>
            <div className='loginForm'>
                <form onSubmit={loginFunc}>
                    <div>
                        <p> Mail </p>
                        <input name='mail' />
                    </div>
                    <div>
                        <p> Password </p>
                        <input type='password' name='password' />
                    </div>
                    <button> Login </button>
                </form>
            </div>
            <div className='haveNoAcc?'>
                Dont have an account? <button onClick={makeItlog} className='plain-button'> Register </button>
            </div>
        </>
    )
}