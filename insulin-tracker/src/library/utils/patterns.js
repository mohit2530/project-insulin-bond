export const recreateObject = function (obj, key, value, index) {
    let temp = {};
    let i = 0;
    for (let prop in obj) {
        if (obj.hasOwnProperty(prop)) {
            if (i === index && key && value) {
                temp[key] = value;
            }
            temp[prop] = obj[prop];
            i++;
        }
    }
    if (!index && key && (value || !value)) {
        temp[key] = value;
    }
    return temp;
};

export const addToObject = (obj, key, value) => {
    let add = [];
    obj.map(newObj => add.push(recreateObject(newObj, key, value)));
    return add;
};
