import {createJobs, getJobs, deleteJobs, updateJob} from "../services/JobService.js";
import {useEffect, useState} from "react";
import JobBubble from "../components/JobBubble.jsx";
import JobsButtons from "../components/JobsButtons.jsx";
import JobForm from "../components/JobForm.jsx"
import "../components/JobBubble.css"


function JobsPage() {
    const [jobs, setJobs] = useState([]);
    const[isCreateFormOpen, setIsCreateFormOpen] = useState(false);

    // deleting
    const[deleteMode, setDeleteMode] = useState(false);
    const[selectedJobs, setSelectedJobs] = useState(new Set());

    // updating
    const[editingJob, setEditingJob] = useState(null);

    const handleCreateJobs = async (formData) => {
        try {
            const newJob = await createJobs(formData);
            setJobs(prev => [...prev, newJob]);
            setIsCreateFormOpen(false);
        } catch (error) {
            console.error(error);
            // keep form open so user can fix it
        }
    }

    // Editing an existing Job
    const handleEdit = (job) => {
        setEditingJob(job);
    }
    const handleUpdateJobs = async (jobData) => {
        const newJob = await updateJob(jobData.id, jobData);
        setJobs(jobs => jobs.map(job =>
            job.id === jobData.id?
                newJob :
                job
        ));

        setEditingJob(null)
    }

    const handleCancelDelete = () => {
        setDeleteMode(false);
        setSelectedJobs(new Set());
    }

    function handleToggleSelection(jobId) {
        setSelectedJobs(prev => {
            const newSet = new Set(prev);

            if (newSet.has(jobId)) {
                newSet.delete(jobId);
            } else {
                newSet.add(jobId);
            }

            return newSet
        })
    }

    const handleDeleteJobs = async () => {
        console.log(selectedJobs)
        await Promise.all(
            [...selectedJobs].map(id => deleteJobs(id))
        );

        setJobs(prev => prev.filter(job => !selectedJobs.has(job.id)))
        setSelectedJobs(new Set());
        setDeleteMode(false);

    }

    useEffect(() => {
        const loadJobs = async () => {
            const data = await getJobs();
            setJobs(data);
        };

        loadJobs();
    }, []);


    // with editingJob?.id, we get undefines for editingJob being null - thus no crash
    return (
        <div>
            <JobsButtons
                text={"Create Job"}
                onClick={() => setIsCreateFormOpen(true)}
            />
            <div className="job-header">
                <span>Title</span>
                <span>Company</span>
                <span>Location</span>
                <span>Status</span>
            </div>

            {jobs.map(job => job.id === editingJob?.id ?
                (<JobForm key = {job.id} onSubmit={handleUpdateJobs} initialData={job} mode={"edit"}/>) : (
                <JobBubble
                    key={job.id}
                    job={job}
                    deleteMode = {deleteMode}
                    isSelected = {selectedJobs.has(job.id)}
                    onToggleSelection ={handleToggleSelection}
                    handleEdit={handleEdit}
                />
            ))}

            {isCreateFormOpen && (
                <JobForm onSubmit={handleCreateJobs} mode={"create"}/>
            )}

            {!deleteMode && (
                <JobsButtons onClick={() => setDeleteMode(true)}
                             text={"Delete Jobs"}
                             />
            )}

            {deleteMode && (
                <>
                    <JobsButtons onClick={handleCancelDelete}
                                 text={"Cancel"}
                                 />
                    <JobsButtons onClick={handleDeleteJobs}
                                 text={"Confirm"}
                                 />
                </>
            )}



        </div>
    );

}

export default JobsPage
