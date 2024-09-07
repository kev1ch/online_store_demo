import { useEffect, useContext, useRef } from "react";
import { LogInContext } from "./LogInContext";


function LogIn() {

    const {login, setLogin} = useContext(LogInContext);
    const user_ref = useRef();
    const password_ref = useRef();

    /*
    useEffect(() => {
        var new_login = {...login};
        new_login.login = !login.login;
        setLogin(new_login);
    }, []);
    */

    async function auth(login_val, password_val) {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ login: login_val, password: password_val})
        };
        const response = await(fetch('http://localhost:8081/account/auth', requestOptions));

        if (response.ok) {
            const jwt = await(response.text());  
            console.log(jwt);
          } else {
            throw "auth error: incorrect login/password";
        }

    }

    return (
        <>
            {!login.login ?
                <>
                    <div>User:</div>
                    <input ref={user_ref}/>
                    <div>Password:</div>
                    <input ref={password_ref}/>
                    <button onClick={() => {
                        var user_val = user_ref.current.value;
                        var password_val = password_ref.current.value;
                        auth(user_val, password_val);
                        console.log(user_val);
                        console.log(password_val);
                        var new_login = {...login};
                        new_login.login = !login.login;
                        setLogin(new_login);
                    }}>Log In</button>
                </>
            :
                <button onClick={() => {
                    var new_login = {...login};
                    new_login.login = !login.login;
                    setLogin(new_login);
                }}>Log Out</button>
            }
        </>
    );
}

export default LogIn;