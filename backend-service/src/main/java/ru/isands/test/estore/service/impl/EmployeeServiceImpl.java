package ru.isands.test.estore.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.isands.test.estore.dao.entity.Employee;
import ru.isands.test.estore.dao.repo.EmployeeRepository;
import ru.isands.test.estore.service.EmployeeService;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final ObjectMapper objectMapper;

    @Override
    public Page<Employee> getAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee getOne(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entity with id `%s` not found", id)));
    }

    @Override
    public List<Employee> getMany(List<Long> ids) {
        return employeeRepository.findAllById(ids);
    }

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee patch(Long id, JsonNode patchNode) throws IOException {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entity with id `%s` not found", id)));

        objectMapper.readerForUpdating(employee).readValue(patchNode);

        return employeeRepository.save(employee);
    }

    @Override
    public List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException {
        Collection<Employee> employees = employeeRepository.findAllById(ids);

        for (Employee employee : employees) {
            objectMapper.readerForUpdating(employee).readValue(patchNode);
        }

        List<Employee> resultEmployees = employeeRepository.saveAll(employees);
        return resultEmployees.stream()
                .map(Employee::getId)
                .collect(Collectors.toList());
    }

    @Override
    public Employee delete(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            employeeRepository.delete(employee);
        }
        return employee;
    }

    @Override
    public void deleteMany(List<Long> ids) {
        employeeRepository.deleteAllById(ids);
    }
}
