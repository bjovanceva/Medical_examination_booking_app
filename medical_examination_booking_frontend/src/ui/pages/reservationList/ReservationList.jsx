import React, { useEffect, useState } from "react";
import {
    Box,
    Card,
    CardContent,
    Typography,
    IconButton,
    Button,
    Divider,
    List,
    ListItem,
    ListItemText,
} from "@mui/material";
import { Delete } from "@mui/icons-material";
import Reservation from "../../../models/Reservation.js";
import keycloak from "../../../keycloak.js";
import { loadStripe } from "@stripe/stripe-js";
import { PayPalScriptProvider, PayPalButtons } from "@paypal/react-paypal-js";

const stripePromise = loadStripe(
    ""

);

const ReservationList = () => {
    const [medicalExaminationReservations, setMedicalExaminationReservations] = useState([]);

    const getTotal = () =>
        medicalExaminationReservations?.reduce((sum, reservation) => sum + reservation.getPrice(), 0);


    useEffect(() => {


        const fetchReservationList = async () => {
            try {
                const res = await fetch("http://localhost:9090/api/examinationList", {
                    headers: { Authorization: `Bearer ${keycloak.token}` },
                });
                if (!res.ok) throw new Error("Network response was not ok");

                const data = await res.json();
                console.log("Fetched reservations:", data.medicalExaminationReservations);

                const reservations = await Promise.all(
                    data.medicalExaminationReservations.map(async (r) => {
                        const reservation = new Reservation(r);
                        await reservation.loadDoctor();
                        return reservation;
                    })
                );

                setMedicalExaminationReservations(reservations);
            } catch (err) {
                console.error("Fetch error:", err);
            }
        };

        fetchReservationList();
    }, [keycloak.token]);

    // Stripe checkout
    const handleCheckout = async () => {
        try {
            const response = await fetch("http://localhost:9090/api/checkout/create-checkout-session", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${keycloak.token}`,
                },
                body: JSON.stringify({ amount: Math.round(getTotal() * 100) }), // Stripe expects cents
            });

            if (!response.ok) throw new Error("Failed to create checkout session");

            const session = await response.json();
            const stripe = await stripePromise;
            await stripe.redirectToCheckout({ sessionId: session.id });
        } catch (error) {
            console.error("Stripe checkout error:", error);
        }
    };

    return (
        <Box my={3} width={500} mx="auto">
            <Card>
                <CardContent>
                    <Typography variant="h5" gutterBottom>
                        Reservation List
                    </Typography>
                    <Divider sx={{ mb: 2 }} />

                    <List>
                        {medicalExaminationReservations?.map((item) => (
                            <ListItem
                                key={item.id}
                                secondaryAction={
                                    <IconButton edge="end" color="error">
                                        <Delete />
                                    </IconButton>
                                }
                            >
                                <ListItemText
                                    primary={
                                        <Box>
                                            <Typography>{item.toString()}</Typography>
                                        </Box>
                                    }
                                />
                            </ListItem>
                        ))}
                    </List>

                    <Divider sx={{ my: 2 }} />
                    <Typography variant="h6">Total: ${getTotal()?.toFixed(2)}</Typography>

                    {/* Stripe Checkout */}
                    <Button
                        onClick={handleCheckout}
                        variant="contained"
                        color="primary"
                        fullWidth
                        sx={{ mt: 2 }}
                    >
                        Pay with Stripe
                    </Button>

                    {/* PayPal Checkout */}
                    <Box sx={{ mt: 2 }}>
                        <PayPalScriptProvider
                            options={{
                                "client-id": "",
                                currency: "USD",
                            }}
                        >
                            <PayPalButtons
                                style={{ layout: "vertical", color: "blue" }}
                                createOrder={async () => {
                                    const res = await fetch("http://localhost:9090/api/payments/create", {
                                        method: "POST",
                                        headers: {
                                            "Content-Type": "application/json",
                                            Authorization: `Bearer ${keycloak.token}`,
                                        },
                                        body: JSON.stringify({ amount: parseFloat(getTotal().toFixed(2)) }),
                                    });

                                    if (!res.ok) throw new Error("Failed to create PayPal order");

                                    const data = await res.json();
                                    console.log("PayPal order created:", data);
                                    return data.id;
                                }}
                                onApprove={async (data, actions) => {
                                    console.log("data:", data)
                                    console.log("actions:", actions)
                                    try {
                                        const details = await actions.order.capture();
                                        console.log("PayPal capture details:", details);
                                        const payerName = details?.payer?.name?.given_name || "User";
                                        alert("Payment completed by " + payerName);
                                    } catch (err) {
                                        console.error("PayPal capture error:", err);
                                    }
                                }}
                                onError={(err) => {
                                    console.error("PayPal error:", err);
                                    alert("PayPal payment failed: " + err.message);
                                }}
                            />

                        </PayPalScriptProvider>
                    </Box>
                </CardContent>
            </Card>
        </Box>
    );
};

export default ReservationList;


