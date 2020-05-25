import { SignUpModel } from "./sign-up.model";

const actionType = '[Sign Up]';
export const SIGN_UP = `${actionType} Sign Up`;
export const SIGN_UP_SUCCEED = `${actionType} Sign Up Succeed`;
export const SIGN_UP_FAILED = `${actionType} Sign Up Failed`;

export const SignUpAction = (payload) => ({
        type: SIGN_UP,
        payload
});

export const SignUpSucceedAction = () => ({
   type: SIGN_UP_SUCCEED,
});

export const SignUpFailedAction = (payload) => ({
    type: SIGN_UP_FAILED,
    payload
});
