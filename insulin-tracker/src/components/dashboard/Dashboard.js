import React, { Component } from 'react';
import Notification from './Notification'
import DosageList from '../dosage/DosageList';
import Log from '../../lib/log';

export class Dashboard extends Component {
    render() {
        Log.info("Dashboard initialized", "Dashboard Component");
        return (
            <div className="container dashboard">
                <div className="row">
                    <div className="col s12 m6">
                        <DosageList/>
                    </div>
                    <div className="col s12 m5 offset-m1">
                        <Notification/>
                    </div>
                </div>
            </div>
        )
    }
}

export default Dashboard;
