import React from "react";
import FormErrorSummary from "../../library/utils/form-error-summary/form-error-summary";
import useForm from "../../library/utils/useForm";
import {SignUpAction} from "./sign-up.actions";
import signUpValidators from "./sign-up.validators";
import {connect} from "react-redux";
import {signUpQuestions} from "./sign-up.questions";
import TextboxQuestion from "../../library/questions/textbox-question/textbox-question";
import Button from "../../library/button/Button";
import {getUsername} from "../sign-in/sign-in.reducer";

const SignUpComponent = (props) => {
  const onSubmit = async () => {
    props.dispatch(SignUpAction(values));
  };

    const { handleSubmit, handleChange, handleBlur, values, errors } = useForm(signUpValidators, onSubmit);

    const question = signUpQuestions(values, errors);

    return (
        <form onSubmit={handleSubmit}>
            <h1>Sign Up</h1>
            <h1>{getUsername()}</h1>
            <FormErrorSummary errors={errors}/>
            <TextboxQuestion onChange={handleChange} onBlur={handleBlur} question={question.firstName} />
            <TextboxQuestion onChange={handleChange} onBlur={handleBlur} question={question.lastName} />
            <TextboxQuestion onChange={handleChange} onBlur={handleBlur} question={question.email} />
            <TextboxQuestion onChange={handleChange} onBlur={handleBlur} question={question.username} />
            <TextboxQuestion onChange={handleChange} onBlur={handleBlur} question={question.password} />
            <TextboxQuestion onChange={handleChange} onBlur={handleBlur} question={question.confirmPassword} />
            <Button name={'Sign Up'}/>
        </form>
    )
};

const mapStateToProps = state => ({});

export default connect(mapStateToProps)(SignUpComponent);
