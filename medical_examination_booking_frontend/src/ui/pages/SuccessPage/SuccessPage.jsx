import React, { useEffect, useState } from "react";
import { Box, Typography, CircularProgress, Alert, Button } from "@mui/material";
import { useKeycloak } from "@react-keycloak/web";
import CheckCircleOutlineIcon from '@mui/icons-material/CheckCircleOutline';
import { useNavigate } from "react-router-dom";

export default function Success() {
    const { keycloak, initialized } = useKeycloak();
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const clearList = async () => {
            try {
                if (!initialized) {
                    await new Promise(resolve => {
                        const interval = setInterval(() => {
                            if (initialized) {
                                clearInterval(interval);
                                resolve();
                            }
                        }, 100);
                    });
                }

                const refreshed = await keycloak.updateToken(5);
                if (!refreshed) console.log("Token is still valid");

                const res = await fetch("http://localhost:9090/api/checkout/cleanList", {
                    method: "GET",
                    headers: { Authorization: `Bearer ${keycloak.token}` },
                });

                if (!res.ok) throw new Error("Failed to clear reservation list");

                console.log("Temporary reservation list cleared!");
                setLoading(false);

                // optional: redirect to home after 5 seconds
                setTimeout(() => navigate("/"), 5000);

            } catch (err) {
                console.error("Error clearing reservation list:", err);
                setError(err.message || "Unknown error occurred");
                setLoading(false);
            }
        };

        clearList();
    }, [initialized, keycloak, navigate]);

    return (
        <Box
            sx={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                justifyContent: "center",
                height: "80vh",
                textAlign: "center",
                p: 2,
            }}
        >
            <CheckCircleOutlineIcon color="success" sx={{ fontSize: 80, mb: 2 }} />
            <Typography variant="h3" gutterBottom>
                Payment Successful!
            </Typography>
            <Typography variant="h6" color="text.secondary">
                Thank you for your purchase. Your temporary reservations have been cleared.
            </Typography>

            {loading && (
                <Box sx={{ display: "flex", alignItems: "center", mt: 3 }}>
                    <CircularProgress />
                    <Typography sx={{ ml: 2 }}>Processing...</Typography>
                </Box>
            )}

            {error && (
                <Alert severity="error" sx={{ mt: 3 }}>
                    {error}
                </Alert>
            )}

            {!loading && !error && (
                <Button
                    variant="contained"
                    color="primary"
                    sx={{ mt: 3 }}
                    onClick={() => navigate("/")}
                >
                    Go to Home
                </Button>
            )}

            {!loading && !error && (
                <Typography variant="body2" color="text.secondary" sx={{ mt: 1 }}>
                    You will be redirected automatically in 5 seconds...
                </Typography>
            )}
        </Box>
    );
}