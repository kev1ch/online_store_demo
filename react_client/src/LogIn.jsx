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

    return (
        <>
            {!login.login ?
                <>
                    <div>User:</div>
                    <input ref={user_ref}/>
                    <div>Password:</div>
                    <input ref={password_ref}/>
                    <button onClick={() => {
                        console.log(user_ref.current.value);
                        console.log(password_ref.current.value);
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