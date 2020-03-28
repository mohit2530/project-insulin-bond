import React, { Component } from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import NavBar from './components/layouts/navbar';
import Dashboard from './components/dashboard/Dashboard';
import Log from './lib/log';
import DosageDetails from './components/dosage/DosageDetails';


export default class extends Component {
    render() {
        Log.info("React Application Initialized", "Main Component");
        return (
            <BrowserRouter>
                <div className="App">
                    <NavBar/>
                    <Switch>
                        <Route exact path='/home' component={Dashboard}/>
                        <Route path='/dosage/:id' component={DosageDetails}/>
                    </Switch>
                </div>
            </BrowserRouter>
        )
    }
}
