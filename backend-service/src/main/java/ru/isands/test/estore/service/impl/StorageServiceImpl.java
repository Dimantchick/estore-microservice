package ru.isands.test.estore.service.impl;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.isands.test.estore.service.StorageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path rootLocation;

    public StorageServiceImpl() throws IOException {
        rootLocation = Paths.get("./temp");
        Files.createDirectories(rootLocation);
    }

    @Override
    public void saveUnzippedFile(MultipartFile file) {
        try {
            // Удалить все содержимое
            FileUtils.cleanDirectory(rootLocation.toFile());

            try (ZipInputStream inputStream = new ZipInputStream(file.getInputStream());) {
                for (ZipEntry entry; (entry = inputStream.getNextEntry()) != null; ) {
                    Path resolvedPath = rootLocation.resolve(entry.getName());
                    if (!entry.isDirectory()) {
                        Files.createDirectories(resolvedPath.getParent());
                        Files.copy(inputStream, resolvedPath);
                    } else {
                        Files.createDirectories(resolvedPath);
                    }
                }
            }
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Ошибка ввода вывода при обработке файла", e);
        }
    }

    @Override
    public Path getRootLocation() {
        return rootLocation;
    }

    @Override
    public void clean() {
        try {
            FileUtils.cleanDirectory(rootLocation.toFile());
        } catch (IOException ignored) {
        }
    }
}
