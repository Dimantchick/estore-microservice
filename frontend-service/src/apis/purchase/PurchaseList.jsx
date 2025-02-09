import {Datagrid, DateField, List, ReferenceField, ShowButton, TextField} from 'react-admin';

export const PurchaseList = () => (
    <List>
        <Datagrid>
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
            <ShowButton label="Просмотреть"/>
        </Datagrid>
    </List>
);
