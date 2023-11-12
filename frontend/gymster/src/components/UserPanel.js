import  '../styles/style.css';
import  '../styles/trainings.css';
import Header from "./Header";
import React, { useState } from 'react';
import { C_API_BASE_URL } from '../global/Api';



const UserPanel = () => {
    const [file,setFile] = useState(null);
    const [email,setEmail] = useState('');
    const [headerKey, setHeaderKey] = useState(0);
    const [errorMsg,setErrorMsg] = useState('');
    const [succesMsg,setSuccesMsg] = useState('');

    const handleFileChange = (event) => {
        setFile(event.target.files[0]);
    }

    const handleEmailChange = (event) => {
      
      setEmail(event.target.value);
    }

    const refreshHeader = () => {
      setHeaderKey(prevKey => prevKey + 1);
    };

    const handleUpload = () => {
        const formData = new FormData();
        formData.append('file', file);
        formData.append('email', email);
        const storedToken = localStorage.getItem("token");
        fetch(C_API_BASE_URL+'/userdetails/update', {
          method: 'POST',
          headers: {
            'Authorization': `Bearer ${storedToken}`,
          },
          body: formData
        })
        .then(response => {
          if (response.ok) {
            refreshHeader();
            setSuccesMsg('Details updated!');
          } else {
            setErrorMsg('Cannot update user deails!');
          }
        })
        .catch(error => {
          setErrorMsg('Cannot update user deails!');
        });
      };

    return(
        <section>
            <Header key={headerKey}/>
            <div className="simple-container">
                <div className="panel-details-container">
                    <div  className="panel-details-form" >
                        <input type="file" onChange={handleFileChange}/>
                        <input name="email" onChange={handleEmailChange} type="text" placeholder="email" />
                        <button type="submit" onClick={handleUpload}>Update</button>
                        <div className="error-message">
                            {errorMsg}
                        </div>
                        <div className="success-message">
                            {succesMsg}
                        </div>
                    </div>
                </div>
            </div>
        </section>
    )
}

export default UserPanel;