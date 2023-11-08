import { useParams } from 'react-router-dom';



const Training = () => {

    const { trainingId } = useParams();



return (


    <section>
        Training ID: {trainingId}
    </section>
)




}

export default Training;