package ru.isands.test.estore.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.isands.test.estore.dao.ErrorMessageDTO;
import ru.isands.test.estore.service.ImportService;

@Slf4j
@RestController
@RequestMapping("/estore/api/import")
@RequiredArgsConstructor
@RestControllerAdvice
public class ImportController {

    private final ImportService importService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestParam("file") MultipartFile file) {
        importService.importFile(file);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(HibernateException.class)
    public ResponseEntity<ErrorMessageDTO> handleHibernateException(HibernateException ex) {
        log.error("Ошибка импорта.", ex);
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorMessageDTO("Невозможно импортировать архив. Ошибка целостности данных."));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorMessageDTO> handleResponseStatusException(ResponseStatusException ex) {
        log.error("Ошибка импорта.", ex);
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErrorMessageDTO(ex.getReason()));
    }

}
