import {Admin, Resource} from "react-admin";
import {dataProvider} from "./dataProvider";
import {ElectroTypeCreate} from "./apis/electroType/ElectroTypeCreate";
import {MyLayout} from "./MyLayout";
import {ElectroTypeEdit} from "./apis/electroType/ElectroTypeEdit";
import {ElectroTypeList} from "./apis/electroType/ElectroTypeList";
import {ElectrotypeShow} from "./apis/electroType/ElectroTypeShow";
import {ElectroItemCreate} from "./apis/electroItem/ElectroItemCreate";
import {ElectroItemList} from "./apis/electroItem/ElectroItemList";
import {ElectroItemEdit} from "./apis/electroItem/ElectroItemEdit";
import {ElectroItemShow} from "./apis/electroItem/ElectroItemShow";
import FaceIcon from '@mui/icons-material/Face';
import BadgeIcon from '@mui/icons-material/Badge';
import InventoryIcon from '@mui/icons-material/Inventory';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import CategoryIcon from '@mui/icons-material/Category';
import StoreIcon from '@mui/icons-material/Store';
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import {ShopEdit} from "./apis/shop/ShopEdit";
import {ShopList} from "./apis/shop/ShopList";
import {ShopShow} from "./apis/shop/ShopShow";
import {ShopCreate} from "./apis/shop/ShopCreate";
import {PurchaseTypeList} from "./apis/purchaseType/PurchaseTypeList";
import {PurchaseTypeShow} from "./apis/purchaseType/PurchaseTypeShow";
import {PurchaseTypeEdit} from "./apis/purchaseType/PurchaseTypeEdit";
import {PurchaseTypeCreate} from "./apis/purchaseType/PurchaseTypeCreate";
import {PositionTypeList} from "./apis/positionType/PositionTypeList";
import {PositionTypeEdit} from "./apis/positionType/PositionTypeEdit";
import {PositionTypeShow} from "./apis/positionType/PositionTypeShow";
import {PositionTypeCreate} from "./apis/positionType/PositionTypeCreate";
import {EmployeeList} from "./apis/employee/EmployeeList";
import {EmployeeShow} from "./apis/employee/EmployeeShow";
import {EmployeeEdit} from "./apis/employee/EmployeeEdit";
import {PurchaseList} from "./apis/purchase/PurchaseList";
import {PurchaseShow} from "./apis/purchase/PurchaseShow";
import {PurchaseEdit} from "./apis/purchase/PurchaseEdit";
import {EmployeeCreate} from "./apis/employee/EmployeeCreate";
import {PurchaseCreate} from "./apis/purchase/PurchaseCreate";
import FileUploadIcon from "@mui/icons-material/FileUpload";
import {CreateFileImport} from "./apis/import/CreateFileImport";
import {ListFileImport} from "./apis/import/ListFileImport";
import StarIcon from '@mui/icons-material/Star';
import {BestpositionList} from "./apis/bestpositionList/BestpositionList";
import {CriteriareportList} from "./apis/criteriaReport/CriteriareportList";

export const App = () => {

    return (
        <Admin
            dataProvider={dataProvider}
            layout={MyLayout}
        >

            {/*Реестры*/}
            <Resource name="employee"
                      options={{label: "Сотрудники"}}
                      edit={EmployeeEdit} list={EmployeeList} show={EmployeeShow} create={EmployeeCreate}
                      recordRepresentation="name"
                      icon={FaceIcon}
            />
            <Resource name="electroItem"
                      options={{label: "Электротовары"}}
                      edit={ElectroItemEdit} list={ElectroItemList} show={ElectroItemShow} create={ElectroItemCreate}
                      recordRepresentation="name"
                      icon={InventoryIcon}
            />
            <Resource name="purchase"
                      options={{label: "Покупки"}}
                      edit={PurchaseEdit} list={PurchaseList} show={PurchaseShow} create={PurchaseCreate}
                      recordRepresentation="name"
                      icon={ShoppingCartIcon}
            />

            {/*Справочники*/}
            <Resource name="positionType"
                      options={{label: "Должности"}}
                      edit={PositionTypeEdit} list={PositionTypeList} show={PositionTypeShow}
                      create={PositionTypeCreate}
                      recordRepresentation="name"
                      icon={BadgeIcon}
            />
            <Resource name="electroType"
                      options={{label: "Типы электроники"}}
                      edit={ElectroTypeEdit} list={ElectroTypeList} show={ElectrotypeShow}
                      recordRepresentation="name" create={ElectroTypeCreate}
                      icon={CategoryIcon}
            />
            <Resource name="shop"
                      options={{label: "Магазины"}}
                      edit={ShopEdit} list={ShopList} show={ShopShow} create={ShopCreate}
                      recordRepresentation="name"
                      icon={StoreIcon}
            />
            <Resource name="purchaseType"
                      options={{label: "Типы покупки"}}
                      edit={PurchaseTypeEdit} list={PurchaseTypeList} show={PurchaseTypeShow}
                      create={PurchaseTypeCreate}
                      recordRepresentation="name"
                      icon={AddShoppingCartIcon}
            />
            {/*Импорт*/}
            <Resource name="import"
                      options={{label: "Импорт архива"}}
                      recordRepresentation="name"
                      icon={FileUploadIcon}
                      create={CreateFileImport}
                      list={ListFileImport}
            />
            {/*Отчеты*/}
            <Resource name="bestPosition"
                      options={{label: "Лучшие по продажам"}}
                      recordRepresentation="name"
                      icon={StarIcon}
                      list={BestpositionList}
                      hasEdit={false}
                      hasShow={false}
                      hasCreate={false}
            />
            <Resource name="criteriaReport"
                      options={{label: "Отчет по критериям"}}
                      recordRepresentation="sum"
                      icon={StarIcon}
                      list={CriteriareportList}
                      hasEdit={false}
                      hasShow={false}
                      hasCreate={false}
            />
        </Admin>
    )
};
