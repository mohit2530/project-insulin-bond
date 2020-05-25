import React from 'react';
import PropTypes from 'prop-types';
import './textbox-question.scss'
import ValidationMessage from "../../common/validation-message/validation-message";
import {phoneMask} from "../../utils/mask";

const TextboxQuestion = ({question, onChange, onBlur}) => {
    const placeHolder = () => {
        let placeholder = '';
        if (question.type === 'tel') {
            placeholder = "(123) 456-789";
        }
        return placeholder
    };

    const questionValue = () => {
        if (question.type === 'tel') {
            return question.value = phoneMask(question.value);
        } else {
            return question.value || '';
        }
    };


    return (
            <div className="textbox-question__container">
                <div className={question.error ? "textbox-question__error" : "textbox-question"}>
                    <div className="textbox-question__items">
                        <input
                            name={question.key}
                            type={!question.type ? 'text' : question.type}
                            value={questionValue()}
                            onChange={onChange}
                            onBlur={onBlur}
                            maxLength={!!question.max ? question.max : ''}
                            minLength={!!question.min ? question.min : ''}
                            placeholder={placeHolder()}
                            className="textbox-question__field"/>
                    </div>
                    <span className={question.error ? "textbox-question__label-error" : "textbox-question__label"}>
                        {question.label}
                    </span>
                </div>
                {question.error && <ValidationMessage message={question.error}/>}
            </div>
    )
};

TextboxQuestion.propTypes = {
    value: PropTypes.string || PropTypes.number,
    // formError: PropTypes.func.isRequired,
};

TextboxQuestion.defaultProps = {
    value: '',
};

export default TextboxQuestion;
