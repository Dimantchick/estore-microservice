package ru.isands.test.estore.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.isands.test.estore.dao.entity.Shop;

import java.io.IOException;
import java.util.List;

public interface ShopService {
    Page<Shop> getAll(Pageable pageable);

    Shop getOne(Long id);

    List<Shop> getMany(List<Long> ids);

    Shop create(Shop shop);

    Shop patch(Long id, JsonNode patchNode) throws IOException;

    List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException;

    Shop delete(Long id);

    void deleteMany(List<Long> ids);
}
