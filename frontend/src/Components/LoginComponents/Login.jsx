import { useState } from "react"
import LoginPage from "./LoginPage"
import RegisterPage from "./RegisterPage"

export default function Login() {

    const [login, setLogin] = useState(true)

    return(
        <div className='mainLoginPage'>
            <div className='sideText-mainLoginPage'>
                <h1>
                    Welcome!
                </h1>
                <p>
                    Login or register to 
                    make reservations for your events!
                </p>
            </div>
            <div className='loginMenu'>
                {(login === true) ? <LoginPage login={login} setLogin={setLogin} /> : <RegisterPage login={login} setLogin={setLogin} />}
            </div>
        </div>
    )
}