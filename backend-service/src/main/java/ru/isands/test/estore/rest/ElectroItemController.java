package ru.isands.test.estore.rest;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dao.entity.ElectroItem;
import ru.isands.test.estore.service.ElectroItemService;

import java.io.IOException;
import java.util.List;

@RestController
@Tag(name = "ElectroItem", description = "Сервис для работы с электротоварами")
@RequestMapping("/estore/api/electroItem")
@RequiredArgsConstructor
public class ElectroItemController {

    private final ElectroItemService electroItemService;

    @GetMapping
    @Operation(summary = "Получение всех электротоваров постранично", responses = {
            @ApiResponse(description = "Список электротоваров постранично")
    })
    public Page<ElectroItem> getAll(Pageable pageable) {
        return electroItemService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение электротовара по ИД", responses = {
            @ApiResponse(description = "Электротовар")
    })
    public ElectroItem getOne(@PathVariable Long id) {
        return electroItemService.getOne(id);
    }

    @GetMapping("/by-ids")
    @Operation(summary = "Получение электротоваров по их ИД", responses = {
            @ApiResponse(description = "Список электротоваров")
    })
    public List<ElectroItem> getMany(@RequestParam List<Long> ids) {
        return electroItemService.getMany(ids);
    }

    @PostMapping
    @Operation(summary = "Добавление электротовара", responses = {
            @ApiResponse(description = "Электротовар")
    })
    public ElectroItem create(@RequestBody ElectroItem electroItem) {
        return electroItemService.create(electroItem);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Частичное изменение электротовара по ИД", responses = {
            @ApiResponse(description = "Электротовар")
    })
    public ElectroItem patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        return electroItemService.patch(id, patchNode);
    }

    @PatchMapping
    @Operation(summary = "Частичное изменение электротоваров", responses = {
            @ApiResponse(description = "Список ИД электротоваров")
    })
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        return electroItemService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление электротовара по ИД", responses = {
            @ApiResponse(description = "Электротовар")
    })
    public ElectroItem delete(@PathVariable Long id) {
        return electroItemService.delete(id);
    }

    @DeleteMapping
    @Operation(summary = "Удаление электротоваров по ИД")
    public void deleteMany(@RequestParam List<Long> ids) {
        electroItemService.deleteMany(ids);
    }
}
