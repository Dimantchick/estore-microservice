import {Edit, SimpleForm, TextInput} from 'react-admin';
import {Consts} from "../Consts";

export const ShopEdit = () => (
    <Edit>
        <SimpleForm>
            <TextInput source="id" readOnly={true} label="ИД"/>
            <TextInput source="name" validate={Consts.validateRequired} label="Наименование"/>
            <TextInput source="address" validate={Consts.validateRequired} label="Адрес"/>
        </SimpleForm>
    </Edit>
);