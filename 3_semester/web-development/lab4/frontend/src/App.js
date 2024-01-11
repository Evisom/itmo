import './App.css';
import {useEffect, useState} from "react";
import {useNavigate} from 'react-router-dom';
import Main from './Main';
import { setSessionID } from './redux/dataActions';
import { useDispatch } from 'react-redux';

const App = () => {
    let navigate = useNavigate();
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [correctUserInfo, setCorrectUserInfo] = useState(false);
    const [error, setError] = useState('')

    const dispatch = useDispatch()
    const login = () => {
        request('login', {
            username: username,
            password: password
        }, (response) => {
            if (response.sessionID) {
                localStorage.setItem('sessionID', response.sessionID)
                localStorage.setItem('username', username)
                dispatch(setSessionID(response.sessionID));
                navigate("/main")
            } else {
                setError("Error")
            }
        }, (error) => {
            setError('Invalid username or password!')
        })
    }

    const createAccount = () => {
        request('register', {
            username: username,
            password: password
        }, (response) => {
            if (response.sessionID) {
                localStorage.setItem('sessionID', response.sessionID)
                localStorage.setItem('username', username)
                navigate("/main")
            } else {
                setError("Error")
            }
        }, (error) => {
            setError('Username must be unique!')
        })
    }

    useEffect(() => {
        setCorrectUserInfo(username.length > 0 && password.length > 0);
        setError("")
    }, [username, password]);
    const request = (path, body, callback, handle_error) => {
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(body)
        }
        fetch('/lab4/app/' + path, options)
            .then(response => response.json())
            .then(data => callback(data))
            .catch(error => handle_error(error));
    }


    return (
        <div className="wrapper">
            <div className="window">
                <div className="title-bar">
                    <button aria-label="Close" className="close"></button>
                    <h1 className="title">Assignment #4</h1>
                    <button aria-label="Resize" className="resize"></button>
                </div>
                <div className="separator"></div>

                <div className="details-bar">
                    <span>P3215</span>
                    <span>Dmitriy Shishmintsev</span>
                    <span>12362</span>
                </div>
                <div className="window-pane">
                    <div className="login-form">
                        <label htmlFor="text_email">Username</label>
                        <input id="text_email" placeholder="" onChange={e => setUsername(e.target.value)}/>
                        <label htmlFor="text_pwd">Password</label>
                        <input id="text_pwd" type="password" onChange={e => setPassword(e.target.value)}/>
                        <div>
                            <button className="btn" disabled={!correctUserInfo} onClick={login}>Login</button>
                            <button className="btn" disabled={!correctUserInfo} onClick={createAccount}>Create an
                                account
                            </button>
                        </div>
                    </div>
                    <p className="error">{error}</p>
                </div>
            </div>
        </div>

    );
}

export default App;
