import {combineEpics, ofType} from "redux-observable";
import {LOG_OUT_NAVIGATION, SIGN_IN_NAVIGATION, SIGN_UP_NAVIGATION} from "./navigation.action";
import {catchError, exhaustMap, map, mapTo, tap} from "rxjs/operators";
import {path} from "./path";
import {push} from 'connected-react-router'
import {EMPTY, merge, of} from "rxjs";
import {CustomerService} from "../core/api/customer-service";

export default function navigationEpics() {
    return combineEpics(
        function signInNavigation$(action$) {
            return action$.pipe(
                ofType(SIGN_IN_NAVIGATION),
                mapTo(push(path.signIn))
            );
        },
        function signUpNavigation$(action$) {
            return action$.pipe(
                ofType(SIGN_UP_NAVIGATION),
                mapTo(push(path.signUp))
            );
        },
        function logOutNavigation$(action$) {
            return action$.pipe(
                ofType(LOG_OUT_NAVIGATION),
                exhaustMap(() => logout()),
                catchError((error, caught) => merge(
                    EMPTY,
                    caught
                ))
            );
        },
    );

}

function logout() {
    return CustomerService.logout().pipe(
        map(() => of(localStorage.removeItem('user'))),
        mapTo(push(path.home))
    );
}
