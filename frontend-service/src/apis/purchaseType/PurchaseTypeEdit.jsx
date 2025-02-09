import {Edit, SimpleForm, TextInput} from 'react-admin';
import {Consts} from "../Consts";

export const PurchaseTypeEdit = () => (
    <Edit>
        <SimpleForm>
            <TextInput source="id" readOnly={true} label="ИД"/>
            <TextInput source="name" validate={Consts.validateRequired} label="Наименование"/>
        </SimpleForm>
    </Edit>
);