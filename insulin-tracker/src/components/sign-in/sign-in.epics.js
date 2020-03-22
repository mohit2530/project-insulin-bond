import {combineEpics, ofType} from "redux-observable";
import {SIGN_IN, SIGN_IN_SUCCEED, SignInFailedAction, SignInSucceedAction} from "./sign-in.actions";
import {catchError, exhaustMap, map, mapTo} from "rxjs/operators";
import {CustomerService} from "../../core/api/customer-service";
import {merge, of} from "rxjs";
import { push } from 'connected-react-router'
import {path} from "../../navigation/path";

export default function signInEpics() {
    return combineEpics(
        function signIn$(action$) {
            return action$.pipe(
                ofType(SIGN_IN),
                exhaustMap(({payload}) => {
                    return signInUser(payload)
                    }
                ),
                catchError((error, caught) => merge(
                    of(SignInFailedAction()),
                    caught,
                )),
            );
        },
        function signInComplete$(action$) {
            return action$.pipe(
                ofType(SIGN_IN_SUCCEED),
                mapTo(push(path.signUp))
            )
        }
    );
}

function signInUser(payload) {
    return CustomerService.signIn(payload).pipe(
        map(response => SignInSucceedAction(response.data)),
    );
}

