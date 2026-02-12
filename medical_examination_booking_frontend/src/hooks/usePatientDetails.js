import {useEffect, useState} from "react";
import patientRepository from "../repository/patientRepository.js";
import doctorRepository from "../repository/doctorRepository.js";

const usePatientDetails = (id) => {
    const [state, setState] = useState({
        "patient": null,
        "doctor": null,
    });

    useEffect(() => {
        patientRepository
            .findById(id)
            .then((response) => {
                setState(prevState => ({...prevState, "patient": response.data}));
                doctorRepository
                    .findById(response.data.doctorId)
                    .then((response) => {
                        setState(prevState => ({...prevState, "doctor": response.data}));
                    })
                    .catch((error) => console.log(error))
            })
            .catch((error) => console.log(error));
    }, [id]);

    return state;
};

export default usePatientDetails;