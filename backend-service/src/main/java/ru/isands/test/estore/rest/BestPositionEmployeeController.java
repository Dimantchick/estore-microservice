package ru.isands.test.estore.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.isands.test.estore.dto.BestPositionEmployeeDTO;
import ru.isands.test.estore.service.BestPositionEmployeeService;

@RestController
@Tag(name = "BestPositionEmployee", description = "Сервис для получения информации о лучших сотрудниках")
@RequestMapping("/estore/api/bestPosition")
@RequiredArgsConstructor
public class BestPositionEmployeeController {

    private final BestPositionEmployeeService bestPositionEmployeeService;

    @GetMapping
    @Operation(summary = "Получение лучших сотрудников постранично", responses = {
            @ApiResponse(description = "Список лучших сотрудников постранично")
    })
    public Page<BestPositionEmployeeDTO> getAll(Pageable pageable) {
        return bestPositionEmployeeService.getBestPositionEmployee(pageable);
    }
}
