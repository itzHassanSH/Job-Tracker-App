import api from "../apis/axios.js"

export const getJobs = async () => {
    const response = await api.get("jobs");
    return response.data;
}

export const createJobs = async (jobData) => {
    const response = await api.post("jobs", jobData);
    return response.data;
}

export const deleteJobs = async (jobId) => {
    await api.delete(`jobs/${jobId}`);
}

export const updateJob = async (jobId, jobData) => {
    const response = await api.patch(`jobs/${jobId}`, jobData);
    return response.data;
}