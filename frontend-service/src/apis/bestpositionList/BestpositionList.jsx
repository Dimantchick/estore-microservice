import {Datagrid, List, NumberField, ReferenceField, TextField} from 'react-admin';
import {Box, Typography} from "@mui/material";

export const BestpositionList = () => (
    <Box paddingTop={'20px'} textAlign={'center'}>
        <Typography variant="h4">Лучшие по продажам за год</Typography>

        <List pagination={null} filters={null} sort={null}>
            <Datagrid bulkActionButtons={false}>
                <ReferenceField source="id" reference="positionType" label="Должность" sortable={false} />
                <ReferenceField source="salesEmployeeId" reference="employee" label="Лучший по кол-ву" sortable={false}>
                    <TextField source="firstName"/>{' '}
                    <TextField source="lastName"/>{' '}
                    <TextField source="patronymic"/>
                </ReferenceField>
                <NumberField source="sales" label="Кол-во" sortable={false}/>
                <ReferenceField source="sumEmployeeId" reference="employee" label="Лучший по сумме" sortable={false}>
                    <TextField source="firstName"/>{' '}
                    <TextField source="lastName"/>{' '}
                    <TextField source="patronymic"/>
                </ReferenceField>
                <NumberField source="sumSales" label="Сумма" sortable={false}/>
            </Datagrid>
        </List>
    </Box>
);