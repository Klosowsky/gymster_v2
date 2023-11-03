import React, { useState } from 'react';
import logoText from '../public/img/logo.svg'; 
import  '../styles/style.css';
import {  useNavigate ,Link } from 'react-router-dom';
import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';



function Header() {
  const role = localStorage.getItem("role");
  const username = localStorage.getItem("username");
  const navigate = useNavigate();
  console.log('ee' +role);
    const [open, setOpen] = React.useState(false);

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

  // Attach the document click handler to the window when the component mounts
  React.useEffect(() => {
    window.addEventListener('click', handleDocumentClick);
    return () => {
      window.removeEventListener('click', handleDocumentClick);
    };
  }, []);

  const redirectAdmin = () => {
    navigate("/admin");
  }

  const redirectProfile = () => {
    navigate("/profile");
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

      { /* Assume localUsername and localImage are props */ }
      <div className="header-user-details" onClick={handleOpen}>
        <div className="user-username">{username}</div>

        <div className="user-photo">
          <img src="/uploads/Will_Smith.jpg" className="user-profile-img" alt="IMAGE" />   {// TO DO - Add link to valid photo
          }

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



        {/*isDropdownVisible && (
          <div className="dropdown-menu-content">
            <a href="/userpanel">Your profile</a>
            {//localPrivilege === 1 && <a href="/adminpanel">Admin panel</a>
            }
            <a href="/logout">Log out</a>
          </div>
        )*/}
      </div>
    </div>
  );
}

export default Header;
