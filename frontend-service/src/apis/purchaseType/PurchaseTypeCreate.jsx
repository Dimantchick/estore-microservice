import React from "react";

import {Create, SimpleForm, TextInput,} from "react-admin";
import {Consts} from "../Consts";

export const PurchaseTypeCreate = (props) => {

    return (
        <Create {...props}>
            <SimpleForm>
                <TextInput source="name" validate={Consts.validateRequired} label="Наименование"/>
            </SimpleForm>
        </Create>
    );
};