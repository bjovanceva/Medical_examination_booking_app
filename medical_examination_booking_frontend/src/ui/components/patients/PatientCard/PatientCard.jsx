import React, {useState} from 'react';
import {Box, Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import {useNavigate} from "react-router";
import InfoIcon from '@mui/icons-material/Info';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import DeleteDoctorDialog from "../../doctors/DeleteDoctorDialog/DeleteDoctorDialog.jsx";
import EditDoctorDialog from "../../doctors/EditDoctorDialog/EditDoctorDialog.jsx";
import EditPatientDialog from "../EditPatientDialog/EditPatientDialog.jsx";
import DeletePatientDialog from "../DeletePatientDialog/DeletePatientDialog.jsx";


const PatientCard = ({patient, onEdit, onDelete}) => {
    const navigate = useNavigate();
    // const [editPatientDialogOpen, setEditPatientDialogOpen] = useState(false);
    // const [deletePatientDialogOpen, setDeletePatientDialogOpen] = useState(false);

    return (
        <>
            <Card sx={{boxShadow: 3, borderRadius: 2, p: 1}}>
                <CardContent>
                    <Typography variant="h5" sx={{ fontWeight: 700, mt: 1 }}>
                        <Box component="span" sx={{ color: "text.secondary" }}>{patient.name}</Box>{" "}
                        <Box component="span" sx={{ color: "text.secondary" }}>{patient.surname}</Box>
                    </Typography>

                    <Box display="flex" justifyContent="center" mt={2}>
                        <img
                            src={patient.photoUrl}
                            alt={`${patient.name} ${patient.surname}`}
                            style={{
                                width: 330,
                                height: 350,
                                borderRadius: 8,
                                boxShadow: "0 4px 8px rgba(0,0,0,0.2)",
                                objectFit: "cover",
                            }}
                        />
                    </Box>
                    <Typography variant="body1" sx={{ mt: 1 }}>
                        <Typography component="span" color="text.secondary" fontWeight="bold">
                            Age:
                        </Typography>{" "}
                        {patient.age}
                    </Typography>                    {/*<Typography variant="subtitle2">*/}
                    {/*    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab aperiam assumenda blanditiis cum*/}
                    {/*    ducimus enim modi natus odit quibusdam veritatis.*/}
                    {/*</Typography>*/}
                    {/*<Typography variant="body1" fontWeight="bold"*/}
                    {/*            sx={{textAlign: "right", fontSize: "1.25rem"}}>${doctor.price}</Typography>*/}
                    {/*<Typography variant="body2" sx={{textAlign: "right"}}>{product.quantity} piece(s)*/}
                    {/*    available</Typography>*/}
                </CardContent>
                {/*<CardActions sx={{justifyContent: "space-between"}}>*/}
                {/*    <Button*/}
                {/*        size="small"*/}
                {/*        color="info"*/}
                {/*        startIcon={<InfoIcon/>}*/}
                {/*        onClick={() => navigate(`/patients/${patient.id}`)}*/}
                {/*    >*/}
                {/*        Info*/}
                {/*    </Button>*/}
                {/*    <Box>*/}
                {/*        <Button*/}
                {/*            size="small"*/}
                {/*            color="warning"*/}
                {/*            startIcon={<EditIcon/>}*/}
                {/*            sx={{mr: "0.25rem"}}*/}
                {/*            onClick={() => setEditPatientDialogOpen(true)}*/}
                {/*        >*/}
                {/*            Edit*/}
                {/*        </Button>*/}
                {/*        <Button*/}
                {/*            size="small"*/}
                {/*            color="error"*/}
                {/*            startIcon={<DeleteIcon/>}*/}
                {/*            onClick={() => setDeletePatientDialogOpen(true)}*/}
                {/*        >*/}
                {/*            Delete*/}
                {/*        </Button>*/}
                {/*    </Box>*/}
                {/*</CardActions>*/}
            </Card>
            {/*<EditPatientDialog*/}
            {/*    open={editPatientDialogOpen}*/}
            {/*    onClose={() => setEditPatientDialogOpen(false)}*/}
            {/*    patient={patient}*/}
            {/*    onEdit={onEdit}*/}
            {/*/>*/}
            {/*<DeletePatientDialog*/}
            {/*    open={deletePatientDialogOpen}*/}
            {/*    onClose={() => setDeletePatientDialogOpen(false)}*/}
            {/*    patient={patient}*/}
            {/*    onDelete={onDelete}*/}
            {/*/>*/}
        </>
    );
};

export default PatientCard;