import React from "react";

import {Create, SimpleForm, TextInput,} from "react-admin";
import {Consts} from "../Consts";

export const PositionTypeCreate = (props) => {

    return (
        <Create {...props}>
            <SimpleForm>
                <TextInput source="name" validate={Consts.validateRequired} label="Наименование"/>
            </SimpleForm>
        </Create>
    );
};