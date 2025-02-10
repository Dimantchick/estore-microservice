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
@Tag(name = "Employee", description = "Сервис для работы с сотрудниками магазина")
@RequestMapping("/estore/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    @Operation(summary = "Получение всех сотрудников постранично", responses = {
            @ApiResponse(description = "Список сотрудников постранично")
    })
    public Page<Employee> getAll(Pageable pageable) {
        return employeeService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение сотрудника по ИД", responses = {
            @ApiResponse(description = "Сотрудник")
    })
    public Employee getOne(@PathVariable Long id) {
        return employeeService.getOne(id);
    }

    @GetMapping("/by-ids")
    @Operation(summary = "Получение сотрудников по их ИД", responses = {
            @ApiResponse(description = "Список сотрудников")
    })
    public List<Employee> getMany(@RequestParam List<Long> ids) {
        return employeeService.getMany(ids);
    }

    @PostMapping
    @Operation(summary = "Добавление сотрудника", responses = {
            @ApiResponse(description = "Сотрудник")
    })
    public Employee create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Частичное изменение сотрудника по ИД", responses = {
            @ApiResponse(description = "Сотрудник")
    })
    public Employee patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        return employeeService.patch(id, patchNode);
    }

    @PatchMapping
    @Operation(summary = "Частичное изменение сотрудников", responses = {
            @ApiResponse(description = "Список ИД сотрудников")
    })
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        return employeeService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление сотрудника по ИД", responses = {
            @ApiResponse(description = "Сотрудник")
    })
    public Employee delete(@PathVariable Long id) {
        return employeeService.delete(id);
    }

    @DeleteMapping
    @Operation(summary = "Удаление сотрудников по ИД")
    public void deleteMany(@RequestParam List<Long> ids) {
        employeeService.deleteMany(ids);
    }
}
