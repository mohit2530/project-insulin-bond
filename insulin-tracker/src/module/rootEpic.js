import {combineEpics} from "redux-observable";
import signInEpics from "../components/sign-in/sign-in.epics";
import signUpEpic from "../components/sign-up/sign-up.epics";
import navigationEpics from "../navigation/navigation.epics";

export const rootEpic = combineEpics(
    signInEpics(),
    signUpEpic(),
    navigationEpics()
);
