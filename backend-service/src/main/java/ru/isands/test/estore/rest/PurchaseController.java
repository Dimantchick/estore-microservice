package ru.isands.test.estore.rest;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dao.entity.Purchase;
import ru.isands.test.estore.service.PurchaseService;

import java.io.IOException;
import java.util.List;

@RestController
@Tag(name = "Purchase", description = "Сервис для работы с покупками")
@RequestMapping("/estore/api/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping
    @Operation(summary = "Получение всех покупок постранично", responses = {
            @ApiResponse(description = "Список покупок постранично")
    })
    public Page<Purchase> getAll(Pageable pageable) {
        return purchaseService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение покупки по ИД", responses = {
            @ApiResponse(description = "Покупка")
    })
    public Purchase getOne(@PathVariable Long id) {
        return purchaseService.getOne(id);
    }

    @GetMapping("/by-ids")
    @Operation(summary = "Получение покупок по их ИД", responses = {
            @ApiResponse(description = "Список покупок")
    })
    public List<Purchase> getMany(@RequestParam List<Long> ids) {
        return purchaseService.getMany(ids);
    }

    @PostMapping
    @Operation(summary = "Добавление покупки", responses = {
            @ApiResponse(description = "Покупка")
    })
    public Purchase create(@RequestBody Purchase purchase) {
        return purchaseService.create(purchase);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Частичное изменение покупки по ИД", responses = {
            @ApiResponse(description = "Покупка")
    })
    public Purchase patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        return purchaseService.patch(id, patchNode);
    }

    @PatchMapping
    @Operation(summary = "Частичное изменение покупок", responses = {
            @ApiResponse(description = "Список ИД покупок")
    })
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        return purchaseService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление покупки по ИД", responses = {
            @ApiResponse(description = "Покупка")
    })
    public Purchase delete(@PathVariable Long id) {
        return purchaseService.delete(id);
    }

    @DeleteMapping
    @Operation(summary = "Удаление покупок по ИД")
    public void deleteMany(@RequestParam List<Long> ids) {
        purchaseService.deleteMany(ids);
    }
}
