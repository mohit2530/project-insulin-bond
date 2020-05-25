import React from 'react';
import ValidationMessage from "../../common/validation-message/validation-message";
import './dropdown-question.scss'

const DropdownQuestion = ({question, onChange, onBlur}) => {
    return (
        <div className="dropdown-question__formGroup">
            <select
                className={question.error ? "dropdown-question__error-select" : "dropdown-question__select"}
                name={question.key}
                value={question.value}
                onChange={onChange}
                onBlur={onBlur}>
                <option value="">{question.label}</option>
                {question.data.map((item, key) => (
                    <option
                        key={key}
                        value={item.value}>
                        {item.label}
                    </option>
                ))}
            </select>
            {question.error && <ValidationMessage message={question.error}/>}
        </div>
    )
};

export default DropdownQuestion;
