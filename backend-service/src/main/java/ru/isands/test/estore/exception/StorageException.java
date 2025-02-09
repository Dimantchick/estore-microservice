package ru.isands.test.estore.exception;

import java.io.IOException;

public class StorageException extends RuntimeException {
    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, IOException e) {
        super(message, e);
    }
}
