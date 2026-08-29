export default function LoginPage({login, setLogin}) {

    function makeItlog(){
        setLogin(false)
    }

    async function loginFunc(e){
        e.preventDefault()
        console.log(e.target.mail.value)
        console.log(e.target.password.value)


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