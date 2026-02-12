import React, {useState} from 'react';
import {Box, Button, CircularProgress} from "@mui/material";
import DoctorsGrid from "../../components/doctors/DoctorsGrid/DoctorsGrid.jsx";
import useDoctors from "../../../hooks/useDoctors.js";
import AddDoctorDialog from "../../components/doctors/AddDoctorDialog/AddDoctorDialog.jsx";
import { useKeycloak } from '@react-keycloak/web';
import axios from 'axios';
import {useUser} from "../../../context/UserContext.jsx";
import {useNavigate} from "react-router";

const DoctorsPage = () => {
    const {doctors, loading, onAdd, onEdit, onDelete} = useDoctors();
    const [addDoctorDialogOpen, setAddDoctorDialogOpen] = useState(false);
    const { keycloak, initialized } = useKeycloak();
    const navigate = useNavigate();

    const tocken = keycloak.token
    localStorage.setItem("access_token", keycloak.token);
    console.log(tocken)


    const fetchUserData = async () => {
                navigate("/profile");
    };


    return (
        <>
            <Box className="products-box">
                {loading && (
                    <Box className="progress-box">
                        <CircularProgress/>
                    </Box>
                )}
                {!loading &&
                    <>
                        <Box sx={{display: "flex", justifyContent: "flex-end", mb: 2}}>

                            {keycloak.authenticated ? (
                                <>
                                <Button variant="outlined" color="secondary" onClick={() => keycloak.logout({ redirectUri: 'http://localhost:3000' })}>
                                    Log Out
                                </Button>
                                <Button variant="outlined" color="info" onClick={fetchUserData}>
                                    Get My Info
                                </Button>
                                </>
                            ) : (
                                <Button variant="contained" color="primary" onClick={() => keycloak.login()}>
                                    Log In
                                </Button>

                            )}

                            <Button variant="contained" color="primary" onClick={() => setAddDoctorDialogOpen(true)}>
                                Add Doctor
                            </Button>
                        </Box>
                        <DoctorsGrid doctors={doctors} onEdit={onEdit} onDelete={onDelete}/>
                    </>}
            </Box>
            <AddDoctorDialog
                open={addDoctorDialogOpen}
                onClose={() => setAddDoctorDialogOpen(false)}
                onAdd={onAdd}
            />
        </>
    );
};

export default DoctorsPage;