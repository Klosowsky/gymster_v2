import {  Link } from "react-router-dom";
import { useState,useEffect } from "react";
import  '../styles/style.css';
import  '../styles/trainings.css';
import Header from "./Header";
import { C_API_BASE_URL } from '../global/Api';
import {globalMessages}  from '../global/Messages'




function TrainingItem({ training }) {
    console.log('aa ' + JSON.stringify(training) );
    const username = localStorage.getItem("username");
    return (


        <Link to={`/training/${training.id}`}>
            <div className="training-item">
                <div className="training-item-usr">
                    { username===training.user.username ? 
                    (<i class="fa-solid fa-user fa-xl"></i>)
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
                    <img src="/uploads/Will_Smith.jpg" className="user-profile-img" alt="IMAGE" />
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
    /*const testTraining = JSON.stringify({
        trainings : [
            {
            trainingId : 1,
            trainingTitle: 'Test training',
            getTrainingDescription: "Example training description",
            likes: 321,
            dislikes : 123,
            username : "testUser",
            photoUrl : "../public/uploads/Will_Smith.jpg",
            },
            {
            trainingId : 2,
            trainingTitle: 'Test training 2',
            getTrainingDescription: "Example training description 2",
            likes: 3211,
            dislikes : 1231,
            username : "matias",
            photoUrl : "../public/uploads/Will_Smith.jpg",
            }
        ]
    });
    const jsonObject = JSON.parse(testTraining);
    console.log("ee");
    setTrainings(jsonObject.trainings);*/
}, []);

    function fetchTrainings(){





        try {
            const storedToken = localStorage.getItem("token");
            const searchData = {
                trainingName: searchText,
              };
            fetch(C_API_BASE_URL+'/training/getallbyname', {
              method: 'POST',
              headers: {
                'Authorization': `Bearer ${storedToken}`,
                'Content-Type': 'application/json',
              },
              body: JSON.stringify(searchData),
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
                
                   setTrainings(data);
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







        
        /*console.log("title like: "+searchText);
        const custom = JSON.stringify({
            trainings : [
                {
                trainingId : 1,
                trainingTitle: 'Test traininggggggg',
                getTrainingDescription: "Example training description",
                likes: 321,
                dislikes : 123,
                username : "testUser",
                photoUrl : "../public/uploads/Will_Smith.jpg",
                }
            ]
        });
    const jsonObjectt = JSON.parse(custom);
    console.log("aaaaaaa");
    setTrainings(jsonObjectt.trainings);*/
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
                    
                    <div className="add-training-btn" onclick="location.href='/addtraining';" >
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

                <template id="training-template">
                <a href="/">
                    <div className="training-item">
                        <div className="training-item-usr">
                        </div>
                        <div className="training-item-title">
                        </div>
                        <div className="training-item-descr">
                        </div>
                        <div className="training-item-rate">
                            <div className="likes">
                                <i className="fa-solid fa-thumbs-up fa-2xl"></i>
                            </div>
                            <div className="dislikes">
                                <i className="fa-solid fa-thumbs-down fa-2xl"></i>
                            </div>
                        </div>
                        <div className="training-photo-position">
                            <div className="training-user-photo">
                                
                            </div>
                        </div>
                        <div className="training-username">
                        </div>
                    </div></a>
                </template>


            </div>
        </section>
    )
}

export default Home