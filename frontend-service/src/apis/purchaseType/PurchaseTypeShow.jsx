import {Show, SimpleShowLayout, TextField} from 'react-admin';

export const PurchaseTypeShow = () => (
    <Show>
        <SimpleShowLayout>
            <TextField source="id" label="ИД"/>
            <TextField source="name" label="Наименование"/>
        </SimpleShowLayout>
    </Show>
);