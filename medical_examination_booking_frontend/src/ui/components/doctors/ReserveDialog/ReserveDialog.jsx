import React, { useEffect, useState } from "react";
import {
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    Button,
    TextField,
    MenuItem
} from "@mui/material";
import keycloak from "../../../../keycloak.js";
import { useUser } from "../../../../context/UserContext.jsx";

const ReserveDialog = ({ open, onClose, doctor }) => {
    const [terms, setTerms] = useState([]);
    const [selectedTerm, setSelectedTerm] = useState("");
    const { user } = useUser();

    useEffect(() => {
        if (!open) return;
        const fetchTerms = async () => {
            try {
                const response = await fetch(
                    `http://localhost:9090/api/doctors/terms/${doctor.id}`,
                    {
                        headers: {
                            Authorization: `Bearer ${keycloak.token}`,
                        },
                    }
                );
                const data = await response.json();
                setTerms(data);
            } catch (error) {
                console.error("Failed to fetch terms", error);
            }
        };

        fetchTerms();
    }, [doctor.id, open]);

    const handleReserve = async () => {
        const term = terms.find((t) => t.id === selectedTerm);
        if (!term) return;


        const payload = {
            date: term.whenDate,
            time: term.whenTime,
        };

        console.log(terms)

        console.log(term.whenDate)
        console.log(term.whenTime)
        console.log("Calling:", `http://localhost:9090/api/examinationList/reserve/${doctor.id}`);

        try {
            const response = await fetch(`http://localhost:9090/api/examinationList/reserve/${doctor.id}`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${keycloak.token}`,
                },
                body: JSON.stringify(payload),
            });

            if (!response.ok) {
                throw new Error("Reservation failed");
            }

            console.log("Reservation saved successfully");
            onClose();
        } catch (error) {
            console.error("Failed to save reservation:", error);
        }
    };

    return (
        <Dialog open={open} onClose={onClose} fullWidth>
            <DialogTitle>Reserve Appointment with {doctor.name}</DialogTitle>
            <DialogContent>
                <TextField
                    select
                    label="Available Terms"
                    value={selectedTerm}
                    onChange={(e) => setSelectedTerm(e.target.value)}
                    fullWidth
                    margin="dense"
                >
                    {terms.map((term) => (
                        <MenuItem key={term.id} value={term.id}>
                            {term.whenDate} {term.whenTime}
                        </MenuItem>
                    ))}
                </TextField>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button
                    onClick={handleReserve}
                    variant="contained"
                    disabled={!selectedTerm}
                >
                    Save
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default ReserveDialog;