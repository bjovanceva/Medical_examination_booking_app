import React from 'react';
import {Grid} from "@mui/material";
import PatientCard from "../PatientCard/PatientCard.jsx";

const PatientsGrid = ({patients, onEdit, onDelete}) => {
    return (
        <Grid container spacing={{xs: 2, md: 3}}>
            {patients.map((patient) => (
                <Grid key={patient.id} size={{xs: 12, sm: 6, md: 4, lg: 3}}>
                    <PatientCard patient={patient} onEdit={onEdit} onDelete={onDelete}/>
                </Grid>
            ))}
        </Grid>
    );
};

export default PatientsGrid;