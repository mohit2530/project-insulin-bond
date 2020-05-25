import { useState, useEffect } from 'react';

const useForm = (validate, runOnSubmit, initialState) => {
    const [values, setValues] = useState(!!initialState ? initialState : {});
    const [errors, setErrors] = useState({});

    const [touched, setTouched] = useState([]);
    const [isSubmitting, setSubmitting] = useState(false);


    useEffect(() => {
        if (isSubmitting) {
            const noErrors = Object.keys(errors).length === 0;
            if (noErrors) {
                setTouched([]);
                runOnSubmit();
                setSubmitting(false);
            } else {
                setSubmitting(false);
            }
        }
    }, [errors]);

    useEffect(() => {
        const validationErrors = validate(values);
        const touchedErrors = Object.keys(validationErrors)
            .filter(key => touched.includes(key))
            .reduce((acc, key) => {
                if (!acc[key]) {
                    acc[key] = validationErrors[key]
                }
                return acc
            }, {});
        setErrors(touchedErrors);
    }, [touched, values]);


    const handleChange = (event) => {
        if(event.selectAll === true){
            setValues(values => ({ ...values, [event.name]: event.value }));
        }else if(event.checkboxGroup === true){
            setValues(values => ({ ...values, [event.name]: event.value }));
        }
        else if (event.checkbox === true) {
            setValues(values => ({ ...values, [event.name]: event.checked }));
        } else {
            event.persist();
            setValues(values => ({...values, [event.target.name]: event.target.value}));
        }
    };

    const handleBlur = (event) => {
        if (event.selectAll === true && !touched.includes(event.name)) {
            setTouched([
                ...touched,
                event.name
            ])
        }if (!touched.includes(!!event.target && event.target.name)) {
            setTouched([
                ...touched,
                event.target.name
            ])
        }
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        const validationErrors = validate(values);
        setErrors(validationErrors);
        setSubmitting(true);
    };


    return {
        handleSubmit,
        handleChange,
        handleBlur,
        values,
        errors,
        isSubmitting,
        setValues
    };
};

export default useForm;
