import {Datagrid, List, ShowButton, TextField} from 'react-admin';

export const PositionTypeList = () => (
    <List>
        <Datagrid>
            <TextField source="id" label="ИД" />
            <TextField source="name" label="Наименование" />
            <ShowButton label="Просмотреть" />
        </Datagrid>
    </List>
);
