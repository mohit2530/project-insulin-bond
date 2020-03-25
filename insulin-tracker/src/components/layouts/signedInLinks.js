import React from 'react';
import Log from '../../lib/log';
import { NavLink } from 'react-router-dom';

const SignedInLinks = () => {
    Log.info("Navigation Bar Initialized", "Navigation Component");
    return (
        <ul className="right">
            <li> <NavLink to="/"> Track Insulin </NavLink></li>
            <li> <NavLink to="/"> Log Out </NavLink></li>
            <li> <NavLink to="/"className="btn btn-floating pink lighten-1" > AV </NavLink></li>
        </ul>
    )
}

export default SignedInLinks;