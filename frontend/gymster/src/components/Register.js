import React, { useState } from "react";
import {  useNavigate ,Link } from 'react-router-dom';
import {globalMessages}  from '../global/Messages'
import { C_API_BASE_URL } from '../global/Api';
import  '../styles/style.css';
import logoIcon from '../public/img/logo_icon.svg';
import logoText from '../public/img/logo.svg';

const Register = () => {

  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    username: "",
    email: "",
    password: "",
    confirmPassword: "",
  });
  const [errMsg, setErrMsg] = useState('');
  
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };
  

  const handleSubmit = (e) => {
    setErrMsg("");
    e.preventDefault();
    let validationMess="";
    if(formData.username.length<6 || formData.username.length>15){
      validationMess = globalMessages.wrongUsernameMess;
    }
    if(formData.password.length<8 || formData.password.length>16){
      validationMess= validationMess+' \n'+globalMessages.wrongPasswordMess;
    }
    else if(formData.password !== formData.confirmPassword){
      validationMess= validationMess+' \n'+globalMessages.passwordsNotMatchMess;
    }

    if(validationMess){
      setErrMsg(validationMess);
    }
    else{
      const registrationData = {
      username: formData.username,
      email: formData.email,
      password: formData.password,
      confirmPassword: formData.confirmPassword,
    };

    try {

      fetch(C_API_BASE_URL+'/user/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(registrationData),
      })
        .then((response) => {
          if (response.ok) {
            navigate('/login', { state: { message: globalMessages.succesRegistrationMess } });
          }
          else if(response.status===409){
            return response.json();;
          }
          else if(response.status===500){
            setErrMsg(globalMessages.internalServerError);
            return null;
          }
          else{
            setErrMsg(globalMessages.failedRegisterMess);
            return null;
          }
        })
        .then((data) => {
          if (data) {
            if(data.responseText){
              setErrMsg(data.responseText);
            }
          } 
        })
        .catch((error) => {
          setErrMsg(error);
        });
    } catch (err) {
      setErrMsg(err);
   }
  }
  };

  return (
    <div className="register-base-container">

        <div className="logo-container">
          <Link to="/">
            <div className="logo-icon">
              <img src={logoIcon} alt="Logo-icon" className='icon'/>
            </div>
            <div className="logo-text">
              <img src={logoText} alt="Logo-text" />
            </div>
          </Link>
        </div>
        <div className="register-container">
            <form name="registerForm" onSubmit={handleSubmit} className="register" >
              <input
              type="text"
              name="username"
              placeholder="username"
              value={formData.username}
              onChange={handleChange}
              />
              <input
              type="email"
              name="email"
              placeholder="email"
              value={formData.email}
              onChange={handleChange}
              />
              <input
              type="password"
              name="password"
              placeholder="password"
              value={formData.password}
              onChange={handleChange}
              />
              <input
              type="password"
              name="confirmPassword"
              placeholder="password"
              value={formData.confirmPassword}
              onChange={handleChange}
              />
              <button type="submit">Register</button>
              <div className="error-message">
                    
                    {errMsg.split('\n').map((line, index) => (
                      <React.Fragment key={index}>
                        {line}
                        <br />
                      </React.Fragment>
                    ))}
              </div>
            </form>
        </div>
    </div>  
  );
}

export default Register;
