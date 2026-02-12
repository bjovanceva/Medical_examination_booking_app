import React, {useState} from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    FormControl,
    InputLabel, MenuItem, Select,
    TextField
} from "@mui/material";

const doctorTypes = [
    { id: 'CARDIOLOGIST', name: 'CARDIOLOGIST' },
    { id: 'DERMATOLOGIST', name: 'DERMATOLOGIST' },
    { id: 'ORTHOPAEDIST', name: 'ORTHOPAEDIST' },
    { id: 'NEUROLOGIST', name: 'NEUROLOGIST' },
    { id: 'PSYCHIATRIST', name: 'PSYCHIATRIST' },
];

const EditDoctorDialog = ({open, onClose, doctor, onEdit}) => {
    const [formData, setFormData] = useState({
        "name": doctor.name,
        "surname": doctor.surname,
        "price": doctor.price,
        "type": doctor.type,
    });

    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    const handleSubmit = () => {
        onEdit(doctor.id, formData);
        setFormData(formData);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Add Doctor</DialogTitle>
            <DialogContent>
                <TextField
                    margin="dense"
                    label="Name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    fullWidth
                />
                <TextField
                    margin="dense"
                    label="Surname"
                    name="surname"
                    value={formData.surname}
                    onChange={handleChange}
                    fullWidth
                />
                <TextField
                    margin="dense"
                    label="Price"
                    name="price"
                    type="number"
                    value={formData.price}
                    onChange={handleChange}
                    fullWidth
                />
                <FormControl fullWidth margin="dense">
                        <InputLabel>Type</InputLabel>
                    <Select
                        name="type"
                        value={formData.type}
                        onChange={handleChange}
                        label="Type"
                        variant="outlined">
                        {doctorTypes.map((type) => (
                            <MenuItem key={type.id} value={type.id}>{type.name}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="warning">Edit</Button>
            </DialogActions>
        </Dialog>
    );
};

export default EditDoctorDialog;