import React from 'react'

const DosageDetails = (props) => {

    const id = props.match.params.id;


    return (
        <div className="container section dosage-details">
            <div className="card z-depth-0">
                <div className="card-content">
    <span className="card-title">Dosage Details for UserId : {id} </span>
                    <p>Just a simple random text.</p>
                </div>
                <div className="card-action grey lighten-4 grey-text">
                    <div> Posted by PROJECT IB</div>
                    <div> March 24, 2020</div>
                </div>
            </div>
            
        </div>
    )
}

export default DosageDetails
