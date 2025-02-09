import {required} from "react-admin";

export const EmployeeConsts = {
    validateRequired: required(),
    genderChoises: [
        {id: true, name: 'Мужской'},
        {id: false, name: 'Женский'},
    ],
    optionRenderer: choice => `${choice.lastName} ${choice.firstName} ${choice.patronymic}`
}