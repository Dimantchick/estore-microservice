import React from "react";

import {Create, DateTimeInput, ReferenceInput, SelectInput, SimpleForm,} from "react-admin";
import {Consts} from "../Consts";
import {EmployeeConsts} from "../employee/EmployeeConsts";

export const PurchaseCreate = (props) => {

    return (
        <Create {...props}>
            <SimpleForm>
                <ReferenceInput source="electroId" reference="electroItem" perPage="10000" page="1">
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
        </Create>
    );
};