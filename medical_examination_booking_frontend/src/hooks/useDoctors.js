import {useCallback, useEffect, useState} from "react";
import doctorRepository from "../repository/doctorRepository.js";
import keycloak from "../keycloak.js";

const initialState = {
    "doctors": [],
    "loading": true,
};

const useDoctors = () => {
    const [state, setState] = useState(initialState);

    const fetchDoctors = useCallback(() => {
        setState(initialState);
        doctorRepository
            .findAll()
            .then((response) => {
                setState({
                    "doctors": response.data,
                    "loading": false,
                });
            })
            .catch((error) => console.log(error));
    }, []);

    const onAdd = useCallback((data) => {
        doctorRepository
            .add(data)
            .then(() => {
                console.log("Successfully added a new doctor.");
                fetchDoctors();
            })
            .catch((error) => console.log(error));
    }, [fetchDoctors]);

    const onEdit = useCallback((id, data) => {
        doctorRepository
            .edit(id, data, keycloak.token)
            .then(() => {
                console.log(`Successfully edited the doctor with ID ${id}.`);
                fetchDoctors();
            })
            .catch((error) => console.log(error));
    }, [fetchDoctors]);

    const onDelete = useCallback((id) => {
        doctorRepository
            .delete(id)
            .then(() => {
                console.log(`Successfully deleted the doctor with ID ${id}.`);
                fetchDoctors();
            })
            .catch((error) => console.log(error));
    }, [fetchDoctors]);

    useEffect(() => {
        fetchDoctors();
    }, [fetchDoctors]);

    return {...state, onAdd: onAdd, onEdit: onEdit, onDelete: onDelete};
};

export default useDoctors;