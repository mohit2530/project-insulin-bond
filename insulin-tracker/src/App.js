import React from 'react';
import './index.css';
import Log from './lib/log';

class App extends React.Component {

    render() {
        Log.info('React Application Initialized', 'Main Component');
        return (
            <div>
                <h1 className="center"> Project Insulin Bond</h1>
            </div>
        )
    }
}

export default App;