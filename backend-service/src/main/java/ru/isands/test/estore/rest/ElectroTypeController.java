package ru.isands.test.estore.rest;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dao.entity.ElectroType;
import ru.isands.test.estore.service.ElectroTypeService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/estore/api/electroType")
@RequiredArgsConstructor
public class ElectroTypeController {

    private final ElectroTypeService electroTypeService;

    @GetMapping
    public Page<ElectroType> getAll(Pageable pageable) {
        return electroTypeService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ElectroType getOne(@PathVariable Long id) {
        return electroTypeService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<ElectroType> getMany(@RequestParam List<Long> ids) {
        return electroTypeService.getMany(ids);
    }

    @PostMapping
    public ElectroType create(@RequestBody ElectroType electroType) {
        return electroTypeService.create(electroType);
    }

    @PatchMapping("/{id}")
    public ElectroType patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        return electroTypeService.patch(id, patchNode);
    }

    @PatchMapping
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        return electroTypeService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public ElectroType delete(@PathVariable Long id) {
        return electroTypeService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Long> ids) {
        electroTypeService.deleteMany(ids);
    }
}
