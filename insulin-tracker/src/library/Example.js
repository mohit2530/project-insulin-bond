import React from 'react';
import FormErrorSummary from "./utils/form-error-summary/form-error-summary";
import TextboxQuestion from "./questions/textbox-question/textbox-question";
import DropdownQuestion from "./questions/dropdown-question/dropdown-question";
import RadioButtonQuestion from "./questions/radionButton-question/radioButton-question";
import CheckboxGroupQuestion from "./questions/checkbox-group-question/checkbox-group-question";
import SelectGroupQuestion from "./questions/select-group-question/select-group-question";
import CheckboxQuestion from "./questions/checkbox-question/checkbox-question";
import validate, {userQuestions} from "./userQuestions";
import useForm from "./utils/useForm";
import Button from "./button/Button";

const Example = () => {
    const submitFunction = async () => {
        console.log(values);
    };

    const initialState = {email: 'email@gmail.com',
    phone: '1234567890'};
    // call the hook
    const { handleSubmit, handleChange, handleBlur, values, errors } = useForm(validate, submitFunction, initialState);
    const question = userQuestions(values, errors);
    return (
        <form onSubmit={handleSubmit} noValidate className="example">
            <FormErrorSummary errors={errors}/>
            <TextboxQuestion
                question={question.email}
                onChange={handleChange}
                onBlur={handleBlur}/>

            <TextboxQuestion question={question.password}
                             onChange={handleChange}
                             onBlur={handleBlur}/>

            <TextboxQuestion question={question.phone}
                             onChange={handleChange}
                             onBlur={handleBlur}/>

            <TextboxQuestion question={question.date}
                             onChange={handleChange}
                             onBlur={handleBlur}/>

            <DropdownQuestion question={question.countries}
                      onChange={handleChange}
                      onBlur={handleBlur}/>

            <RadioButtonQuestion question={question.country}
                      onChange={handleChange}
                      onBlur={handleBlur}/>

             <CheckboxGroupQuestion
                 question={question.sport}
                 onChange={handleChange}
                 onBlur={handleBlur}/>

             <SelectGroupQuestion
                 question={question.hello}
                 onChange={handleChange}
                 onBlur={handleBlur}/>

            <CheckboxQuestion question={question.accept}
                              onChange={handleChange}
                              onBlur={handleBlur}/>
            <Button/>
        </form>

        );
};

export default Example;
