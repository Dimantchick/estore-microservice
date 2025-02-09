import {BooleanField, Datagrid, List, NumberField, ReferenceField, ShowButton, TextField} from 'react-admin';

export const ElectroItemList = () => (
    <List>
        <Datagrid>
            <TextField source="id" label="ИД"/>
            <TextField source="name" label="Название"/>
            <ReferenceField source="etypeId" reference="electroType" label="Тип"/>
            <NumberField source="price" label="Цена"/>
            <NumberField source="count" label="Количество"/>
            <BooleanField source="archive" label="Признак архивности"/>
            <TextField source="description" label="Описание"/>
            <ShowButton label="Просмотреть"/>
        </Datagrid>
    </List>
);