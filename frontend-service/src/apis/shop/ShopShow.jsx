import {Show, SimpleShowLayout, TextField} from 'react-admin';

export const ShopShow = () => (
    <Show>
        <SimpleShowLayout>
            <TextField source="id" label="ИД"/>
            <TextField source="name" label="Наименование"/>
            <TextField source="address" label="Адрес"/>
        </SimpleShowLayout>
    </Show>
);