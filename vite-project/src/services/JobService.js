import api from "../apis/axios.js"

export const getJobs = async () => {
    const response = await api.get("jobs");
    return response.data;
}

export const createJobs = async (jobData) => {
    const response = await api.post("jobs", jobData);
    return response.data;
}