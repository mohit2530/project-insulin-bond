import {SignInModel} from "./sign-in.model";

const actionType = '[Sign In]';
export const SIGN_IN = `${actionType} Sign In`;
export const SIGN_IN_SUCCEED = `${actionType} Sign In Succeed`;
export const SIGN_IN_FAILED = `${actionType} Sign In Failed`;
export const SIGN_IN_LOADED = `${actionType} Sign In Loaded`;

export const SignInAction = (payload = SignInModel) => ({
    type: SIGN_IN,
    payload
});

export const SignInSucceedAction = (payload) => ({
    type: SIGN_IN_SUCCEED,
    payload
});

export const SignInFailedAction = (payload) => ({
    type: SIGN_IN_FAILED,
    payload
});

export const SignInLoadedAction = () => ({
    type: SIGN_IN_LOADED
});


