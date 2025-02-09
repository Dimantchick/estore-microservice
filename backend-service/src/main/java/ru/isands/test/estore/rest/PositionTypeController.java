package ru.isands.test.estore.rest;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dao.entity.PositionType;
import ru.isands.test.estore.service.PositionTypeService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/estore/api/positionType")
@RequiredArgsConstructor
public class PositionTypeController {

    private final PositionTypeService positionTypeService;

    @GetMapping
    public Page<PositionType> getAll(Pageable pageable) {
        return positionTypeService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public PositionType getOne(@PathVariable Long id) {
        return positionTypeService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<PositionType> getMany(@RequestParam List<Long> ids) {
        return positionTypeService.getMany(ids);
    }

    @PostMapping
    public PositionType create(@RequestBody PositionType positionType) {
        return positionTypeService.create(positionType);
    }

    @PatchMapping("/{id}")
    public PositionType patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        return positionTypeService.patch(id, patchNode);
    }

    @PatchMapping
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        return positionTypeService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public PositionType delete(@PathVariable Long id) {
        return positionTypeService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Long> ids) {
        positionTypeService.deleteMany(ids);
    }
}
