import React, { useState } from 'react';
import logoText from '../public/img/logo.svg'; 
import  '../styles/style.css';
import {  useNavigate ,Link } from 'react-router-dom';
import { C_API_BASE_URL } from '../global/Api';


function Header() {

  const role = localStorage.getItem("role");
  const username = localStorage.getItem("username");
  const navigate = useNavigate();
  const [open, setOpen] = React.useState(false);
  const [imageSrc, setImageSrc] = useState('');
  const storedToken = localStorage.getItem("token");

  const handleOpen = () => {
    setOpen(!open);
  };

  const handleDocumentClick = (event) => {
    if (
      !event.target.classList.contains('header-user-details') &&
      !event.target.classList.contains('user-username') &&
      !event.target.classList.contains('user-profile-img')
    ) {
        setOpen(false);
    }
  };

  React.useEffect(() => {
    window.addEventListener('click', handleDocumentClick);
    return () => {
      window.removeEventListener('click', handleDocumentClick);
    };
  }, []);

  React.useEffect(() => {
    fetch(C_API_BASE_URL+'/userdetails/getimage?username='+username, {
              method: 'GET',
              headers: {
                'Authorization': `Bearer ${storedToken}`,
              }, }) 
      .then(response => response.blob())
      .then(blob => {
        const imageUrl = URL.createObjectURL(blob);
        setImageSrc(imageUrl);
      })
      .catch(error => console.error('Error fetching image:', error));
  }, []);

  const redirectAdmin = () => {
    navigate("/adminpanel");
  }

  const redirectProfile = () => {
    navigate("/userpanel");
  }

  const logOut = () => {
    localStorage.clear();
    navigate("/");
  }

  return (
    <div className="trainings-header">
      <div className="header-logo">
       <Link to="/">
         <img src={logoText} alt="Logo" className='"header-logo-img'/>
       </Link>
      </div>
      <div className="header-user-details" onClick={handleOpen}>
        <div className="user-username">{username}</div>
        <div className="user-photo">
          {imageSrc && <img src={imageSrc} className="user-profile-img" alt="Image" />}
        </div>
        {open ? (
        <ul className="menu">
          <li className="menu-item">
            <button onClick={redirectProfile}>Profile</button>
          </li>
          <li className="menu-item">
            <button onClick={logOut}>Sign out</button>
          </li>
          {role === "1" ? (
            <li className="menu-item">
              <button onClick={redirectAdmin}>Admin panel</button>
            </li> ) 
            : null}
        </ul>
        ) : null}
      </div>
    </div>
  );
}

export default Header;
