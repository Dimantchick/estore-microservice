package ru.isands.test.estore.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.isands.test.estore.dao.entity.PositionType;
import ru.isands.test.estore.dao.repo.PositionTypeRepository;
import ru.isands.test.estore.service.PositionTypeService;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PositionTypeServiceImpl implements PositionTypeService {

    private final PositionTypeRepository positionTypeRepository;

    private final ObjectMapper objectMapper;

    @Override
    public Page<PositionType> getAll(Pageable pageable) {
        return positionTypeRepository.findAll(pageable);
    }

    @Override
    public PositionType getOne(Long id) {
        Optional<PositionType> positionTypeOptional = positionTypeRepository.findById(id);
        return positionTypeOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entity with id `%s` not found", id)));
    }

    @Override
    public List<PositionType> getMany(List<Long> ids) {
        return positionTypeRepository.findAllById(ids);
    }

    @Override
    public PositionType create(PositionType positionType) {
        return positionTypeRepository.save(positionType);
    }

    @Override
    public PositionType patch(Long id, JsonNode patchNode) throws IOException {
        PositionType positionType = positionTypeRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entity with id `%s` not found", id)));

        objectMapper.readerForUpdating(positionType).readValue(patchNode);

        return positionTypeRepository.save(positionType);
    }

    @Override
    public List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException {
        Collection<PositionType> positionTypes = positionTypeRepository.findAllById(ids);

        for (PositionType positionType : positionTypes) {
            objectMapper.readerForUpdating(positionType).readValue(patchNode);
        }

        List<PositionType> resultPositionTypes = positionTypeRepository.saveAll(positionTypes);
        return resultPositionTypes.stream()
                .map(PositionType::getId)
                .collect(Collectors.toList());
    }

    @Override
    public PositionType delete(Long id) {
        PositionType positionType = positionTypeRepository.findById(id).orElse(null);
        if (positionType != null) {
            positionTypeRepository.delete(positionType);
        }
        return positionType;
    }

    @Override
    public void deleteMany(List<Long> ids) {
        positionTypeRepository.deleteAllById(ids);
    }
}
