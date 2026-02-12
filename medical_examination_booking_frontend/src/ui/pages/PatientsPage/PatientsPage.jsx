import React, {useState} from 'react';
import {Box, Button, CircularProgress} from "@mui/material";
import usePatients from "../../../hooks/usePatients.js";
import PatientsGrid from "../../components/patients/PatientsGrid/PatientsGrid.jsx";
import AddPatientDialog from "../../components/patients/AddPatientDialog/AddPatientDialog.jsx";
import {useKeycloak} from "@react-keycloak/web";
import {useNavigate} from "react-router";

const PatientsPage = () => {
    const {patients, loading, onAdd, onEdit, onDelete} = usePatients();
    const [addPatientDialogOpen, setAddPatientDialogOpen] = useState(false);
    const { keycloak, initialized } = useKeycloak();
    const navigate = useNavigate();

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

                            <Button variant="contained" color="primary" onClick={() => setAddPatientDialogOpen(true)}>
                                Add Patient
                            </Button>
                        </Box>
                        <PatientsGrid patients={patients} onEdit={onEdit} onDelete={onDelete}/>
                    </>}
            </Box>
            <AddPatientDialog
                open={addPatientDialogOpen}
                onClose={() => setAddPatientDialogOpen(false)}
                onAdd={onAdd}
            />
        </>
    );
};

export default PatientsPage;