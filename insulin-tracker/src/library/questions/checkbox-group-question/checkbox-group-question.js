import React, {useState} from "react";
import ValidationMessage from "../../common/validation-message/validation-message";
import './checkbox-group-question.scss';

const CheckboxGroupQuestion = ({question, onChange, onBlur}) => {
    const [inputs, setInputs] = useState([]);
    const handleClick = (event) => {
        const index = inputs.findIndex(item => item === event.target.value);

        if (~index) {
            setInputs([
                ...inputs.slice(0, index),
                ...inputs.slice(index + 1)
            ]);
        } else {
            setInputs([...inputs, event.target.value]);
        }
    };

    const handleBlur = (event) => {
        onChange({checkboxGroup: true, name: event.target.name, value: inputs});
        onBlur(event);
    };

    return (
        <div>
        <div className={question.error ? "checkbox-group-question__error-formGroup" : "checkbox-group-question__formGroup"}>
            <label className={question.error ? "checkbox-question__error-label" : "checkbox-question__label"}>
                {question.label}
            </label>
            <div className="checkbox-group-question__container">
                {question.data.map(data => (
                    <div key={data.value}>
                    <label>
                        <input
                            className="checkbox-group-question__input"
                            type="checkbox"
                            name={question.key}
                            value={data.value}
                            onClick={handleClick}
                            onBlur={handleBlur}
                        />
                        {data.value}
                    </label>
                    </div>
                ))}
            </div>
        </div>
            {question.error && <ValidationMessage message={question.error}/>}
        </div>
    );
};

export default CheckboxGroupQuestion;
