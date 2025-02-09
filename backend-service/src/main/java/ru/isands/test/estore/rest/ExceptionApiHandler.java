package ru.isands.test.estore.rest;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import ru.isands.test.estore.dao.ErrorMessageDTO;

@Slf4j
@RestControllerAdvice
public class ExceptionApiHandler {
    @ExceptionHandler(HibernateException.class)
    public ResponseEntity<ErrorMessageDTO> handleHibernateException(HibernateException ex) {
        log.error("Ошибка импорта.", ex);
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorMessageDTO("Невозможно импортировать архив. Ошибка целостности данных."));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorMessageDTO> handleResponseStatusException(ResponseStatusException ex) {
        log.error("Ошибка статуса", ex);
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErrorMessageDTO(ex.getReason()));
    }
}
