import React from "react";

import {
    Create,
    DateInput,
    RadioButtonGroupInput,
    ReferenceInput,
    SelectInput,
    SimpleForm,
    TextInput,
} from "react-admin";
import {Consts} from "../Consts";
import {EmployeeConsts} from "./EmployeeConsts";

export const EmployeeCreate = (props) => {

    return (
        <Create {...props}>
            <SimpleForm>
                <TextInput source="lastName" validate={Consts.validateRequired} label="Фамилия"/>
                <TextInput source="firstName" validate={Consts.validateRequired} label="Имя"/>
                <TextInput source="patronymic" validate={Consts.validateRequired} label="Отчество"/>
                <DateInput source="birthDate" validate={Consts.validateRequired} label="Дата рождения"/>
                <ReferenceInput source="positionId" reference="positionType">
                    <SelectInput source="positionId" validate={Consts.validateRequired} label="Должность"/>
                </ReferenceInput>
                <ReferenceInput source="shopId" reference="shop">
                    <SelectInput source="shopId" validate={Consts.validateRequired} label="Магазин"/>
                </ReferenceInput>
                <RadioButtonGroupInput source="gender" choices={EmployeeConsts.genderChoises} defaultValue={true}
                                       label="Пол"/>
            </SimpleForm>
        </Create>
    );
};