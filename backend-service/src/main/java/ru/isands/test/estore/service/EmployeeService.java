package ru.isands.test.estore.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.isands.test.estore.dao.entity.Employee;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {
    Page<Employee> getAll(Pageable pageable);

    Employee getOne(Long id);

    List<Employee> getMany(List<Long> ids);

    Employee create(Employee employee);

    Employee patch(Long id, JsonNode patchNode) throws IOException;

    List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException;

    Employee delete(Long id);

    void deleteMany(List<Long> ids);
}
