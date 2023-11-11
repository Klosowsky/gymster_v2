import  '../styles/style.css';
import  '../styles/trainings.css';
import Header from "./Header";
import React, { useState } from 'react';
import { C_API_BASE_URL } from '../global/Api';




const UserPanel = () => {
    const [file,setFile] = useState(null);
    const [email,setEmail] = useState('');
    const [headerKey, setHeaderKey] = useState(0);

    const handleFileChange = (event) => {
        setFile(event.target.files[0]);
    }

    const handleEmailChange = (event) => {
      
      setEmail(event.target.value);
    }

    const refreshHeader = () => {
      setHeaderKey(prevKey => prevKey + 1);
    };

    const handleUpload = (event) => {
      console.log("1");
        //const file = event.target.files[0];
        console.log("2");
        const formData = new FormData();
        console.log("3");
        formData.append('file', file);
        console.log("4");
        formData.append('email', email);
        console.log(formData);
        const storedToken = localStorage.getItem("token");
        console.log("5");
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
            console.log('Image uploaded successfully');
            
          } else {
            console.error('Error uploading image');
          }
        })
        .catch(error => {
          console.error('Error uploading image:', error);
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
                           
                        </div>
                        <div className="success-message">
                           
                        </div>
                    </div>

                </div>

            </div>

        </section>

    )

}

export default UserPanel;