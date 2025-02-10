package ru.isands.test.estore.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.isands.test.estore.dto.BestPositionEmployeeDTO;
import ru.isands.test.estore.service.BestPositionEmployeeService;

@RestController
@RequestMapping("/estore/api/bestPosition")
@RequiredArgsConstructor
public class BestPositionEmployeeController {

    private final BestPositionEmployeeService bestPositionEmployeeService;

    @GetMapping
    public Page<BestPositionEmployeeDTO> getAll(Pageable pageable) {
        return bestPositionEmployeeService.getBestPositionEmployee(pageable);
    }
}
