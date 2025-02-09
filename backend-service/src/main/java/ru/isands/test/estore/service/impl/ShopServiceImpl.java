package ru.isands.test.estore.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.isands.test.estore.dao.entity.Shop;
import ru.isands.test.estore.dao.repo.ShopRepository;
import ru.isands.test.estore.service.ShopService;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    private final ObjectMapper objectMapper;

    @Override
    public Page<Shop> getAll(Pageable pageable) {
        return shopRepository.findAll(pageable);
    }

    @Override
    public Shop getOne(Long id) {
        Optional<Shop> shopOptional = shopRepository.findById(id);
        return shopOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entity with id `%s` not found", id)));
    }

    @Override
    public List<Shop> getMany(List<Long> ids) {
        return shopRepository.findAllById(ids);
    }

    @Override
    public Shop create(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public Shop patch(Long id, JsonNode patchNode) throws IOException {
        Shop shop = shopRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entity with id `%s` not found", id)));

        objectMapper.readerForUpdating(shop).readValue(patchNode);

        return shopRepository.save(shop);
    }

    @Override
    public List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException {
        Collection<Shop> shops = shopRepository.findAllById(ids);

        for (Shop shop : shops) {
            objectMapper.readerForUpdating(shop).readValue(patchNode);
        }

        List<Shop> resultShops = shopRepository.saveAll(shops);
        return resultShops.stream()
                .map(Shop::getId)
                .collect(Collectors.toList());
    }

    @Override
    public Shop delete(Long id) {
        Shop shop = shopRepository.findById(id).orElse(null);
        if (shop != null) {
            shopRepository.delete(shop);
        }
        return shop;
    }

    @Override
    public void deleteMany(List<Long> ids) {
        shopRepository.deleteAllById(ids);
    }
}
