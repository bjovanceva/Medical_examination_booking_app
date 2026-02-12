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

const initialFormData = {
    "name": "",
    "surname": "",
    "examination_price": "",
    "type": "",
};

const AddDoctorDialog = ({open, onClose, onAdd}) => {
    const [formData, setFormData] = useState(initialFormData);

    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    const handleSubmit = () => {
        onAdd(formData);
        setFormData(initialFormData);
        onClose();
    };


    const doctorTypes = [
        { id: 'CARDIOLOGIST', name: 'CARDIOLOGIST' },
        { id: 'DERMATOLOGIST', name: 'DERMATOLOGIST' },
        { id: 'ORTHOPAEDIST', name: 'ORTHOPAEDIST' },
        { id: 'NEUROLOGIST', name: 'NEUROLOGIST' },
        { id: 'PSYCHIATRIST', name: 'PSYCHIATRIST' },
    ];

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
                    label="Examination_price"
                    name="examination_price"
                    type="number"
                    value={formData.examination_price}
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
                            <MenuItem key={type.id} value={type.id}>
                                {type.name}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary">Add</Button>
            </DialogActions>
        </Dialog>
    );
};

export default AddDoctorDialog;