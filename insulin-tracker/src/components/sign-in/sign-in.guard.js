import {useEffect} from "react";
import {SignInLoadedAction} from "./sign-in.actions";

const SignInGuard = (props) => {
    useEffect(() => {
            props.dispatch(SignInLoadedAction())
        }, []
    );
};

export default SignInGuard;
