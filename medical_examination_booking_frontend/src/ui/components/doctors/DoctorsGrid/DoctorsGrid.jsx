import React from 'react';
import DoctorGrid from "../DoctorCard/DoctorCard.jsx";
import {Grid} from "@mui/material";
import DoctorCard from "../DoctorCard/DoctorCard.jsx";

const DoctorsGrid = ({doctors, onEdit, onDelete}) => {
    return (
        <Grid container spacing={{xs: 2, md: 3}}>
            {doctors.map((doctor) => (
                <Grid key={doctor.id} size={{xs: 12, sm: 6, md: 4, lg: 3}}>
                    <DoctorCard doctor={doctor} onEdit={onEdit} onDelete={onDelete}/>
                </Grid>
            ))}
        </Grid>
    );
};

export default DoctorsGrid;