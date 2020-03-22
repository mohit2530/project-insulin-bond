import {combineEpics, ofType} from "redux-observable";
import {SIGN_UP, SignUpFailedAction, SignUpSucceedAction} from "./sign-up.actions";
import {catchError, exhaustMap, map} from "rxjs/operators";
import {CustomerService} from "../../core/api/customer-service";
import {SignInSucceedAction} from "../sign-in/sign-in.actions";
import {concat, merge, of} from "rxjs";

export default function signUpEpic() {
    return combineEpics(
        function signUp$(action$) {
            return action$.pipe(
                ofType(SIGN_UP),
                exhaustMap(({payload}) => concat(
                    signUp(payload),
                    of(SignUpSucceedAction())
                )),
                catchError((err, caught) => merge(
                    of(SignUpFailedAction(err)),
                    caught))
            )
        }
    )
}

function signUp(payload) {
    return CustomerService.signUp(payload).pipe(
        map(({data}) => {
            return SignInSucceedAction(data);
        })
    )
}
