import {Datagrid, List, ShowButton, TextField} from 'react-admin';

export const ElectroTypeList = () => (
    <List>
        <Datagrid>
            <TextField source="id" label="ИД" />
            <TextField source="name" label="Наименование" />
            <ShowButton label="Просмотреть" />
        </Datagrid>
    </List>
);
