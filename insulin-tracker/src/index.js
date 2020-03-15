import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';

/**
 * Initializing Logging for developmental usage. For this to work, it is important to set Local Storage under applications to debug and pass the project name with an * after deploying the application.
 * We are not using it in production env, because prod is minimized and it doesn't suffice the reasons of logging at least here.
 */

if ( process.env.NODE_ENV !== 'production') {
    localStorage.setItem('debug', 'project-insulin:*');
}


ReactDOM.render(<App/>, document.getElementById('root'));