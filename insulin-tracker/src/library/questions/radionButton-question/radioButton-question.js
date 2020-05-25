import React from 'react';
import ValidationMessage from "../../common/validation-message/validation-message";
import './radioButton-question.scss'

const RadioButtonQuestion = ({question, onChange, onBlur}) => {
    return (
        <div>
        <div className={question.error ? "radioButton-question__error-formGroup" : "radioButton-question__formGroup"}>
            <label className={question.error ? "radioButton-question__error-label" : "radioButton-question__label"}>
                {question.label}:
            </label>
            <div className="radioButton-question__container">
            {question.data.map((item, key) => (
                <div key={key}>
                    <label>
                        <input className="radioButton-question__input"
                               type="radio"
                               name={question.key}
                               value={item.value}
                               onChange={onChange}
                               onBlur={onBlur}/>
                        {item.label}
                    </label>
                </div>
            ))}
            </div>
        </div>
            {question.error && <ValidationMessage message={question.error}/>}
        </div>
    )
};

export default RadioButtonQuestion;
