import axiosInstance from "../axios/axios.js";

const doctorRepository = {
    findAll: async () => {
        return await axiosInstance.get("/doctors");
    },
    findById: async (id, token) => {
            return await axiosInstance.get(`/doctors/${id}`, {
                headers: { Authorization: `Bearer ${token}` },
            });
        },
    add: async (data) => {
        return await axiosInstance.post("/doctors/add", data);
    },
    edit: async (id, data, token) => {
        return await axiosInstance.put(
            `/doctors/edit/${id}`,
            data,
            {
                headers: { Authorization: `Bearer ${token}` },
            }
        );
    },
    delete: async (id) => {
        return await axiosInstance.delete(`/doctors/delete/${id}`);
    },
};

export default doctorRepository;