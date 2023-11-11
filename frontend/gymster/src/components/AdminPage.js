import  '../styles/style.css';
import  '../styles/trainings.css';
import Header from "./Header";
import React, { useState } from 'react';
import { C_API_BASE_URL } from '../global/Api';



const AdminPage = () =>{

    const [errorMsg,setErrMsg] = useState('');
    const [succesMsg,setSuccesMsg] = useState('');
    const [exerciseName,setExerciseName] = useState('');

    const handleExerciseName = (e) => {
        setExerciseName(e.target.value); 
    }

    function uploadExercise(){
        try {
            const storedToken = localStorage.getItem("token");
            fetch(C_API_BASE_URL+'/exercise/add', {
              method: 'POST',
              headers: {
                'Authorization': `Bearer ${storedToken}`,
                'Content-Type': 'application/json',
              },
              body: JSON.stringify({ "name" : exerciseName }),
            })
              .then((response) => {
                if (response.ok) {
                  setSuccesMsg("Exercise added!");
                  setErrMsg("");
                }
                else {
                  setErrMsg("Cannot add exercise!");
                  setSuccesMsg("");
                }
              })
              .catch((error) => {
                setErrMsg("Cannot add exercise!");
                setSuccesMsg("");
                console.log("Server error! " +error);
              });
          } catch (err) {
            setErrMsg("Cannot add exercise!");
            setSuccesMsg("");
            console.log("Server error! "+err);
          }
    }

    return(
        <section>
            <Header/>
            <div className="simple-container">
                <div className="panel-details-container">
                    <div className="panel-details-form">
                        <input name="exercise" placeholder="exercise name" onChange={handleExerciseName}/>
                        <button type="submit" onClick={uploadExercise}>Add exercise</button>
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


export default AdminPage;