import React from "react";
import './button.css'

export default function Button({type, name, disable}) {
    return (
        <div className="main">
            <button className={disable ? "disable-button" : "button"}
                    type={!!type ? type : 'submit'}>
                {!!name ? name : 'Submit'}
            </button>
        </div>
    )
}
