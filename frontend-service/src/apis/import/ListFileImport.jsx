import {useNotify} from "react-admin";
import {Box, Typography} from "@mui/material";

export const ListFileImport = (props) => {
    // const redirect = useRedirect();
    // useEffect(() => {
    //     redirect('/employee');
    // }, []);

    const notify = useNotify();
    notify("Архив успешно импортирован");


    return (
        <Box padding={'20px'} textAlign={'center'}>
            <Typography variant="h4">Архив успешно импортирован</Typography>
        </Box>
    );
};
