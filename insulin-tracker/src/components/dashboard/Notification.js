import React, { Component } from 'react';
import Log from '../../lib/log';


export default class Notification extends Component {
    render() {
        Log.info("Notification Class Initialized", "Notification Component");
        return (
            <div>
                <p> Notifcations</p>
            </div>
        )
    }
}
