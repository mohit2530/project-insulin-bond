import React from "react";
import ValidationMessage from "../../common/validation-message/validation-message";
import "./checkbox-question.scss"

const CheckboxQuestion = ({question, onChange, onBlur}) => {
    const handleChange = (event) => {
        const {name, checked} = event.target;
        onChange({checkbox: true, name: name, checked: checked})
    };
    return (
        <div className="checkbox-question__formGroup">
            <label className={question.error ? "checkbox-question__error-label": "checkbox-question__label"}>
                <input
                    className="checkbox-question__input"
                    name={question.key}
                    type="checkbox"
                    value={question.value || false}
                    defaultChecked={question.value || false}
                    onChange={handleChange}
                    onBlur={onBlur}/>
                {question.label}
            </label>
            {question.error && <ValidationMessage message={question.error}/>}
        </div>
    )
};

export default CheckboxQuestion;
