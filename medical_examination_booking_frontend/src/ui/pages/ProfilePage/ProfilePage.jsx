import React, {useState} from "react";
import {Box, Typography, Paper, CircularProgress, Button} from "@mui/material";
import { useUser } from "../../../context/UserContext.jsx";
import EditPatientDialog from "../../components/patients/EditPatientDialog/EditPatientDialog.jsx";
import EditDoctorDialog from "../../components/doctors/EditDoctorDialog/EditDoctorDialog.jsx";
import TermDialog from "../../components/doctors/TermDialog/TermDialog.jsx";
import {useKeycloak} from "@react-keycloak/web";
import useDoctors from "../../../hooks/useDoctors.js";
import usePatients from "../../../hooks/usePatients.js";

const ProfilePage = () => {
    const { user } = useUser();
    const [editDialogOpen, setEditDialogOpen] = useState(false);
    const [termDialogOpen, setTermDialogOpen] = useState(false);
    const { keycloak, initialized } = useKeycloak();

    const { doctors, onEdit: onEditDoctor } = useDoctors();
    const { patients, onEdit: onEditPatient } = usePatients();



    if (!user) {
        return (
            <Box sx={{ display: "flex", justifyContent: "center", mt: 4 }}>
                <CircularProgress />
                <Typography sx={{ ml: 2 }}>Loading user...</Typography>
            </Box>
        );
    }


    const isPatient = user.roles?.includes("PATIENT");
    const isDoctor = user.roles?.includes("DOCTOR");


    const [selectedUser, setSelectedUser] = useState(user);
    const [onEditHandler, setOnEditHandler] = useState(null);

    React.useEffect(() => {
        if (isDoctor && doctors.length > 0) {
            setSelectedUser(doctors.find(d => d.username === user.username));
            setOnEditHandler(() => onEditDoctor);
        } else if (isPatient && patients.length > 0) {
            setSelectedUser(patients.find(p => p.username === user.username));
            setOnEditHandler(() => onEditPatient);
        }
    }, [doctors, patients, isDoctor, isPatient, user.username, onEditDoctor, onEditPatient]);


    return (
        <Box sx={{ display: "flex", justifyContent: "center", mt: 4 }}>

            {keycloak.authenticated ? (
                <>
                    <Button variant="outlined" color="secondary" onClick={() => keycloak.logout({ redirectUri: 'http://localhost:3000' })}>
                        Log Out
                    </Button>
                </>
            ) : (
                <Button variant="contained" color="primary" onClick={() => keycloak.login()}>
                    Log In
                </Button>

            )}

            <Paper sx={{ p: 4, width: "400px" }} elevation={3}>
                <Typography variant="h5" gutterBottom>
                    Profile Information
                </Typography>

                <Box display="flex" justifyContent="center" mt={2}>
                    <img
                        src={user.photoUrl}
                        alt={`${user.name} ${user.surname}`}
                        style={{
                            width: 200,
                            height: 290,
                            borderRadius: 8,
                            boxShadow: "0 4px 8px rgba(0,0,0,0.2)",
                            objectFit: "cover",
                        }}
                    />
                </Box>

                <h3>{user.firstName} {user.lastName}</h3>
                <Typography><b>Username:</b> {user.username}</Typography>
                {/*<Typography><b>Roles:</b> {user.roles} </Typography>*/}
                <Typography><b>Email:</b> {user.email}</Typography>
                {(isPatient) &&(
                    <Typography><b>Age:</b> {user.age}</Typography>
                )}

                {(isDoctor) &&(
                    <Typography><b>Price:</b> ${user.price}</Typography>
                )}

                {isDoctor && (
                    <Box sx={{ mt: 2 }}>
                        <Button
                            variant="contained"
                            color="primary"
                            onClick={() => setTermDialogOpen(true)}
                        >
                            Add Term
                        </Button>
                    </Box>
                )}

                {isDoctor && (
                <TermDialog
                    open={termDialogOpen}
                    onClose={() => setTermDialogOpen(false)}
                    doctorUsername={user.username}
                />
                )}

                { (isPatient || isDoctor) && (
                    <Button
                        variant="contained"
                        color="primary"
                        sx={{ mt: 2 }}
                        onClick={() => setEditDialogOpen(true)}
                    >
                        Edit Profile
                    </Button>
                )}

                {isPatient && selectedUser && (
                    <EditPatientDialog
                        open={editDialogOpen}
                        onClose={() => setEditDialogOpen(false)}
                        patient={selectedUser}
                        onEdit={onEditHandler}
                    />
                )}
                {isDoctor && selectedUser && (
                    <EditDoctorDialog
                        open={editDialogOpen}
                        onClose={() => setEditDialogOpen(false)}
                        doctor={selectedUser}
                        onEdit={onEditHandler}
                    />
                )}

            </Paper>
        </Box>
    );
};

export default ProfilePage;
