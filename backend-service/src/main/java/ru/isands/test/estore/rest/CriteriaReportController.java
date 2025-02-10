package ru.isands.test.estore.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.isands.test.estore.dto.CriteriasReportDTO;
import ru.isands.test.estore.service.CriteriaReportService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/estore/api/criteriaReport")
@RequiredArgsConstructor
public class CriteriaReportController {


    private final CriteriaReportService criteriaReportService;

    @GetMapping()
    public List<CriteriasReportDTO> getReport() {
        return Collections.singletonList(criteriaReportService.getCriteriasReport());
    }
}
