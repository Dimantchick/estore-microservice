package ru.isands.test.estore.rest;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dao.entity.Shop;
import ru.isands.test.estore.service.ShopService;

import java.io.IOException;
import java.util.List;

@RestController
@Tag(name = "Purchase", description = "Сервис для работы с магазинами")
@RequestMapping("/estore/api/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping
    @Operation(summary = "Получение всех магазинов постранично", responses = {
            @ApiResponse(description = "Список магазинов постранично")
    })
    public Page<Shop> getAll(Pageable pageable) {
        return shopService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение магазина по ИД", responses = {
            @ApiResponse(description = "Магазин")
    })
    public Shop getOne(@PathVariable Long id) {
        return shopService.getOne(id);
    }

    @GetMapping("/by-ids")
    @Operation(summary = "Получение магазинов по их ИД", responses = {
            @ApiResponse(description = "Список магазинов")
    })
    public List<Shop> getMany(@RequestParam List<Long> ids) {
        return shopService.getMany(ids);
    }

    @PostMapping
    @Operation(summary = "Добавление магазина", responses = {
            @ApiResponse(description = "Магазин")
    })
    public Shop create(@RequestBody Shop shop) {
        return shopService.create(shop);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Частичное изменение магазина по ИД", responses = {
            @ApiResponse(description = "Магазин")
    })
    public Shop patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        return shopService.patch(id, patchNode);
    }

    @PatchMapping
    @Operation(summary = "Частичное изменение магазинов", responses = {
            @ApiResponse(description = "Список ИД магазинов")
    })
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        return shopService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление магазина по ИД", responses = {
            @ApiResponse(description = "Магазин")
    })
    public Shop delete(@PathVariable Long id) {
        return shopService.delete(id);
    }

    @DeleteMapping
    @Operation(summary = "Удаление магазинов по ИД")
    public void deleteMany(@RequestParam List<Long> ids) {
        shopService.deleteMany(ids);
    }
}
