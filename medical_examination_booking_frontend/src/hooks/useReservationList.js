import {useEffect, useState} from "react";
import keycloak from "../keycloak.js";
import axios from "axios";
import medicalExaminationsReservationListRepository
    from "../repository/medicalExaminationsReservationListRepository.js";
import doctorRepository from "../repository/doctorRepository.js";

const useReservationList = () => {
    const [reservationList, setReservationList] = useState({
        dateCreated: null,
        id: null,
        medicalExaminationReservations: [],
        status: null,
        user: null,
    });

    useEffect(() => {
        const fetchReservations = async () => {
            if (!keycloak.token) {
                console.log("Keycloak token not ready yet");
                return;
            }

            // Refresh token if it's about to expire
            await keycloak.updateToken(5).catch(() => keycloak.logout());

            try {
                const response = await axios.get(
                    "http://localhost:9090/api/examinationList",
                    {
                        headers: {
                            Authorization: `Bearer ${keycloak.token}`,
                        },
                    }
                );

                console.log(response.data);
                setReservationList(response.data);
            } catch (error) {
                console.log(error);
            }
        };

        fetchReservations();
    }, [keycloak.token]);

    return reservationList;
};

export default useReservationList;