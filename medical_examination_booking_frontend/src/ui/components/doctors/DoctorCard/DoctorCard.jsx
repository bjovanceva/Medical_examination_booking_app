import React, {useState} from 'react';
import {Box, Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import {useNavigate} from "react-router";
import InfoIcon from '@mui/icons-material/Info';
import SaveIcon from '@mui/icons-material/Save';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import EditDoctorDialog from "../EditDoctorDialog/EditDoctorDialog.jsx";
import ReserveDialog from "../ReserveDialog/ReserveDialog.jsx"
import DeleteDoctorDialog from "../DeleteDoctorDialog/DeleteDoctorDialog.jsx";
import {useUser} from "../../../../context/UserContext.jsx";
import {useKeycloak} from "@react-keycloak/web";


const DoctorCard = ({doctor, onEdit, onDelete}) => {
    const navigate = useNavigate();
    // const [editDoctorDialogOpen, setEditDoctorDialogOpen] = useState(false);
    // const [deleteDoctorDialogOpen, setDeleteDoctorDialogOpen] = useState(false);
    const [reserveDialogOpen, setReserveDialogOpen] = useState(false);
    // const {user} = useUser();
    //
    // const isPatient = user?.roles?.includes("PATIENT");
    // const isDoctor = user?.roles?.includes("DOCTOR");

    const { keycloak } = useKeycloak();
    const roles = keycloak.tokenParsed?.realm_access?.roles || [];
    const isPatient = roles.includes("PATIENT");
    const isDoctor = roles.includes("DOCTOR");

    console.log(doctor)

    return (
        <>
            <Card sx={{boxShadow: 3, borderRadius: 2, p: 1}}>
                <CardContent>
                    {/*<Typography variant="h5">{doctor.name}</Typography>*/}
                    {/*<Typography variant="h5">{doctor.surname}</Typography>*/}

                    <Typography variant="h5" sx={{fontWeight: 700, mt: 1}}>
                        <Box component="span" sx={{color: "text.secondary"}}>{doctor.name}</Box>{" "}
                        <Box component="span" sx={{color: "text.secondary"}}>{doctor.surname}</Box>
                    </Typography>

                    <Box display="flex" justifyContent="center" mt={2}>
                        <img
                            src={doctor.photoUrl}
                            alt={`${doctor.name} ${doctor.surname}`}
                            style={{
                                width: 200,
                                height: 290,
                                borderRadius: 8,
                                boxShadow: "0 4px 8px rgba(0,0,0,0.2)",
                                objectFit: "cover",
                            }}
                        />
                    </Box>

                    <Typography variant="h6" color="primary" style={{marginTop: 4}}>{doctor.type}</Typography>
                    <Typography variant="h6" color="" style={{marginTop: 4}}><b>Price: </b>${doctor.examination_price}
                    </Typography>

                    {/*<Typography variant="subtitle2">*/}
                    {/*    Lorem ipsum dolor sit amet, consectetur adipisicing elit.*/}
                    {/*</Typography>*/}
                </CardContent>
                <CardActions sx={{justifyContent: "space-between"}}>
                    {isPatient && (
                        <Button
                            style={{marginLeft: 75}}
                            variant="contained"
                            size="medium"
                            color="primary"
                            startIcon={<SaveIcon/>}
                            onClick={() => setReserveDialogOpen(true)}
                            // onClick={() => navigate(`/doctors/${doctor.id}`)}
                        >
                            Reserve
                        </Button>
                    )}
                    <Box>
                        {/*<Button*/}
                        {/*    size="small"*/}
                        {/*    color="warning"*/}
                        {/*    startIcon={<EditIcon/>}*/}
                        {/*    sx={{mr: "0.25rem"}}*/}
                        {/*    onClick={() => setEditDoctorDialogOpen(true)}*/}
                        {/*>*/}
                        {/*    Edit*/}
                        {/*</Button>*/}
                        {/*<Button*/}
                        {/*    size="small"*/}
                        {/*    color="error"*/}
                        {/*    startIcon={<DeleteIcon/>}*/}
                        {/*    onClick={() => setDeleteDoctorDialogOpen(true)}*/}
                        {/*>*/}
                        {/*    Delete*/}
                        {/*</Button>*/}
                    </Box>
                </CardActions>
            </Card>
            {/*<EditDoctorDialog*/}
            {/*    open={editDoctorDialogOpen}*/}
            {/*    onClose={() => setEditDoctorDialogOpen(false)}*/}
            {/*    doctor={doctor}*/}
            {/*    onEdit={onEdit}*/}
            {/*/>*/}
            {/*<DeleteDoctorDialog*/}
            {/*    open={deleteDoctorDialogOpen}*/}
            {/*    onClose={() => setDeleteDoctorDialogOpen(false)}*/}
            {/*    doctor={doctor}*/}
            {/*    onDelete={onDelete}*/}
            {/*/>*/}
            {isPatient && (
                <ReserveDialog
                    open={reserveDialogOpen}
                    onClose={() => setReserveDialogOpen(false)}
                    doctor={doctor}
                />
            )}
        </>
    );
};

export default DoctorCard;