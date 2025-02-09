package ru.isands.test.estore.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.isands.test.estore.dao.entity.Purchase;

import java.io.IOException;
import java.util.List;

public interface PurchaseService {
    Page<Purchase> getAll(Pageable pageable);

    Purchase getOne(Long id);

    List<Purchase> getMany(List<Long> ids);

    Purchase create(Purchase purchase);

    Purchase patch(Long id, JsonNode patchNode) throws IOException;

    List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException;

    Purchase delete(Long id);

    void deleteMany(List<Long> ids);
}
