package ru.isands.test.estore.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.isands.test.estore.dao.entity.ElectroItem;
import ru.isands.test.estore.dao.repo.ElectroItemRepository;
import ru.isands.test.estore.service.ElectroItemService;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ElectroItemServiceImpl implements ElectroItemService{

    private final ElectroItemRepository electroItemRepository;

    private final ObjectMapper objectMapper;

    @Override
    public Page<ElectroItem> getAll(Pageable pageable) {
        return electroItemRepository.findAll(pageable);
    }

    @Override
    public ElectroItem getOne(Long id) {
        Optional<ElectroItem> electroItemOptional = electroItemRepository.findById(id);
        return electroItemOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entity with id `%s` not found", id)));
    }

    @Override
    public List<ElectroItem> getMany(List<Long> ids) {
        return electroItemRepository.findAllById(ids);
    }

    @Override
    public ElectroItem create(ElectroItem electroItem) {
        return electroItemRepository.save(electroItem);
    }

    @Override
    public ElectroItem patch(Long id, JsonNode patchNode) throws IOException {
        ElectroItem electroItem = electroItemRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entity with id `%s` not found", id)));

        objectMapper.readerForUpdating(electroItem).readValue(patchNode);

        return electroItemRepository.save(electroItem);
    }

    @Override
    public List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException {
        Collection<ElectroItem> electroItems = electroItemRepository.findAllById(ids);

        for (ElectroItem electroItem : electroItems) {
            objectMapper.readerForUpdating(electroItem).readValue(patchNode);
        }

        List<ElectroItem> resultElectroItems = electroItemRepository.saveAll(electroItems);
        return resultElectroItems.stream()
                .map(ElectroItem::getId)
                .collect(Collectors.toList());
    }

    @Override
    public ElectroItem delete(Long id) {
        ElectroItem electroItem = electroItemRepository.findById(id).orElse(null);
        if (electroItem != null) {
            electroItemRepository.delete(electroItem);
        }
        return electroItem;
    }

    @Override
    public void deleteMany(List<Long> ids) {
        electroItemRepository.deleteAllById(ids);
    }
}
