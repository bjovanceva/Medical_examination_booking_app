import {useCallback, useEffect, useState} from "react";
import patientRepository from "../repository/patientRepository.js";
import keycloak from "../keycloak.js";

const initialState = {
    "patients": [],
    "loading": true,
};

const usePatients = () => {
    const [state, setState] = useState(initialState);

    const fetchPatients = useCallback(() => {
        setState(initialState);
        patientRepository
            .findAll(keycloak.token)
            .then((response) => {
                // console.log(keycloak.token)
                // console.log("Later parsed")
                // console.log(keycloak.tokenParsed)
                setState({
                    "patients": response.data,
                    "loading": false,
                });
            })
            .catch((error) => console.log(error));
    }, []);

    const onAdd = useCallback((data) => {
        patientRepository
            .add(data)
            .then(() => {
                console.log("Successfully added a new paient.");
                fetchPatients();
            })
            .catch((error) => console.log(error));
    }, [fetchPatients]);

    const onEdit = useCallback((id, data) => {
        patientRepository
            .edit(id, data, keycloak.token)
            .then(() => {
                console.log(`Successfully edited the patient with ID ${id}.`);
                fetchPatients();
            })
            .catch((error) => console.log(error));
    }, [fetchPatients]);

    const onDelete = useCallback((id) => {
        patientRepository
            .delete(id)
            .then(() => {
                console.log(`Successfully deleted the patient with ID ${id}.`);
                fetchPatients();
            })
            .catch((error) => console.log(error));
    }, [fetchPatients]);

    useEffect(() => {
        fetchPatients();
    }, [fetchPatients]);

    return {...state, onAdd: onAdd, onEdit: onEdit, onDelete: onDelete};
};

export default usePatients;