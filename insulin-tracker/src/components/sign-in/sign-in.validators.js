import {Validators} from "../../library/utils/Validator";

export default function signInValidate(values) {
    let errors = {};
    if (Validators.required(values.username)) {
        errors.username = 'Please enter your username'
    }
    if (Validators.required(values.password)) {
        errors.password = 'Please enter your password'
    }

    return errors;
}
