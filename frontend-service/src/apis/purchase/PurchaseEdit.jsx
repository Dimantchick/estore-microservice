import {DateTimeInput, Edit, ReferenceInput, SelectInput, SimpleForm, TextInput} from 'react-admin';
import React from "react";
import {EmployeeConsts} from "../employee/EmployeeConsts";
import {Consts} from "../Consts";

export const PurchaseEdit = () => (
    <Edit>
        <SimpleForm>
            <TextInput source="id" readOnly={true} label="ИД"/>
            <ReferenceInput source="electroId" reference="electroItem">
                <SelectInput source="electroId" validate={Consts.validateRequired} label="Товар"/>
            </ReferenceInput>
            <ReferenceInput source="employeeId" reference="employee">
                <SelectInput source="employeeId" validate={Consts.validateRequired}
                             optionText={EmployeeConsts.optionRenderer} label="Сотрудник"/>
            </ReferenceInput>
            <ReferenceInput source="shopId" reference="shop">
                <SelectInput source="shopId" validate={Consts.validateRequired} label="Магазин"/>
            </ReferenceInput>
            <DateTimeInput source="purchaseDate" validate={Consts.validateRequired} label="Дата/Время"/>
            <ReferenceInput source="typeId" reference="purchaseType">
                <SelectInput source="typeId" validate={Consts.validateRequired} label="Тип"/>
            </ReferenceInput>
        </SimpleForm>
    </Edit>
);