

import { useRef, useState, useEffect } from 'react';
import React from "react";
import {  useNavigate, useLocation  } from 'react-router-dom';
import { C_API_BASE_URL } from '../global/Api';
import  '../styles/style.css';
import {globalMessages}  from '../global/Messages'
import logoIcon from '../public/img/logo_icon.svg';
import logoText from '../public/img/logo.svg';


const Login = () => {

    const navigate = useNavigate();
    const location = useLocation();
    const [message,setMessage] = useState(location.state?.message);
    const userRef = useRef();
    const errRef = useRef();
    const [user, setUser] = useState('');
    const [pwd, setPwd] = useState('');
    const [errMsg, setErrMsg] = useState('');

    useEffect(() => {
        userRef.current.focus();
    }, [])

    const handleSubmit = async (e) => {
        setMessage("");
        setErrMsg("");
        e.preventDefault();
        try {
           const response = await fetch(C_API_BASE_URL+'/auth/login', {
           method: 'POST',
           headers: {
           'Content-Type': 'application/json',
          } ,
          body: JSON.stringify({ "username" : user,"password": pwd }),
      });
      if (response.status === 200) {

            const data = await response.json();
            const {token , roleId} = data;
            const roles = [roleId];
            localStorage.setItem('username', user);
            localStorage.setItem('role', roles);
            localStorage.setItem('token', token);
            navigate('/home');
    }
    else if (response.status === 401){
        setErrMsg(globalMessages.wrongCredentialsMess);
    }
    else if (response.status === 500){
        setErrMsg(globalMessages.failedLoginMess);
    }
    else {
        setErrMsg(globalMessages.errorLoginMess);
    }
        } catch (err) {
            if (!err?.response) {
                setErrMsg(globalMessages.noServerResponseMess);
            } else if (err.response?.status === 401) {
                setErrMsg(globalMessages.wrongCredentialsMess);
            } else {
                setErrMsg(globalMessages.failedLoginMess);
            }
            errRef.current.focus();
        }
    }

    return (
      <section className="container">
        <div className="logo-container">
            <div className="logo-icon">
            <img src={logoIcon} alt="Logo-icon" className='icon'/>
            </div>
            <div className="logo-text">
            <img src={logoText} alt="Logo-text" clasName='logo-icon'/>
            </div>
        </div>
        <div className="login-container">
            <form onSubmit={handleSubmit} className="login">
                <input
                    type="text"
                    id="username"
                    placeholder="username"
                    ref={userRef}
                    autoComplete="off"
                    onChange={(e) => setUser(e.target.value)}
                    value={user}
                    required
                />
                <input
                    type="password"
                    id="password"
                    placeholder="password"
                    onChange={(e) => setPwd(e.target.value)}
                    value={pwd}
                    required
                />
                <button type="submit">Log in</button>
                <div className="error-message">
                    { invalidSession(message) ? (
                        message
                        ) :
                        ("" )
                    }
                    {errMsg}
                </div>
                <div className="success-message">
                    { succesRegistration(message) ? (
                        message 
                        ) :
                        ("" )
                    }
                </div>
                <label className='register-link' for="register">Don't have an account? Register <b><a href="/register" className="register-a">here</a></b></label>
            </form>
        </div>
      </section>      

    )
}

function invalidSession(message){
    return globalMessages.invalidSessionMess === message;
}

function succesRegistration(message){
    return globalMessages.succesRegistrationMess === message;
}

export default Login
