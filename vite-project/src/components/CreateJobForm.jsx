// onSubmit is a prop - it can be a function or some data that we will use within this function

import {useState} from "react";

function CreateJobForm({onSubmit}) {
    const[formData, setFormData] = useState({
        company: "",
        title: "",
        location: "",
        description: "",
        contactPerson: ""
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
        // form is built-in HTML (a normal HTML form element)
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
    );
}

export default CreateJobForm;