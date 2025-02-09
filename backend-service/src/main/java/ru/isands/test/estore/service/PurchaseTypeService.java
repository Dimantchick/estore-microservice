package ru.isands.test.estore.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.isands.test.estore.dao.entity.PurchaseType;

import java.io.IOException;
import java.util.List;

public interface PurchaseTypeService {
    Page<PurchaseType> getAll(Pageable pageable);

    PurchaseType getOne(Long id);

    List<PurchaseType> getMany(List<Long> ids);

    PurchaseType create(PurchaseType purchaseType);

    PurchaseType patch(Long id, JsonNode patchNode) throws IOException;

    List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException;

    PurchaseType delete(Long id);

    void deleteMany(List<Long> ids);
}
