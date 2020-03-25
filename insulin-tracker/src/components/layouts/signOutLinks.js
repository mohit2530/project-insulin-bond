import React from 'react';
import Log from '../../lib/log';
import { NavLink } from 'react-router-dom';

const SignedOutLinks = () => {
    Log.info("Navigation Bar Initialized", "Navigation Component");
    return (
        <ul className="right">
            <li> <NavLink to="/"> Register </NavLink></li>
            <li> <NavLink to="/"> Log In </NavLink></li>
        </ul>
    )
}

export default SignedOutLinks;