import {Validators} from "../../library/utils/Validator";

export default function signUpValidators(value) {
    let errors = {};

    if (Validators.required(value.firstName)) {
        errors.firstName = 'First Name is Required';
    }
    if (Validators.required(value.lastName)) {
        errors.lastName = 'Last Name is Required';
    }
    if (Validators.required(value.email)) {
        errors.email = 'Email is Required';
    }
    if (Validators.required(value.username)) {
        errors.username = 'Username is Required';
    }
    if (Validators.required(value.password)) {
        errors.password = 'Password is Required';
    }
    if (Validators.required(value.confirmPassword)) {
        errors.confirmPassword = 'Confirm Password is Required';
    } else if (value.confirmPassword !== value.password) {
        errors.confirmPassword = 'Password Does not Match';
    }

    return errors;
}
