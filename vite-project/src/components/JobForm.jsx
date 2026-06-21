// onSubmit is a prop - it can be a function or some data that we will use within this function

import {useState} from "react";
import "./JobForm.css"

function JobForm({onSubmit, initialData, mode}) {
    const[formData, setFormData] = useState(mode === "create"? {
        id: "",
        company: "",
        title: "",
        location: "",
        description: "",
        contactPerson: ""
    } :
        {
            id: initialData.id,
            company: initialData.company,
            title: initialData.title,
            location: initialData.location,
            description: initialData.description,
            contactPerson: initialData.contactPerson
        });

    // onSubmit comes from parent - JobsPage
    // e.preventDefault() prevents the page from reloading - so inplace changes are done
    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit(formData);
    }

    // <form onSubmit={handleSubmit}> same as:
    //      form.addEventListener("submit", handleSubmit)
    return (
        < div>
            <div className={"form-header"}>
                <span>title:</span>
                <span>company:</span>
                <span>location:</span>
                <span>contact person:</span>
                <span>description:</span>
            </div>
            <form onSubmit={handleSubmit}>
                <input
                    value = {formData.title || ""}
                    onChange={(e) =>
                        setFormData({...formData, title: e.target.value})
                    }
                />
                <input
                    value = {formData.company || ""}
                    onChange={(e) =>
                        setFormData({...formData, company: e.target.value})
                    }
                />
                <input
                    value = {formData.location || ""}
                    onChange={(e) =>
                        setFormData({...formData, location: e.target.value})
                    }
                />
                <input
                    value = {formData.contactPerson || ""}
                    onChange={(e) =>
                        setFormData({...formData, contactPerson: e.target.value})
                    }
                />
                <input
                    value = {formData.description || ""}
                    onChange={(e) =>
                        setFormData({...formData, description: e.target.value})
                    }
                />

                <button type={"submit"}>
                    Submit
                </button>

            </form>
        </div>
        // form is built-in HTML (a normal HTML form element)

    );
}

export default JobForm;