package ru.isands.test.estore.service;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {

    void saveUnzippedFile(MultipartFile file);

    Path getRootLocation();

    void clean();
}
