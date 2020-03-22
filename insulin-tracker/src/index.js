import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import thunk from "redux-thunk";
import {createEpicMiddleware} from "redux-observable";
import {applyMiddleware, compose, createStore} from "redux";
import {rootReducers} from "./module/rootReducer";
import {Provider} from "react-redux";
import {rootEpic} from "./module/rootEpic";
import {routerMiddleware} from 'connected-react-router';
import {createBrowserHistory} from 'history';
import {BrowserRouter} from "react-router-dom";


/**
 * Initializing Logging for developmental usage. For this to work, it is important to set Local Storage under applications to debug and pass the project name with an * after deploying the application.
 * We are not using it in production env, because prod is minimized and it doesn't suffice the reasons of logging at least here.
 */

export const history = createBrowserHistory();


const middleWare = [thunk];
const epicMiddleware = createEpicMiddleware();
let composed;
if (process.env.NODE_ENV !== 'production') {
    localStorage.setItem('debug', 'project-insulin:*');
    composed = compose(applyMiddleware(routerMiddleware(history), epicMiddleware, ...middleWare),
        window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__());
} else {
    composed = compose(applyMiddleware(routerMiddleware(history), epicMiddleware, ...middleWare));
}
export const store = createStore(rootReducers(history), composed);


const routing = (
    <BrowserRouter>
        <Provider store={store}>
            <App history={history}/>
        </Provider>
    </BrowserRouter>
);

// this will work same middle ware but this is required for observable action and really useful
epicMiddleware.run(rootEpic);
ReactDOM.render(routing, document.getElementById('root'));
