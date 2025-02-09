import React from "react";

import {
    BooleanInput,
    Create,
    NumberInput,
    ReferenceInput,
    SelectInput,
    SimpleForm,
    TextInput,
} from "react-admin";
import {Consts} from "../Consts";

export const ElectroItemCreate = (props) => {

    return (
        <Create {...props}>
            <SimpleForm>
                <TextInput source="name" validate={Consts.validateRequired} label="Название"/>
                <ReferenceInput source="etypeId" reference="electroType">
                    <SelectInput validate={Consts.validateRequired} label="Тип"/>
                </ReferenceInput>
                <NumberInput source="price" validate={Consts.validateRequired} label="Цена"/>
                <NumberInput source="count" validate={Consts.validateRequired} label="Количество"/>
                <BooleanInput source="archive" label="Признак архивности"/>
                <TextInput multiline={true} source="description" validate={Consts.validateRequired} label="Описание"/>
            </SimpleForm>
        </Create>
    );
};