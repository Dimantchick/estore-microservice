package ru.isands.test.estore.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.isands.test.estore.service.ImportService;

@Slf4j
@RestController
@Tag(name = "Import", description = "Сервис для импорта данных")
@RequestMapping("/estore/api/import")
@RequiredArgsConstructor
public class ImportController {

    private final ImportService importService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "Импорт архива данных", parameters = {
                @Parameter(description = "Архив импорта", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
    })
    public ResponseEntity<Void> create(@RequestParam("file") MultipartFile file) {
        importService.importFile(file);
        return ResponseEntity.ok().build();
    }
}
