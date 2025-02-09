package ru.isands.test.estore.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.isands.test.estore.dao.entity.ElectroItem;

import java.io.IOException;
import java.util.List;

public interface ElectroItemService {
    Page<ElectroItem> getAll(Pageable pageable);

    ElectroItem getOne(Long id);

    List<ElectroItem> getMany(List<Long> ids);

    ElectroItem create(ElectroItem electroItem);

    ElectroItem patch(Long id, JsonNode patchNode) throws IOException;

    List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException;

    ElectroItem delete(Long id);

    void deleteMany(List<Long> ids);
}
