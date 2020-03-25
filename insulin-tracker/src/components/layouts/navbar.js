import React from 'react';
import Log from '../../lib/log';
import { Link } from 'react-router-dom';
import SignedInLinks from './signedInLinks';
import SignedOutLinks from './signOutLinks';

const NavBar = () => {
    Log.info("Navigation Bar Initialized", "Navigation Component");
    return (
        <nav className="nav-wrapper teal darken-3">
            <div className="container">
                <Link to="/" className="brand-logo left">
                    Project Insulin Bond
                </Link>
                <SignedInLinks/>
                <SignedOutLinks/>
            </div>
        </nav>
    )
}

export default NavBar;