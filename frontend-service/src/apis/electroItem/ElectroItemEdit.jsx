import {
    BooleanInput,
    Edit,
    NumberInput,
    ReferenceInput,
    SelectInput,
    SimpleForm,
    TextInput
} from 'react-admin';
import React from "react";
import {Consts} from "../Consts";

export const ElectroItemEdit = () => (
    <Edit>
        <SimpleForm>
            <TextInput source="id" readOnly={true} label="ИД"/>
            <TextInput source="name" validate={Consts.validateRequired} label="Название"/>
            <ReferenceInput source="etypeId" reference="electroType">
                <SelectInput validate={Consts.validateRequired} label="Тип"/>
            </ReferenceInput>
            <NumberInput source="price" validate={Consts.validateRequired} label="Цена"/>
            <NumberInput source="count" validate={Consts.validateRequired} label="Количество"/>
            <BooleanInput source="archive" label="Признак архивности"/>
            <TextInput multiline={true} source="description" validate={Consts.validateRequired} label="Описание"/>
        </SimpleForm>
    </Edit>
);