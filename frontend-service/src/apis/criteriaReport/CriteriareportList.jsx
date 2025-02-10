import {List, WithListContext} from 'react-admin';
import {Box, Typography} from "@mui/material";

export const Report = () => (
    <WithListContext render={({data, isPending}) =>
        (
            <Box paddingTop={'20px'} textAlign={'center'}>
                <Typography variant="h4">Отчет по криетриям</Typography>
                <Typography variant="h5">&nbsp;</Typography>
                <Typography variant="body2">
                    Лучший младший продавец-консультант, продавший больше всех умных часов: {data ? data[0].id : ""}
                </Typography>
                <Typography variant="body2">
                    Сумма денежных средств, полученная магазином через оплату наличными: {data ? data[0].sum : ""}

                </Typography>
                <Typography variant="h5">&nbsp;</Typography>
            </Box>
        )}/>
);

export const CriteriareportList = () => (
    <List pagination={null} title={null} bulkActionButtons={false} actions={null}>
        <Report/>
    </List>
);