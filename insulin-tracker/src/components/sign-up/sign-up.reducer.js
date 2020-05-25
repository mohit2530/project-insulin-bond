import {SignUpModel} from "./sign-up.model";
import * as actions from './sign-up.actions';

const initialState = {
    user: SignUpModel,
    loading: false
};

function reducer(state = initialState, action) {
    switch (action.type) {
        case actions.SIGN_UP:
            return {...state, user: action.payload, loading: true};
        case actions.SIGN_UP_SUCCEED:
            return {...state, loading: false};
        default:
            return state;
    };
}

export const SignUp = reducer;

export const getState = state => state.SignUp;
