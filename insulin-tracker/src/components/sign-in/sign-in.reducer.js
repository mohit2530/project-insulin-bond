import {SignInModel} from "./sign-in.model";
import * as actions from "./sign-in.actions"
import axios from 'axios';
import { createSelector } from 'reselect'
import {LOG_OUT_NAVIGATION} from "../../navigation/navigation.action";


const initialState = {
  user: getUsername(),
  signIn: SignInModel
};

function reducer(state = initialState, action) {
    switch (action.type) {
        case actions.SIGN_IN_SUCCEED:
            localStorage.setItem('user', JSON.stringify(action.payload));
            axios.defaults.headers.common.Authorization = `Bearer ${action.payload.token}`;
            return {...state, user: action.payload};

        case actions.SIGN_IN:
            return {...state, signIn: action.payload};

        case LOG_OUT_NAVIGATION:
            return {...state, user: ''};

        default:
            return state;
    }
}

export const SignIn = reducer;

const getState = state => state.SignIn;

export const getStateUsername = createSelector(
    [getState],
    state => !!state.user && state.user
);

export function getUsername() {
    const username = localStorage.getItem('user');
    return !!username ? username.substr(1, username.length - 2) : '';
}
