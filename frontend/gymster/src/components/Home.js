import {  Link } from "react-router-dom";
import { useState,useEffect } from "react";
import  '../styles/style.css';
import  '../styles/trainings.css';
import Header from "./Header";
import { C_API_BASE_URL } from '../global/Api';

function TrainingItem({ training }) {
    const username = localStorage.getItem("username");
    const [imageSrc, setImageSrc] = useState(null);

    useEffect(() => {
        const binaryString = atob(training.user.userDetails.profilePhoto);
        const bytes = new Uint8Array(binaryString.length);
        for (let i = 0; i < binaryString.length; i++) {
            bytes[i] = binaryString.charCodeAt(i);
        }
        const blob = new Blob([bytes], { type: 'application/octet-stream' });
        const imageUrl = URL.createObjectURL(blob);
        setImageSrc(imageUrl);
    }, [training]);

    return (
        <Link to={`/training/${training.id}`}>
            <div className="training-item">
                <div className="training-item-usr">
                    { username===training.user.username ? 
                    (<i className="fa-solid fa-user fa-xl"></i>)
                    : (null)
                    }                   
                </div>
                <div className="training-item-title">
                    {training.title}
                </div>
                <div className="training-item-descr">
                    {training.description}
                </div>
                <div className="training-item-rate">
                    <div className="likes">
                        <i className="fa-solid fa-thumbs-up fa-2xl"></i>
                        <p> {training.likes}</p>
                    </div>
                    <div className="dislikes">
                        <i className="fa-solid fa-thumbs-down fa-2xl"></i>
                        <p> {training.dislikes}</p>
                    </div>
                </div>
                <div className="training-photo-position">
                    <div className="training-user-photo">    
                    {imageSrc && <img src={imageSrc} className="user-profile-img" alt="Image" />}
                    </div>
                </div>
                <div className="training-username"> <p>{training.user.username}</p>
                </div>
            </div>
            </Link>
    );
}

const Home = () => {
    const [trainings, setTrainings] = useState([]);
    const [searchText, setSearchText] = useState('');

    const handleSearchChange = (e) => {
        setSearchText(e.target.value); 
    };
  
    useEffect(() => {
        fetchTrainings();
   
    }, []);

    function fetchTrainings(){
        try {
            const storedToken = localStorage.getItem("token");
            fetch(C_API_BASE_URL+'/training/getallbyname?trainingName='+searchText, {
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
                  alert('Server error!');
                  return null;
                }
              })
              .then((data) => {
                if (data) {
                    setTrainings(data);
                } 
              })
              .catch((error) => {
                alert('Server error! '+error);
              });
          } catch (err) {
            alert('Server error! ' +err);
         }
    }

    return (
        <section>
            <Header />
            <div className="main-container">
                <div className="main-tool-bar">
                    <div className="search-training-bar">
                        <input className="search-training-input" onChange={handleSearchChange} id="search" type="text" placeholder="Search..."></input> 
                        <i className="fa-solid fa-magnifying-glass fa-2xl" onClick={fetchTrainings}></i>
                    </div>    
                    <div className="add-training-btn" >
                        <Link to="/addtraining">
                            <i className="fa-solid fa-plus fa-2xl"></i>
                            <p className="p-add-workout">Add training</p>
                        </Link>
                    </div>
                </div>
                <div className="trainings-main-container">
                    <section className="trainings-sec">                      
                        {trainings.map((training) => (                    
                            <TrainingItem  training={training} />
                        ))}                                  
                    </section>
                </div>    
            </div>
        </section>
    )
}

export default Home