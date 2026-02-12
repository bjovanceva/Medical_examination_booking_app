import React from "react";
import { Box, Typography, Button, Card, CardContent, Chip } from "@mui/material";
import { useNavigate } from "react-router";
import { Favorite, HealthAndSafety, SportsHandball, LocalHospital, Psychology } from "@mui/icons-material";
import keycloak from "../../../keycloak.js";

export const HomePage = () => {
    const navigate = useNavigate();

    const medicalImages = [
        "https://cpd.partners.org/sites/default/files/course/2025-01/neurology_brain.jpg",
        "https://productimages.withfloats.com/serviceimages/tile/68873439ff6171f0bca106d1Leading-Orthopedic-Surgeon",
        "https://images.squarespace-cdn.com/content/v1/542b409ce4b0682851cb9cd6/1415166412434-3MHGAIHU6LEENC5ISJHK/image-asset.jpeg",
        "https://www.concilio.com/wp-content/uploads/cardiologie-concilio-votre-conciergerie-medicale_718x452.jpg",
        "https://alkhidmatraazi.com/rawalpindi/wp-content/uploads/2018/11/Psychiatrist.jpg",
    ];

    return (
        <Box
            display="flex"
            flexDirection="column"
            alignItems="center"
            justifyContent="center"
            minHeight="80vh"
            p={2}
        >
            {/* Welcome Card */}
            <Card sx={{ maxWidth: 600, width: "100%", textAlign: "center", boxShadow: 3, mb: 4 }}>
                <CardContent>
                    <Typography variant="h4" gutterBottom>
                        Welcome to the Medical Examination Booking App
                    </Typography>
                    <Typography variant="body1" color="textSecondary" gutterBottom>
                        Easily manage your appointments and reserve your examinations online.
                    </Typography>

                    <Typography variant="body1" color="textSecondary" gutterBottom sx={{ mt: 2 }}>
                        If you have aches or health concerns, we have doctors for:
                    </Typography>

                    <Box display="flex" justifyContent="center" flexWrap="wrap" gap={1} mt={1} mb={2}>
                        <Chip icon={<Favorite />} label="CARDIOLOGIST" color="error" />
                        <Chip icon={<HealthAndSafety />} label="DERMATOLOGIST" color="success" />
                        <Chip icon={<SportsHandball />} label="ORTHOPAEDIST" color="warning" />
                        <Chip icon={<LocalHospital />} label="NEUROLOGIST" color="info" />
                        <Chip icon={<Psychology />} label="PSYCHIATRIST" color="secondary" />
                    </Box>

                    <Button
                        variant="contained"
                        color="primary"
                        sx={{ mt: 2 }}
                        onClick={() => navigate("/doctors")}
                    >
                        View Doctors
                    </Button>
                    <Typography color="textDisabled">You must be logged in first</Typography>
                </CardContent>
            </Card>

            {/* Medical Images Row */}
            <Box
                display="flex"
                gap={2}
                overflowX="auto"
                sx={{ width: "100%", justifyContent: "center", pb: 2 }}
            >
                {medicalImages.map((src, index) => (
                    <img
                        key={index}
                        src={src}
                        alt={`Medical ${index + 1}`}
                        style={{
                            width: 250,
                            height: 150,
                            borderRadius: 8,
                            boxShadow: "0 4px 8px rgba(0,0,0,0.2)",
                            objectFit: "cover",
                        }}
                    />
                ))}
            </Box>
        </Box>
    );
};