import {Menu, Resource, ShowGuesser} from 'react-admin';
import {Collapse} from "@mui/material";
import React from "react";
import {ViewList, Label} from "@mui/icons-material";
import ExpandLessIcon from '@mui/icons-material/ExpandLess';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import FileUploadIcon from "@mui/icons-material/FileUpload";

export const MyMenu = () => {
    const padded = {
        paddingLeft: '10px'
    };
    const [open, setOpen] = React.useState(true);

    const handleClick = () => {
        setOpen(!open);
    };
    return (<Menu>
            <Menu.ResourceItem name="employee"/>
            <Menu.ResourceItem name="electroItem"/>
            <Menu.ResourceItem name="purchase"/>
            <Menu.Item primaryText="Справочники" to="#" onClick={handleClick}
                       leftIcon={open ? <ExpandLessIcon/> : <ExpandMoreIcon/>}>

            </Menu.Item>
            <Collapse in={open} timeout="auto" unmountOnExit style={padded}>
                <Menu.ResourceItem name="positionType"/>
                <Menu.ResourceItem name="electroType"/>
                <Menu.ResourceItem name="shop"/>
                <Menu.ResourceItem name="purchaseType"/>
            </Collapse>
            <Menu.Item to="/import/create" primaryText="Импорт архива" leftIcon={<FileUploadIcon/>}/>
            {/*<Menu.ResourceItem name="import"/>*/}
            {/*<Menu.Item to="/file-import" primaryText="Импорт архива" leftIcon={<FileUploadIcon/>}/>*/}
        </Menu>
    );
}