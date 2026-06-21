import {useState} from "react"
import "../components/JobBubble.css"
import JobsButtons from "./JobsButtons.jsx";

function JobBubble( {job, deleteMode, isSelected, onToggleSelection, handleEdit} ) {
    const [expanded, setExpanded] = useState(false);

    // There's a big difference between handleEdit(job) and () => handleEdit(job)
    // One is a function being passed (that executes onClick), while the other executes exactly then and there
    return (
        <div className="job-bubble" >

            {deleteMode && (
                <input
                    type="checkbox"
                    checked={isSelected}
                    onChange={() => onToggleSelection(job.id)}
                />
            )}

            <div className="job-row" onClick={() => setExpanded(!expanded)}>
                <span>{job.title}</span>
                <span>{job.company}</span>
                <span>{job.location}</span>
                <span>{job.status}</span>
            </div>
            {!deleteMode && (<JobsButtons text={"Edit"} onClick={() => handleEdit(job)} className={"edit-button"}/>)}

            {expanded && (
                <div>
                    <p>Description: </p>
                    <p>{job.description}</p>
                </div>
            )}
        </div>
    )
}

export default JobBubble;