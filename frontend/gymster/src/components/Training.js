import { useParams } from 'react-router-dom';
import  '../styles/style.css';
import  '../styles/trainings.css';
import Header from "./Header";
import React, { useState, useEffect } from 'react';
import { C_API_BASE_URL } from '../global/Api';


function TrainingDetails({ trainingDetails }) {
  return (
    <>
      {trainingDetails.map(trainingDetail => (
        <div className="training-day-box"  key={trainingDetail.id}>
          <div className="training-box-day-number">
            <p>Day {trainingDetail.id}</p>
          </div>
          <div className="training-box-exercise-list">
              {trainingDetail.inputs.map(input => (
                <div className="training-box-exercise" key = {input.id}>
                  <p className="p-exercise-name">{input.exerciseName}</p>
                  <p className="p-exercise-details">{input.series} series</p>
                    <p className="p-exercise-details">{input.reps} reps</p>
                  </div>
              ))}
            </div>
          </div>

      ))}
    </>
  );
}


const Training = () => {

    const { trainingId } = useParams();
    const defaultJson = {
      "trainingTitle" : "",
      "trainingDesc" : "",
      "trainingDetails" :[],
      "likes" : "0",
      "dislikes" :"0",
      "photoUrl" : "default_profile.jpg",
      "username" : "",
    }

    const [trainingData, setTrainingData] = useState(defaultJson);


    useEffect(() => {
        fetchTrainings();
   
    }, []);

    function fetchTrainings(){
      console.log('trrr')

        try {
            const storedToken = localStorage.getItem("token");
            fetch(C_API_BASE_URL+'/training/print?id='+trainingId, {
              method: 'GET',
              headers: {
                'Authorization': `Bearer ${storedToken}`,
              },
            })
              .then((response) => {
                if (response.ok) {
                  console.log("Response ok!");
                  return response.json();
                }
                else {
                  //setErrMsg(globalMessages.internalServerError);
                  console.log("Response not ok!1 " );
                  return null;
                }
              })
              .then((data) => {
                if (data) {
                    console.log("yyyyyyyy ");
                    console.log("m "+ JSON.stringify(data));
                
                    setTrainingData(data);
                } 
              })
              .catch((error) => {
                console.log("Response not ok!2 " +error);
                //setErrMsg(error);
              });
          } catch (err) {
            console.log("Response not ok!3 "+err);
           // setErrMsg(err);
         }

    }



return (


    <section>
        <Header />



        <div className="training-details-container">
            <div className="training-general-info-container">
              
                <div className="training-general-info">

                    <div className="training-item-usr" >
                       
                            <i className="fa-solid fa-trash fa-2xl" onClick="location.href='/deletetraining/<?= $training->getTrainingId()?>';"></i>
                    </div>
                    <div className="training-item-title">
                      {trainingData.trainingTitle}
                    </div>
                    <div className="training-item-descr">
                      {trainingData.trainingDesc}
                    </div>
                    <div className="training-item-rate">
                        <div className="likes">
                            <i className="fa-solid fa-thumbs-up fa-xl" > { trainingData.likes}</i>{/*</div>style="font-weight: 150; letter-spacing: 5px"*/}
                        </div>
                        <div className="dislikes">
                            <i className="fa-solid fa-thumbs-down fa-xl" > { trainingData.dislikes}</i>{/* style="font-weight: 150; letter-spacing: 5px" */}
                        </div>
                    </div>
                    <div className="training-photo-position">
                        <div className="training-user-photo">
                          <img src={`/uploads/${trainingData.photoUrl}`} className="user-profile-img" alt="IMAGE" />
                        </div>
                    </div>
                    <div className="training-username"> <p>{trainingData.username}</p>
                    </div>
                </div>
            </div>
            <div className="training-days-container">

              <TrainingDetails trainingDetails={trainingData.trainingDetails} />

            </div>

          </div>
        
</section>

)




}

export default Training;