import './App.css'
import React, {useEffect} from 'react'
import {BrowserRouter, Routes, Route} from "react-router-dom";
import DoctorsPage from "./ui/pages/DoctorsPage/DoctorsPage.jsx";
import Layout from "./ui/components/layout/Layout/Layout.jsx";
import PatientsPage from "./ui/pages/PatientsPage/PatientsPage.jsx";
import ReservationList from "./ui/pages/reservationList/ReservationList.jsx";
import SuccessPage from "./ui/pages/SuccessPage/SuccessPage.jsx";
import ProfilePage from "./ui/pages/ProfilePage/ProfilePage.jsx";
import {useUser} from "./context/UserContext.jsx";
import {useKeycloak} from "@react-keycloak/web";
import axios from "axios";
import {HomePage} from "./ui/pages/HomePage/HomePage.jsx"

const App = () => {

    const { setUser } = useUser();
    const { keycloak, initialized } = useKeycloak();


    useEffect(() => {
        const fetchUser = async () => {
            if (initialized && keycloak?.authenticated) {
                try {
                    const response = await axios.get("http://localhost:9090/me", {
                        headers: { Authorization: `Bearer ${keycloak.token}` },
                    });
                    setUser(response.data);
                    console.log("Fetched user:", response.data);
                } catch (err) {
                    console.error("Failed to fetch user:", err);
                }
            }
        };

        fetchUser();
    }, [initialized, keycloak, setUser]);


    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout />}>
                    {/*<Route index element={<DoctorsPage />} />*/}
                    <Route index element={<HomePage />} />
                    <Route path="home" element={<HomePage />} />
                    <Route path="doctors" element={<DoctorsPage />} />
                    <Route path="patients" element={<PatientsPage />} />
                    <Route path="reservationList" element={<ReservationList />} />
                    <Route path="profile" element={<ProfilePage />} />
                    <Route path="success" element={<SuccessPage />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
};

export default App

