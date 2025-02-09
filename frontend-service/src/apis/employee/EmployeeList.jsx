import {Datagrid, DateField, List, ReferenceField, SelectField, ShowButton, TextField} from 'react-admin';
import {EmployeeConsts} from "./EmployeeConsts";

export const EmployeeList = () => (
    <List>
        <Datagrid>
            <TextField source="id" label="ИД"/>
            <TextField source="lastName" label="Фамилия"/>
            <TextField source="firstName" label="Имя"/>
            <TextField source="patronymic" label="Отчество"/>
            <DateField source="birthDate" label="Дата рождения"/>
            <ReferenceField source="positionId" reference="positionType" label="Должность"/>
            <ReferenceField source="shopId" reference="shop" label="Магазин"/>
            <SelectField source="gender" choices={EmployeeConsts.genderChoises} label="Пол"/>
            <ShowButton label="Просмотреть"/>
        </Datagrid>
    </List>
);
