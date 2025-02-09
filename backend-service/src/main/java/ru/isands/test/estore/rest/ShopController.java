package ru.isands.test.estore.rest;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dao.entity.Shop;
import ru.isands.test.estore.service.ShopService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/estore/api/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping
    public Page<Shop> getAll(Pageable pageable) {
        return shopService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public Shop getOne(@PathVariable Long id) {
        return shopService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<Shop> getMany(@RequestParam List<Long> ids) {
        return shopService.getMany(ids);
    }

    @PostMapping
    public Shop create(@RequestBody Shop shop) {
        return shopService.create(shop);
    }

    @PatchMapping("/{id}")
    public Shop patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        return shopService.patch(id, patchNode);
    }

    @PatchMapping
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        return shopService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public Shop delete(@PathVariable Long id) {
        return shopService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Long> ids) {
        shopService.deleteMany(ids);
    }
}
