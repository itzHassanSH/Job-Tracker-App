import {createJobs, getJobs} from "../services/JobService.js";
import {useEffect, useState} from "react";
import JobBubble from "../components/JobBubble.jsx";
import JobsButtons from "../components/JobsButtons.jsx";
import CreateJobForm from "../components/CreateJobForm.jsx"


function JobsPage() {
    const [jobs, setJobs] = useState([]);

    const[isCreateFormOpen, setIsCreateFormOpen] = useState(false);

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

    useEffect(() => {
        const loadJobs = async () => {
            const data = await getJobs();
            setJobs(data);
        };

        loadJobs();
    }, []);



    return (
        <div>
            <JobsButtons
                text={"Create job"}
                onClick={() => setIsCreateFormOpen(true)}
            />

            {isCreateFormOpen && (
                <CreateJobForm onSubmit={handleCreateJobs} />
            )}


            {jobs.map(job => (
                <JobBubble key={job.id} job={job} />
            ))}
        </div>
    );

}

export default JobsPage
