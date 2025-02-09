package ru.isands.test.estore.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.isands.test.estore.dao.entity.Purchase;
import ru.isands.test.estore.dao.repo.PurchaseRepository;
import ru.isands.test.estore.service.PurchaseService;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    private final ObjectMapper objectMapper;

    @Override
    public Page<Purchase> getAll(Pageable pageable) {
        return purchaseRepository.findAll(pageable);
    }

    @Override
    public Purchase getOne(Long id) {
        Optional<Purchase> purchaseOptional = purchaseRepository.findById(id);
        return purchaseOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entity with id `%s` not found", id)));
    }

    @Override
    public List<Purchase> getMany(List<Long> ids) {
        return purchaseRepository.findAllById(ids);
    }

    @Override
    public Purchase create(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    public Purchase patch(Long id, JsonNode patchNode) throws IOException {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entity with id `%s` not found", id)));

        objectMapper.readerForUpdating(purchase).readValue(patchNode);

        return purchaseRepository.save(purchase);
    }

    @Override
    public List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException {
        Collection<Purchase> purchases = purchaseRepository.findAllById(ids);

        for (Purchase purchase : purchases) {
            objectMapper.readerForUpdating(purchase).readValue(patchNode);
        }

        List<Purchase> resultPurchases = purchaseRepository.saveAll(purchases);
        return resultPurchases.stream()
                .map(Purchase::getId)
                .collect(Collectors.toList());
    }

    @Override
    public Purchase delete(Long id) {
        Purchase purchase = purchaseRepository.findById(id).orElse(null);
        if (purchase != null) {
            purchaseRepository.delete(purchase);
        }
        return purchase;
    }

    @Override
    public void deleteMany(List<Long> ids) {
        purchaseRepository.deleteAllById(ids);
    }
}
