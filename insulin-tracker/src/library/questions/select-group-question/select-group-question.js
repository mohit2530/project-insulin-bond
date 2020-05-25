import React, {useState} from "react";
import ValidationMessage from "../../common/validation-message/validation-message";
import './select-group-question.scss';
import {addToObject} from "../../utils/patterns";

const SelectGroupQuestion = ({question, onChange, onBlur}) => {
    const INITIAL_STATE = addToObject(question.data, 'isChecked', false);
    const [inputs, setInputs] = useState({list: INITIAL_STATE, allChecked: false});
    const [mainCheck, setMainCheck] = useState(false);

    const handleChange = e => {
        let itemName = e.target.name;
        let checked = e.target.checked;
        setInputs(inputs => {
            let { list, allChecked } = inputs;
            if (itemName === question.key) {
                allChecked = checked;
                list = list.map(item => ({ ...item, isChecked: checked }));
            } else {
                list = list.map(item =>
                    item.value === itemName ? { ...item, isChecked: checked } : item
                );
                allChecked = list.every(item => item.isChecked);
            }
        return { list, allChecked };
    });
    };

    const handleBlur = (event) => {
        let finalAns = [];
        inputs.list.map(item => item.isChecked ? finalAns.push(item.value) : []);
        onChange({selectAll: true, name: question.key, value: finalAns});
        const target = {name: question.key};
        onBlur({selectAll: true, target: target});
    };

    return (
        <div>
            <div className={question.error ? "select-group-question__error-formGroup" : "select-group-question__formGroup"}>
            <input
                className="select-group-question__input"
                type="checkbox"
                name={question.key}
                checked={inputs.allChecked}
                onChange={handleChange}
                onBlur={handleBlur}
            />
                <label className={question.error ? "select-group-question__error-label" : "select-group-question__label"}>
                    {question.label}</label>
                <div className="select-group-question__selection">
                {inputs.list.map((item, key) => (
                <div  key={key}>
                <input
                    className="select-group-question__input"
                    type="checkbox"
                    name={item.value}
                    value={item.value}
                    checked={item.isChecked}
                    onChange={handleChange}
                    onBlur={handleBlur}
                    />
                <label>{item.label}</label>
                </div>
                ))}
                </div>
        </div>
            {question.error && <ValidationMessage message={question.error}/>}
    </div>
    );

};

export default SelectGroupQuestion;
