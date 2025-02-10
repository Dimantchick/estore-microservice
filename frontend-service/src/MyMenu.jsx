import {Menu} from 'react-admin';
import {Collapse} from "@mui/material";
import React from "react";
import ExpandLessIcon from '@mui/icons-material/ExpandLess';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import FileUploadIcon from "@mui/icons-material/FileUpload";

export const MyMenu = () => {
    const padded = {
        paddingLeft: '10px'
    };
    const [dictionaryOpen, setDictionaryOpen] = React.useState(true);

    const [reportsOpen, setReportsOpen] = React.useState(true);

    const handleDictionaryClick = () => {
        setDictionaryOpen(!dictionaryOpen);
    };

    const handleReportsClick = () => {
        setReportsOpen(!reportsOpen);
    };
    return (<Menu>
            <Menu.ResourceItem name="employee"/>
            <Menu.ResourceItem name="electroItem"/>
            <Menu.ResourceItem name="purchase"/>
            <Menu.Item primaryText="Справочники" to="#" onClick={handleDictionaryClick}
                       leftIcon={dictionaryOpen ? <ExpandLessIcon/> : <ExpandMoreIcon/>}>

            </Menu.Item>
            <Collapse in={dictionaryOpen} timeout="auto" unmountOnExit style={padded}>
                <Menu.ResourceItem name="positionType"/>
                <Menu.ResourceItem name="electroType"/>
                <Menu.ResourceItem name="shop"/>
                <Menu.ResourceItem name="purchaseType"/>
            </Collapse>

            <Menu.Item primaryText="Отчеты" to="#" onClick={handleReportsClick}
                       leftIcon={reportsOpen ? <ExpandLessIcon/> : <ExpandMoreIcon/>}>

            </Menu.Item>
            <Collapse in={reportsOpen} timeout="auto" unmountOnExit style={padded}>
                <Menu.ResourceItem name="bestPosition"/>
            </Collapse>


            <Menu.Item to="/import/create" primaryText="Импорт архива" leftIcon={<FileUploadIcon/>}/>
        </Menu>
    );
}