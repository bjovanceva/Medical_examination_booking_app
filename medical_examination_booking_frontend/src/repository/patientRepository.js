import axiosInstance from "../axios/axios.js";

const patientRepository = {
    findAll: async (token) => {
        return await axiosInstance.get("/patients", {
            headers: { Authorization: `Bearer ${token}` },
        });
    },
    findById: async (id) => {
        return await axiosInstance.get(`/patients/${id}`);
    },
    add: async (data) => {
        return await axiosInstance.post("/patients/add", data);
    },
    edit: async (id, data, token) => {
        return await axiosInstance.put(`/patients/edit/${id}`, data, {
            headers: { Authorization: `Bearer ${token}` },
        });
    },
    delete: async (id) => {
        return await axiosInstance.delete(`/patients/delete/${id}`);
    },
};

export default patientRepository;