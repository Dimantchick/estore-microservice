package ru.isands.test.estore.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.isands.test.estore.dao.entity.PositionType;

import java.io.IOException;
import java.util.List;

public interface PositionTypeService {
    Page<PositionType> getAll(Pageable pageable);

    PositionType getOne(Long id);

    List<PositionType> getMany(List<Long> ids);

    PositionType create(PositionType positionType);

    PositionType patch(Long id, JsonNode patchNode) throws IOException;

    List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException;

    PositionType delete(Long id);

    void deleteMany(List<Long> ids);
}
