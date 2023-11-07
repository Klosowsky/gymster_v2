import Header from "./Header";
import  '../styles/style.css';
import  '../styles/trainings.css';
import React, { useState } from 'react';


const AddTraining = () => {

  const [formComponents, setFormComponents] = useState([]);
  const [title, setTitle] = useState('');
  const [desc, setDesc] = useState('');

  const exerciseOptions = {             /* TO DO: exercises from database - API call */
    "1": "Exercise1",
    "2": "Exercise2",
    "3": "Exercise3",
  };
  


  const handleTitleChange = (event) => {
    setTitle(event.target.value);
  };

  const handleDescChange = (event) => {
    setDesc(event.target.value);
  };

  const iconStyles = {
    position: 'absolute',
    right: '20px',
  };

  const addComponent = () => {
    const newComponent = {
      id: formComponents.length + 1,
      inputs: [],
    };
    setFormComponents([...formComponents, newComponent]);
  };

  const addInput = (componentId) => {
    const newInput = {
      id: formComponents[componentId - 1].inputs.length + 1,
      series: '',
      reps: '',
      exercise: '',
    };

    const updatedComponents = [...formComponents];
    updatedComponents[componentId - 1].inputs.push(newInput);

    setFormComponents(updatedComponents);
  };

  const handleExerciseChange = (componentId, inputId, value) => {
    console.log('exercise: '+value);
    const updatedComponents = [...formComponents];
    updatedComponents[componentId - 1].inputs[inputId - 1].exercise = value;
    setFormComponents(updatedComponents);
  };

  const handleSeriesChange = (componentId, inputId, value) => {
    console.log('series: '+value);
    const updatedComponents = [...formComponents];
    updatedComponents[componentId - 1].inputs[inputId - 1].series = value;
    setFormComponents(updatedComponents);
  };

  const handleRepsChange = (componentId, inputId, value) => {
    console.log('reps: '+value);
    const updatedComponents = [...formComponents];
    updatedComponents[componentId - 1].inputs[inputId - 1].reps = value;
    setFormComponents(updatedComponents);
  };

  const removeComponent = (componentId) => {
    const updatedComponents = (formComponents.filter((component) => component.id !== componentId))
    .map((component) => {
        if (component.id > componentId) {
          return { ...component, id: component.id - 1 };
        }
        return component;
      });
    setFormComponents(updatedComponents);
  };

  const removeInput = (componentId, inputId) => {
    const updatedComponents = [...formComponents];
    updatedComponents[componentId - 1].inputs = updatedComponents[componentId - 1].inputs.filter(
      (input) => input.id !== inputId
    ).map((input) => {
        if (input.id > inputId) {
          return { ...input, id: input.id - 1 };
        }
        return input;
      });
    setFormComponents(updatedComponents);
  };

  const handleSubmit = () => {

    const initialData = {
        trainingTitle : title,
        trainingDesc : desc,
        trainingDetails : formComponents,
      };
      console.log(JSON.stringify(initialData));

      // TO DO: CALL API and save training, perhaps some validations?

  };

  
    return (

        <section> 
            <Header />
            
                <div className="training-details-container">
                    <div className="add-training-general-info-container">
                        <div className="add-training-title-box" id="trng-title-box">
                            <div className="add-training-title-header">
                                <p>Title</p>
                            </div>
                            <div className="add-training-title-content">
                                <textarea value={title} onChange={handleTitleChange} id="trng-title" maxLength="40" className="new-training-title" name="trng-title" placeholder="Your title..."></textarea>
                            </div>
                        </div>

                        <div className="add-training-desc-box" id="trng-desc-box">
                            <div className="add-training-desc-header">
                                <p>Description</p>
                            </div>
                            <div className="add-training-desc-content">
                                <textarea value={desc} onChange={handleDescChange} id="trng-desc" maxLength="100" className="new-training-desc" name="trng-desc" placeholder="Your description..."></textarea>
                            </div>
                        </div>

                        <div className="add-training-buttons-box">
                            <div className="upload-training" onClick={handleSubmit}>
                                <i className="fa-sharp fa-solid fa-arrow-up-from-bracket fa-2xl"></i>
                                <p className="p-add-workout">Upload training</p>
                            </div>

                        </div>
                    </div>
                    
                    <div className="training-add-days-container" id="trng-days-cont"> 
                    
                        {formComponents.map((component) => (
                            
                                <div className="training-day-box" id="add-day-1" key={component.id}>
                                        <div className="training-box-day-number">
                                            <p>Day {component.id}</p>
                                            <i className="fa-solid fa-trash fa-xl" id="delete-trng-day-icon" style={iconStyles} onClick={() => removeComponent(component.id)}></i>
                                        </div>
                                        <div className="training-box-add-exercise-list" id="trn-day-1">
                                        {component.inputs.map((input) => (
                                            <div key={input.id}>
                                                <div className="add-exercise-details" id="'+newExerciseId+'"> 
                                                    <div className="my-inline-pos"> 
                                                        <select className="select-add-exercise" value={input.exercise} onChange={(e) => handleExerciseChange(component.id, input.id, e.target.value)}> 
                                                            <option value="" disabled hidden >Choose exercise</option> 
                                                            {Object.entries(exerciseOptions).map(([key, value]) => (
                                                                <option key={key} value={key}>
                                                                    {value}
                                                                </option>
                                                            ))}
                                                        </select> 
                                                        <i className="fa-solid fa-trash fa-2xl" id="teeeest" onClick={() => removeInput(component.id, input.id)}></i> 
                                                    </div> 
                                                    <div className="my-inline-pos"> 
                                                    <p>Series:</p> 
                                                        <input value={input.series} onChange={(e) => handleSeriesChange(component.id, input.id, e.target.value)} type="number" min="1" max="20" placeholder="Series" /> 
                                                    </div> 
                                                    <div className="my-inline-pos"> 
                                                        <p>Reps:</p> 
                                                        <input value={input.reps} onChange={(e) => handleRepsChange(component.id, input.id, e.target.value)} type="number" min="1" max="50" placeholder="Reps"/> 
                                                    </div> 
                                                </div>

                                        </div>
                                        ))}

                                            <div id="add-exe-btn-d1" className="add-exercise-btn" onClick={() => addInput(component.id)}>
                                                <i className="fa-solid fa-plus fa-2xl"></i>
                                                <p className="p-add-workout">Add exercise</p>
                                            </div>

                                        </div>
                                    </div>

                        ))}
                        <div id="add-tng-day-btn" className="add-training-day-btn" onClick={addComponent} >
                                    <i className="fa-solid fa-plus fa-2xl"></i>
                        </div>
                    </div>
                </div>
            </section>
    )
}

export default AddTraining;