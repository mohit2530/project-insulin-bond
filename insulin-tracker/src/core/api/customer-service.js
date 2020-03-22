import axios from 'axios';
import {from} from "rxjs";
import {ajax} from "rxjs/ajax";

const api = 'http://localhost:8099/project/ib';

export class CustomerService {
    static signIn = (signInModel) =>
        from(axios.post(`${api}/login`, signInModel));

    static signUp = (signUpModel) =>
        from(axios.post(`${api}/register`, signUpModel));

    static logout = () =>
        from(axios.get(`${api}/logout`));
}
