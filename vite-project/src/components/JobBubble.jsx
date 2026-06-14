import {useState} from "react";

function JobBubble( {job} ) {
    const [expanded, setExpanded] = useState(false);

    return (
        <div onClick={() => setExpanded(!expanded)}>
            <h3>{job.title}</h3>
            <h3>{job.company}</h3>
            <h3>{job.status}</h3>
            <h3>{job.location}</h3>

            {expanded && (
                <div>
                    <p>{job.description}</p>
                </div>
            )}
        </div>
    )
}

export default JobBubble;