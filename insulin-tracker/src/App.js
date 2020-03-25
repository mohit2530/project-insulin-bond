import React, { Component } from 'react';
import { BrowserRouter } from 'react-router-dom';
import NavBar from './components/layouts/navbar';
import Log from './lib/log';


export default class extends Component {
    render() {
        Log.info("React Application Initialized", "Main Component");
        return (
            <BrowserRouter>
                <div className="App">
                    <NavBar/>
                </div>
            </BrowserRouter>
        )
    }
}
