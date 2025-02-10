package ru.isands.test.estore.rest;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dao.entity.PurchaseType;
import ru.isands.test.estore.service.PurchaseTypeService;

import java.io.IOException;
import java.util.List;

@RestController
@Tag(name = "PurchaseType", description = "Сервис для работы со справочником «Тип покупки»")
@RequestMapping("/estore/api/purchaseType")
@RequiredArgsConstructor
public class PurchaseTypeController {

    private final PurchaseTypeService purchaseTypeService;

    @GetMapping
    @Operation(summary = "Получение всех типов покупки постранично", responses = {
            @ApiResponse(description = "Список типов покупки постранично")
    })
    public Page<PurchaseType> getAll(Pageable pageable) {
        return purchaseTypeService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение типа покупки по ИД", responses = {
            @ApiResponse(description = "Тип покупки")
    })
    public PurchaseType getOne(@PathVariable Long id) {
        return purchaseTypeService.getOne(id);
    }

    @GetMapping("/by-ids")
    @Operation(summary = "Получение типов покупки по их ИД", responses = {
            @ApiResponse(description = "Список типов покупки")
    })
    public List<PurchaseType> getMany(@RequestParam List<Long> ids) {
        return purchaseTypeService.getMany(ids);
    }

    @PostMapping
    @Operation(summary = "Добавление типа покупки", responses = {
            @ApiResponse(description = "Тип покупки")
    })
    public PurchaseType create(@RequestBody PurchaseType purchaseType) {
        return purchaseTypeService.create(purchaseType);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Частичное изменение типа покупки по ИД", responses = {
            @ApiResponse(description = "Тип покупки")
    })
    public PurchaseType patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        return purchaseTypeService.patch(id, patchNode);
    }

    @PatchMapping
    @Operation(summary = "Частичное изменение типов покупки", responses = {
            @ApiResponse(description = "Список ИД типов покупки")
    })
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        return purchaseTypeService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление типа покупки по ИД", responses = {
            @ApiResponse(description = "Тип покупки")
    })
    public PurchaseType delete(@PathVariable Long id) {
        return purchaseTypeService.delete(id);
    }

    @DeleteMapping
    @Operation(summary = "Удаление типов покупки по ИД")
    public void deleteMany(@RequestParam List<Long> ids) {
        purchaseTypeService.deleteMany(ids);
    }
}
