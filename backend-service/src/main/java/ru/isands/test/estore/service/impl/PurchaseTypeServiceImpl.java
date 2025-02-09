package ru.isands.test.estore.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.isands.test.estore.dao.entity.PurchaseType;
import ru.isands.test.estore.dao.repo.PurchaseTypeRepository;
import ru.isands.test.estore.service.PurchaseTypeService;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PurchaseTypeServiceImpl implements PurchaseTypeService {

    private final PurchaseTypeRepository purchaseTypeRepository;

    private final ObjectMapper objectMapper;

    @Override
    public Page<PurchaseType> getAll(Pageable pageable) {
        return purchaseTypeRepository.findAll(pageable);
    }

    @Override
    public PurchaseType getOne(Long id) {
        Optional<PurchaseType> purchaseTypeOptional = purchaseTypeRepository.findById(id);
        return purchaseTypeOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entity with id `%s` not found", id)));
    }

    @Override
    public List<PurchaseType> getMany(List<Long> ids) {
        return purchaseTypeRepository.findAllById(ids);
    }

    @Override
    public PurchaseType create(PurchaseType purchaseType) {
        return purchaseTypeRepository.save(purchaseType);
    }

    @Override
    public PurchaseType patch(Long id, JsonNode patchNode) throws IOException {
        PurchaseType purchaseType = purchaseTypeRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entity with id `%s` not found", id)));

        objectMapper.readerForUpdating(purchaseType).readValue(patchNode);

        return purchaseTypeRepository.save(purchaseType);
    }

    @Override
    public List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException {
        Collection<PurchaseType> purchaseTypes = purchaseTypeRepository.findAllById(ids);

        for (PurchaseType purchaseType : purchaseTypes) {
            objectMapper.readerForUpdating(purchaseType).readValue(patchNode);
        }

        List<PurchaseType> resultPurchaseTypes = purchaseTypeRepository.saveAll(purchaseTypes);
        return resultPurchaseTypes.stream()
                .map(PurchaseType::getId)
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseType delete(Long id) {
        PurchaseType purchaseType = purchaseTypeRepository.findById(id).orElse(null);
        if (purchaseType != null) {
            purchaseTypeRepository.delete(purchaseType);
        }
        return purchaseType;
    }

    @Override
    public void deleteMany(List<Long> ids) {
        purchaseTypeRepository.deleteAllById(ids);
    }
}
