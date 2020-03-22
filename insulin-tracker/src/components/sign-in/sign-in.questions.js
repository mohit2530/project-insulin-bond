export const signInQuestion = (values, errors) => {
    return {
        username: {
            value: values.username,
            key: 'username',
            label: 'Username',
            error: errors.username
        },
        password: {
            value: values.password,
            key: 'password',
            type: 'password',
            label: 'Password',
            error: errors.password
        }
    };
};
