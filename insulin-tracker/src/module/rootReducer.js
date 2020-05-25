import {combineReducers} from "redux";
import {SignIn} from "../components/sign-in/sign-in.reducer";
import {SignUp} from "../components/sign-up/sign-up.reducer";
import { connectRouter } from 'connected-react-router'


export const rootReducers = (history) => combineReducers(
    {
        router: connectRouter(history),
        SignIn,
        SignUp,
    }
);
