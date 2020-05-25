import React from 'react';
import {Link, NavLink} from 'react-router-dom';
import {path} from "../../navigation/path";
import {connect} from "react-redux";
import {LogOutNavigationAction} from "../../navigation/navigation.action";

const SignedInLinks = (props) => {
    const logOutNavigation = () => props.dispatch(LogOutNavigationAction());
    return (
        <ul className="right">
            <li><NavLink to={path.home}> Track Insulin </NavLink></li>
            <li onClick={logOutNavigation}><a>Log Out</a></li>
            <li><NavLink to="/" className="btn btn-floating pink lighten-1"> AV </NavLink></li>
        </ul>
    )
};
const mapStateToProps = state => ({});

export default connect(mapStateToProps)(SignedInLinks);
