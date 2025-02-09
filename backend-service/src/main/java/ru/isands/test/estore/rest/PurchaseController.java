package ru.isands.test.estore.rest;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dao.entity.Purchase;
import ru.isands.test.estore.service.PurchaseService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/estore/api/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping
    public Page<Purchase> getAll(Pageable pageable) {
        return purchaseService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public Purchase getOne(@PathVariable Long id) {
        return purchaseService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<Purchase> getMany(@RequestParam List<Long> ids) {
        return purchaseService.getMany(ids);
    }

    @PostMapping
    public Purchase create(@RequestBody Purchase purchase) {
        return purchaseService.create(purchase);
    }

    @PatchMapping("/{id}")
    public Purchase patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        return purchaseService.patch(id, patchNode);
    }

    @PatchMapping
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        return purchaseService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public Purchase delete(@PathVariable Long id) {
        return purchaseService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Long> ids) {
        purchaseService.deleteMany(ids);
    }
}
