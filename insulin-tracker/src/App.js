import React, {Component} from 'react';
import {BrowserRouter, Switch, Route} from 'react-router-dom';
import NavBar from './components/layouts/navbar';
import Dashboard from './components/dashboard/Dashboard';
import Log from './lib/log';
import SignIn from "./components/sign-in/sign-in.component";
import SignUpComponent from "./components/sign-up/sign-up.component";
import {ConnectedRouter} from 'connected-react-router'
import {path} from "./navigation/path";


const App = ({history}) => {
    Log.info("React Application Initialized", "Main Component");
    return (
        <BrowserRouter>
            <div className="App">
                <NavBar/>
                <ConnectedRouter history={history}>
                    <Switch>
                        <Route exact path={path.home} component={Dashboard}/>
                        <Route path={path.signUp} component={SignUpComponent}/>
                        <Route path={path.signIn} component={SignIn}/>
                        <SignIn/>
                    </Switch>
                </ConnectedRouter>
            </div>
        </BrowserRouter>
    )
};

export default App;
