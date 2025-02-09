import React from "react";

import {Create, SimpleForm, TextInput,} from "react-admin";
import {Consts} from "../Consts";

export const ShopCreate = (props) => {

    return (
        <Create {...props}>
            <SimpleForm>
                <TextInput source="name" validate={Consts.validateRequired} label="Наименование"/>
                <TextInput source="address" validate={Consts.validateRequired} label="Адрес"/>
            </SimpleForm>
        </Create>
    );
};