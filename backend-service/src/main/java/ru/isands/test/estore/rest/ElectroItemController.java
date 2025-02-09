package ru.isands.test.estore.rest;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dao.entity.ElectroItem;
import ru.isands.test.estore.service.ElectroItemService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/estore/api/electroItem")
@RequiredArgsConstructor
public class ElectroItemController {

    private final ElectroItemService electroItemService;

    @GetMapping
    public Page<ElectroItem> getAll(Pageable pageable) {
        return electroItemService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ElectroItem getOne(@PathVariable Long id) {
        return electroItemService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<ElectroItem> getMany(@RequestParam List<Long> ids) {
        return electroItemService.getMany(ids);
    }

    @PostMapping
    public ElectroItem create(@RequestBody ElectroItem electroItem) {
        return electroItemService.create(electroItem);
    }

    @PatchMapping("/{id}")
    public ElectroItem patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        return electroItemService.patch(id, patchNode);
    }

    @PatchMapping
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        return electroItemService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public ElectroItem delete(@PathVariable Long id) {
        return electroItemService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Long> ids) {
        electroItemService.deleteMany(ids);
    }
}
