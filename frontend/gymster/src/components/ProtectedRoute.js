import { Navigate } from "react-router-dom";
import { C_API_BASE_URL } from '../global/Api';
import {  useState } from 'react';
import {globalMessages}  from '../global/Messages'


const ProtectedRoute = ({ children }) => {   

  const [check, setCheck] = useState(true);
  if (localStorage.getItem("token")) {
    const authenticatedData = {
      username: localStorage.getItem("username"),
      token: localStorage.getItem("token"),
      role: localStorage.getItem("role"),
    };
    
    const storedToken = localStorage.getItem("token");
   
    try {
      fetch(C_API_BASE_URL+'/auth/check', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${storedToken}`

        },
        body: JSON.stringify(authenticatedData),
      })
        .then((response) => {
          if (response.ok) {
            return response.json();
          } else {
            return null;
          }
        })
        .then((data) => {
          if (data) {
            if(!data.authenticated){
              localStorage.clear();
              setCheck(false);
            }
          } else {
            localStorage.clear();
            setCheck(false);
          }
        })
        .catch((error) => {
          localStorage.clear();
          setCheck(false);
        });
    } catch (err) {
      setCheck(false);
   }
   if(check){
    return children;
   }
  }
  return <Navigate to="/login" state={{ message: globalMessages.invalidSessionMess }}/>;
};

export default ProtectedRoute