import React from "react";
import './form-error-summary.scss'
export default function FormErrorSummary({errors}) {
    return (
        <div>
            {Object.keys(errors).length > 0 &&
            <div className="form-error">
                <div className="form-error__image"></div>
                <div className="form-error__content">
                    <p>
                        Please Correct {Object.keys(errors).length === 1 ? `${Object.keys(errors).length} Error` :
                        ` ${Object.keys(errors).length} Errors`}
                    </p>
                </div>
            </div>}
        </div>
    )
};
