import {Datagrid, List, ShowButton, TextField} from 'react-admin';

export const ShopList = () => (
    <List>
        <Datagrid>
            <TextField source="id" label="ИД"/>
            <TextField source="name" label="Наименование"/>
            <TextField source="address" label="Адрес"/>
            <ShowButton label="Просмотреть"/>
        </Datagrid>
    </List>
);
