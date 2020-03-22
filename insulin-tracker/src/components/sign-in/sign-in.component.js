import React, {useEffect} from "react";
import useForm from "../../library/utils/useForm";
import signInValidate from "./sign-in.validators";
import {signInQuestion} from "./sign-in.questions";
import TextboxQuestion from "../../library/questions/textbox-question/textbox-question";
import Button from "../../library/button/Button";
import FormErrorSummary from "../../library/utils/form-error-summary/form-error-summary";
import {connect} from "react-redux";
import {SignInAction, SignInLoadedAction} from "./sign-in.actions";
import SignInGuard from "./sign-in.guard";

const SignInComponent = (props) => {
    SignInGuard(props);
    const onSubmit = () => {
        props.dispatch(SignInAction(values));
    };

    const { handleSubmit, handleChange, handleBlur, values, errors } = useForm(signInValidate, onSubmit);

    const question = signInQuestion(values, errors);

    return (
        <form onSubmit={handleSubmit}>
            <h1>Sign In</h1>
            <FormErrorSummary errors={errors}/>
            <TextboxQuestion onChange={handleChange} onBlur={handleBlur} question={question.username}/>
            <TextboxQuestion onChange={handleChange} onBlur={handleBlur} question={question.password}/>
            <Button/>
        </form>
    )
};

const mapStateToProps = state => ({});

export default connect(mapStateToProps)(SignInComponent);
