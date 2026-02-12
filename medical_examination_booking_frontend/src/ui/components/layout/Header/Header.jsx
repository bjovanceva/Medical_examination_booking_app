import React from 'react';
import {Link, useNavigate} from "react-router";
import {AppBar, Box, Button, CircularProgress, IconButton, Toolbar, Typography} from "@mui/material";
import MenuIcon from '@mui/icons-material/Menu';
import "./Header.css";
import DoctorsGrid from "../../doctors/DoctorsGrid/DoctorsGrid.jsx";
import keycloak from "../../../../keycloak.js";
// import AuthenticationToggle from "../../auth/AuthenticationToggle/AuthenticationToggle.jsx";

const pages = [
    {"path": "/home", "name": "home"},
    {"path": "/doctors", "name": "doctors"},
    {"path": "/patients", "name": "patients"},
    {"path": "/reservationList", "name": "reservation List"},
];


const Header = () => {
    return (
        <Box>
            <AppBar position="static">
                <Toolbar>
                    <IconButton
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        sx={{mr: 2}}
                    >
                        <MenuIcon/>
                    </IconButton>
                    <Typography variant="h6" component="div" sx={{mr: 3}}>
                        Reservation System
                    </Typography>
                    <Box sx={{flexGrow: 1, display: {xs: "none", md: "flex"}}}>
                        {pages.map((page) => (
                            <Link key={page.name} to={page.path}>
                                <Button
                                    sx={{my: 2, color: "white", display: "block", textDecoration: "none"}}
                                >
                                    {page.name}
                                </Button>
                            </Link>
                        ))}
                    </Box>

                    {keycloak.authenticated ? (
                        <>
                            <Button variant="outlined" color="inherit" onClick={() => keycloak.logout({ redirectUri: 'http://localhost:3000' })}>
                                Log Out
                            </Button>
                        </>
                    ) : (
                        <Button variant="outlined" color="inherit" onClick={() => keycloak.login()}>
                            Log In
                        </Button>

                    )}

                    {/*<AuthenticationToggle/>*/}
                </Toolbar>
            </AppBar>
        </Box>
    );
};

export default Header;