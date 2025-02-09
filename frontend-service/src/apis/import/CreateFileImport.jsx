import {Create, Edit, FileInput, SimpleForm} from "react-admin";

export const CreateFileImport = () => {
    return (
        <Create>
            <SimpleForm>
                <FileInput source="file" placeholder={<p>Перетащите файл сюда</p>}>
                </FileInput>
            </SimpleForm>
        </Create>
    )
}