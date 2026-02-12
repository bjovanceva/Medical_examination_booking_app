import React, { useState } from 'react';
import {
    Dialog, DialogTitle, DialogContent, DialogActions,
    Button, TextField
} from '@mui/material';
import keycloak from "../../../../keycloak.js";

const TermDialog = ({ open, onClose, doctorUsername, onAdd }) => {
    const [date, setDate] = useState('');
    const [time, setTime] = useState('');

    const handleSubmit = async () => {
        try {
            const payload = {
                doctorUsername: doctorUsername,
                date: date,
                time: time,
            };

            const token = keycloak.token;
            console.log("Token:", token);

            const response = await fetch(`http://localhost:9090/api/doctors/addNewTerm`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`,
                },
                body: JSON.stringify(payload),
            });

            const text = await response.text();
            const data = text ? JSON.parse(text) : {};

            if (!response.ok) {
                alert(`Error: ${data.message || 'Reservation failed'}`);
                return;
            }

            if (onAdd) {
                onAdd(data);
            }

            setDate('');
            setTime('');
            onClose();

        } catch (error) {
            console.error('Reservation creation error:', error);
            alert('An unexpected error occurred. Please try again.');
        }
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Reserve Medical Examination</DialogTitle>
            <DialogContent>
                <TextField
                    label="Date"
                    type="date"
                    fullWidth
                    margin="dense"
                    value={date}
                    onChange={e => setDate(e.target.value)}
                    InputLabelProps={{ shrink: true }}
                />
                {/*<TextField*/}
                {/*    label="Time"*/}
                {/*    type="time"*/}
                {/*    fullWidth*/}
                {/*    margin="dense"*/}
                {/*    value={time}*/}
                {/*    onChange={e => setTime(e.target.value)}*/}
                {/*    InputLabelProps={{ shrink: true }}*/}
                {/*/>*/}
                <TextField
                    label="Time"
                    type="time"
                    fullWidth
                    margin="dense"
                    value={time}
                    onChange={e => setTime(e.target.value)}
                    InputLabelProps={{ shrink: true }}
                    inputProps={{
                        step: 3600, // seconds → 3600 = 1 hour
                    }}
                />
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary">Save</Button>
            </DialogActions>
        </Dialog>
    );
};

export default TermDialog;
