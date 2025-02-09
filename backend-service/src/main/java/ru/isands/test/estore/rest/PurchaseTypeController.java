package ru.isands.test.estore.rest;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dao.entity.PurchaseType;
import ru.isands.test.estore.service.PurchaseTypeService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/estore/api/purchaseType")
@RequiredArgsConstructor
public class PurchaseTypeController {

    private final PurchaseTypeService purchaseTypeService;

    @GetMapping
    public Page<PurchaseType> getAll(Pageable pageable) {
        return purchaseTypeService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public PurchaseType getOne(@PathVariable Long id) {
        return purchaseTypeService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<PurchaseType> getMany(@RequestParam List<Long> ids) {
        return purchaseTypeService.getMany(ids);
    }

    @PostMapping
    public PurchaseType create(@RequestBody PurchaseType purchaseType) {
        return purchaseTypeService.create(purchaseType);
    }

    @PatchMapping("/{id}")
    public PurchaseType patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        return purchaseTypeService.patch(id, patchNode);
    }

    @PatchMapping
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        return purchaseTypeService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public PurchaseType delete(@PathVariable Long id) {
        return purchaseTypeService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Long> ids) {
        purchaseTypeService.deleteMany(ids);
    }
}
