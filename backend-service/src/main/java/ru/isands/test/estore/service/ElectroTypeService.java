package ru.isands.test.estore.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.isands.test.estore.dao.entity.ElectroType;

import java.io.IOException;
import java.util.List;

public interface ElectroTypeService {
    Page<ElectroType> getAll(Pageable pageable);

    ElectroType getOne(Long id);

    List<ElectroType> getMany(List<Long> ids);

    ElectroType create(ElectroType electroType);

    ElectroType patch(Long id, JsonNode patchNode) throws IOException;

    List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException;

    ElectroType delete(Long id);

    void deleteMany(List<Long> ids);
}
