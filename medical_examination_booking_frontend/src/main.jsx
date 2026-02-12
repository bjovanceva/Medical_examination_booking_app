import {StrictMode} from 'react'
import {createRoot} from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import {ReactKeycloakProvider} from '@react-keycloak/web';
import keycloak from "./keycloak.js";
import React from 'react'
import {UserProvider} from "./context/UserContext.jsx";


const eventLogger = (event, error) => {
    console.log('Keycloak event:', event);
    if (error) console.error('Keycloak error:', error);
};


createRoot(document.getElementById("root")).render(
    // <StrictMode>
            <ReactKeycloakProvider
                authClient={keycloak}
                onEvent={eventLogger}
            >
                <UserProvider>
                    <App />
                </UserProvider>
            </ReactKeycloakProvider>
    // </StrictMode>
);
