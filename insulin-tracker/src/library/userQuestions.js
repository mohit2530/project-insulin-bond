import {Validators} from "./utils/Validator";

export const userQuestions = (values, errors) => {
    return {
        email: { // text box
            value: values.email,
            key: 'email',
            label: 'Username',
            /*max: 5,
            min: 5,*/
            error: errors.email
        },
        password: {
            value: values.password,
            key: 'password',
            label: 'Password',
            // max: 5,
            type: 'password',
            error: errors.password
        },
        phone: {
            value: values.phone,
            key: 'phone',
            label: 'Phone Number',
            type: 'tel',
            max: 14,
            error: errors.phone
        },
        date: {
            value: values.date,
            key: 'date',
            label: 'Today Date',
            type: 'date',
            error: errors.date
        },
        accept: { // single check box return true or false
            value: values.accept,
            key: 'accept',
            label: 'Accept Term and Condition',
            error: errors.accept
        },
        countries: { // single choice dropdown
            value: values.countries,
            key: 'countries',
            label: 'Choose From List of Countries',
            data: [
                {value: 'India', label: 'India'},
                {value: 'USA', label: 'USA'},
                {value: 'UK', label: 'UK'},
                {value: 'Germany', label: 'Germany'},
                {value: 'Russia', label: 'Russia'},
                {value: 'Italy', label: 'Italy'},
            ],
            error: errors.countries
        },
        country: { // Radio Button for single choices
            value: values.country,
            key: 'country',
            label: 'List of Countries',
            data: [
                {value: 'India', label: 'India'},
                {value: 'USA', label: 'USA'},
                {value: 'UK', label: 'UK'},
                {value: 'Germany', label: 'Germany'},
                {value: 'Russia', label: 'Russia'},
                {value: 'Italy', label: 'Italy'},
            ],
            error: errors.country
        },
        sport: { // Group Check boxes
            value: values.sport,
            key: 'sport',
            label: 'What is your favorite Sport',
            data: [
                {value: 'Soccer', label: 'Soccer'},
                {value: 'Cricket', label: 'Cricket'},
                {value: 'Football', label: 'FootBall'},
                {value: 'Hockey', label: 'Hockey'},
                {value: 'Table Tennis', label: 'Table Tennis'},
                {value: 'Boxing', label: 'Boxing'},
            ],
            error: errors.sport
        },
        hello: { // Group Check boxes
            value: values.hello,
            key: 'hello',
            label: 'What is your favorite Sport',
            data: [
                {value: 'Soccer', label: 'Soccer'},
                {value: 'Cricket', label: 'Cricket'},
                {value: 'Football', label: 'FootBall'},
                {value: 'Hockey', label: 'Hockey'},
                {value: 'Table Tennis', label: 'Table Tennis'},
                {value: 'Boxing', label: 'Boxing'},
            ],
            error: errors.hello
        },
    }
};

export default function validate(values) {
    let errors = {};
    if (Validators.required(values.email)) {
        errors.email = "Email is required";
    } else if (Validators.email(values.email)) {
        errors.email = "Please enter a valid email address";
    }

    if (Validators.required(values.password)) {
        errors.password = "password is required";
    } else if(Validators.password(values.password)) {
        errors.password = "Please try Stronger Password"
    }
    if (Validators.required(values.accept)) {
        errors.accept = "Please Check this Box";
    }
    if (Validators.required(values.countries)) {
        errors.countries = "Please Choose one";
    }
    if (Validators.required(values.country)) {
        errors.country = "Please Choose one";
    }
    if (Validators.required(values.sport)) {
        errors.sport = "Please Choose More then one";
    }
    if (Validators.required(values.phone)) {
        errors.phone = "Please Enter your Phone Number";
    }
    if (Validators.phone(values.phone)) {
        errors.phone = "Please Valid Phone Number";
    }
    if (Validators.required(values.date)) {
        errors.date = "Please Enter the date";
    }
    if (!values.hello || Object.keys(values.hello).length === 0) {
        errors.hello = "Please Choose More then one";
    }
    return errors;
};
