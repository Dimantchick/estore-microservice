import {DateField, ReferenceField, SelectField, Show, SimpleShowLayout, TextField} from 'react-admin';
import {EmployeeConsts} from "./EmployeeConsts";

export const EmployeeShow = () => (
    <Show>
        <SimpleShowLayout>
            <TextField source="id" label="ИД"/>
            <TextField source="lastName" label="Фамилия"/>
            <TextField source="firstName" label="Имя"/>
            <TextField source="patronymic" label="Отчество"/>
            <DateField source="birthDate" label="Дата рождения"/>
            <ReferenceField source="positionId" reference="positionType" label="Должность"/>
            <ReferenceField source="shopId" reference="shop" label="Магазин"/>
            <SelectField source="gender" choices={EmployeeConsts.genderChoises} label="Пол"/>
        </SimpleShowLayout>
    </Show>
);