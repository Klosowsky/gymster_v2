import { useParams,useNavigate } from 'react-router-dom';
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
    const defaultJson = {
      "trainingTitle" : "",
      "trainingDesc" : "",
      "trainingDetails" :[],
      "likes" : "0",
      "dislikes" :"0",
      "photoUrl" : "default_profile.jpg",
      "username" : "",
    }
    const storedToken = localStorage.getItem("token");
    const navigate = useNavigate();
    const [trainingData, setTrainingData] = useState(defaultJson);
    const [rating, setRating] = useState("0");
    const { trainingId } = useParams();
    const [imageSrc, setImageSrc] = useState(null);
    
    useEffect(() => {
        fetchTraining();
    }, []);

    function fetchTraining(){
        try {
            fetch(C_API_BASE_URL+'/training/print?id='+trainingId, {
              method: 'GET',
              headers: {
                'Authorization': `Bearer ${storedToken}`,
              },
            })
              .then((response) => {
                if (response.ok) {
                  return response.json();
                }
                else {
                  return null;
                }
              })
              .then((data) => {
                if (data) {
                  const binaryString = atob(data.profilePhoto);
                  const bytes = new Uint8Array(binaryString.length);
                  for (let i = 0; i < binaryString.length; i++) {
                      bytes[i] = binaryString.charCodeAt(i);
                  }
                  const blob = new Blob([bytes], { type: 'application/octet-stream' });
                  const imageUrl = URL.createObjectURL(blob);
                  setImageSrc(imageUrl);
                  setTrainingData(data);
                } 
              })
              .catch((error) => {
                console.log("Server error! " +error);
              });
          } catch (err) {
            console.log("Server error! "+err);
         }
         refreshRatings();
    }

    const rateTraining = (rate) => {
      const ratingDTO = {
        "trainingId" : trainingId,
        "rating" : rate,
      }
      try {
        fetch(C_API_BASE_URL+'/rating/set', {
          method: 'POST',
          headers: {
            'Authorization': `Bearer ${storedToken}`,
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(ratingDTO),
        })
          .then((response) => {
            if (response.ok) {
              refreshRatings();
              return null;
            }
            else {
              return null;
            }
          })
          .catch((error) => {
            console.log("Server error! " +error);
          });
      } catch (err) {
        console.log("Server error! "+err);
      }
    };

    function refreshRatings(){
      try {
        fetch(C_API_BASE_URL+'/rating/check?trainingId='+trainingId, {
          method: 'GET',
          headers: {
            'Authorization': `Bearer ${storedToken}`,
          },
        })
          .then((response) => {
            if (response.ok) {
              return response.json();
            }
            else {
              return null;
            }
          })
          .then((data) => {
            if (data) {
                setTrainingData(prevData => ({ ...prevData, likes: data.likes, dislikes: data.dislikes }));
                setRating(data.rating);
            } 
          })
          .catch((error) => {
            console.log("Server error! " +error);
          });
      } catch (err) {
        console.log("Server error! "+err);
      }
    }

    function defeteTraining(){

      // API CALL
      try {
        fetch(C_API_BASE_URL+'/training/delete?trainingId='+trainingId, {
          method: 'DELETE',
          headers: {
            'Authorization': `Bearer ${storedToken}`,
          },
        })
          .then((response) => {
            if (response.ok) {
              navigate("/")
            }
            else {
              alert("Cannot delete training!");
            }
          })
          .catch((error) => {
            console.log("Server error! " +error);
          });
      } catch (err) {
        console.log("Server error! "+err);
      }
    }


return (
    <section>
        <Header />
        <div className="training-details-container">
            <div className="training-general-info-container">           
                <div className="training-general-info">
                    <div className="training-item-usr" >
                      {trainingData.username === localStorage.getItem("username") ? (<i className="fa-solid fa-trash fa-2xl" onClick={defeteTraining}></i>) : null}
                    </div>
                    <div className="training-item-title">
                      {trainingData.trainingTitle}
                    </div>
                    <div className="training-item-descr">
                      {trainingData.trainingDesc}
                    </div>
                    <div className="training-item-rate">
                        <div className="likes" onClick={rateTraining.bind(null, 1)}>
                            <i className="fa-solid fa-thumbs-up fa-xl" style={{
                              fontWeight: rating === 1 ? 1000 : 100,
                              letterSpacing: '5px',
                            }}> { trainingData.likes}</i>
                        </div>
                        <div className="dislikes" onClick={rateTraining.bind(null, -1)}>
                            <i className="fa-solid fa-thumbs-down fa-xl" style={{
                              fontWeight: rating === -1 ? 1000 : 100,
                              letterSpacing: '5px',
                            }}> { trainingData.dislikes}</i>
                        </div>
                    </div>
                    <div className="training-photo-position">
                        <div className="training-user-photo">
                          <img src={imageSrc} className="user-profile-img" alt="IMAGE" />
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