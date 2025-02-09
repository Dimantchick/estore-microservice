package ru.isands.test.estore.rest;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dao.entity.Employee;
import ru.isands.test.estore.service.EmployeeService;

import java.io.IOException;
import java.util.List;

@RestController
@Tag(name = "Employee", description = "Сервис для выполнения операций над сотрудниками магазина")
@RequestMapping("/estore/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    @Operation(summary = "Получение сотрудников", responses = {
            @ApiResponse(description = "Список сотрудников")
    })
    public Page<Employee> getAll(Pageable pageable) {
        return employeeService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public Employee getOne(@PathVariable Long id) {
        return employeeService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<Employee> getMany(@RequestParam List<Long> ids) {
        return employeeService.getMany(ids);
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @PatchMapping("/{id}")
    public Employee patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        return employeeService.patch(id, patchNode);
    }

    @PatchMapping
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        return employeeService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public Employee delete(@PathVariable Long id) {
        return employeeService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Long> ids) {
        employeeService.deleteMany(ids);
    }
}
