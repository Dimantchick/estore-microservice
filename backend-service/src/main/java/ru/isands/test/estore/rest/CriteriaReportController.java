package ru.isands.test.estore.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.isands.test.estore.dto.CriteriasReportDTO;
import ru.isands.test.estore.service.CriteriaReportService;

import java.util.Collections;
import java.util.List;

@RestController
@Tag(name = "CriteriaReport", description = "Сервис для получения информации по определенным критериям")
@RequestMapping("/estore/api/criteriaReport")
@RequiredArgsConstructor
public class CriteriaReportController {


    private final CriteriaReportService criteriaReportService;

    @GetMapping()
    @Operation(summary = "Получение информации по определенным критериям", responses = {
            @ApiResponse(description = "Список информации по определенным критериям")
    })
    public List<CriteriasReportDTO> getReport() {
        return Collections.singletonList(criteriaReportService.getCriteriasReport());
    }
}
