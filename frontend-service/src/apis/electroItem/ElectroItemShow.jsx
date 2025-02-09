import {BooleanField, NumberField, ReferenceField, Show, SimpleShowLayout, TextField} from 'react-admin';

export const ElectroItemShow = () => (
    <Show>
        <SimpleShowLayout>
            <TextField source="id" label="ИД"/>
            <TextField source="name" label="Название"/>
            <ReferenceField source="etypeId" reference="electroType" label="Тип"/>
            <NumberField source="price" label="Цена"/>
            <NumberField source="count" label="Количество"/>
            <BooleanField source="archive" label="Признак архивности"/>
            <TextField source="description" label="Описание"/>
        </SimpleShowLayout>
    </Show>
);