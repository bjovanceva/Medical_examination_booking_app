import axiosInstance from "../axios/axios.js";

const medicalExaminationsReservationListRepository = {
    getActive: async () => {
        return await axiosInstance.get("/examinationList");
    },
    reserve: async (id) => {
        return await axiosInstance.post(`/examinationList/reserve/${id}`);
    },
};

export default medicalExaminationsReservationListRepository;