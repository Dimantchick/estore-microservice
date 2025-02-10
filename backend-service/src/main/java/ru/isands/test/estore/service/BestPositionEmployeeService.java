package ru.isands.test.estore.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.isands.test.estore.dto.BestPositionEmployeeDTO;

public interface BestPositionEmployeeService {
    Page<BestPositionEmployeeDTO> getBestPositionEmployee(Pageable pageable) ;
}
