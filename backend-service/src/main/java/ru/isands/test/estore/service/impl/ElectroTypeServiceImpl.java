package ru.isands.test.estore.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.isands.test.estore.dao.entity.ElectroType;
import ru.isands.test.estore.dao.repo.ElectroTypeRepository;
import ru.isands.test.estore.service.ElectroTypeService;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ElectroTypeServiceImpl implements ElectroTypeService {

    private final ElectroTypeRepository electroTypeRepository;

    private final ObjectMapper objectMapper;

    @Override
    public Page<ElectroType> getAll(Pageable pageable) {
        return electroTypeRepository.findAll(pageable);
    }

    @Override
    public ElectroType getOne(Long id) {
        Optional<ElectroType> electroTypeOptional = electroTypeRepository.findById(id);
        return electroTypeOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entity with id `%s` not found", id)));
    }

    @Override
    public List<ElectroType> getMany(List<Long> ids) {
        return electroTypeRepository.findAllById(ids);
    }

    @Override
    public ElectroType create(ElectroType electroType) {
        return electroTypeRepository.save(electroType);
    }

    @Override
    public ElectroType patch(Long id, JsonNode patchNode) throws IOException {
        ElectroType electroType = electroTypeRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entity with id `%s` not found", id)));

        objectMapper.readerForUpdating(electroType).readValue(patchNode);

        return electroTypeRepository.save(electroType);
    }

    @Override
    public List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException {
        Collection<ElectroType> electroTypes = electroTypeRepository.findAllById(ids);

        for (ElectroType electroType : electroTypes) {
            objectMapper.readerForUpdating(electroType).readValue(patchNode);
        }

        List<ElectroType> resultElectroTypes = electroTypeRepository.saveAll(electroTypes);
        return resultElectroTypes.stream()
                .map(ElectroType::getId)
                .collect(Collectors.toList());
    }

    @Override
    public ElectroType delete(Long id) {
        ElectroType electroType = electroTypeRepository.findById(id).orElse(null);
        if (electroType != null) {
            electroTypeRepository.delete(electroType);
        }
        return electroType;
    }

    @Override
    public void deleteMany(List<Long> ids) {
        electroTypeRepository.deleteAllById(ids);
    }
}
