import {Show, SimpleShowLayout, TextField} from 'react-admin';

export const PositionTypeShow = () => (
    <Show>
        <SimpleShowLayout>
            <TextField source="id" label="ИД"/>
            <TextField source="name" label="Наименование"/>
        </SimpleShowLayout>
    </Show>
);