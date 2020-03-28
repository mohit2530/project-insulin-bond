import React, { Fragment } from 'react';
import Log from '../../lib/log';
import DosageSummary from './DosageSummary';

const DosageList = () => {
    Log.info("List of Insulin Records ( Max 3 ) ", "DosageList Component");
    return (
        <Fragment>
            <div className="insulin-list section">
                <DosageSummary />
                <DosageSummary />
                <DosageSummary />
                <DosageSummary />
            </div>
        </Fragment>
    )
}

export default DosageList
