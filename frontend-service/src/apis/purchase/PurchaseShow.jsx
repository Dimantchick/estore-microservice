import {DateField, ReferenceField, Show, SimpleShowLayout, TextField} from 'react-admin';

export const PurchaseShow = () => (
    <Show>
        <SimpleShowLayout>
            <TextField source="id" label="ИД"/>
            <ReferenceField source="electroId" reference="electroItem" label="Товар"/>
            <ReferenceField source="employeeId" reference="employee" label="Сотрудник">
                <TextField source="firstName"/>{' '}
                <TextField source="lastName"/>{' '}
                <TextField source="patronymic"/>
            </ReferenceField>
            <ReferenceField source="shopId" reference="shop" label="Магазин"/>
            <DateField source="purchaseDate" showTime={true} showDate={true} label="Дата/Время"/>
            <ReferenceField source="typeId" reference="purchaseType" label="Тип"/>
        </SimpleShowLayout>
    </Show>
);