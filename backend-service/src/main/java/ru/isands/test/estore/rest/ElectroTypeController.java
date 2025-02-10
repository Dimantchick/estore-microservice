package ru.isands.test.estore.rest;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dao.entity.ElectroType;
import ru.isands.test.estore.service.ElectroTypeService;

import java.io.IOException;
import java.util.List;

@RestController
@Tag(name = "ElectroType", description = "Сервис для работы со справочником «Тип электроники»")
@RequestMapping("/estore/api/electroType")
@RequiredArgsConstructor
public class ElectroTypeController {

    private final ElectroTypeService electroTypeService;

    @GetMapping
    @Operation(summary = "Получение всех типов электроники постранично", responses = {
            @ApiResponse(description = "Список типов электроники постранично")
    })
    public Page<ElectroType> getAll(Pageable pageable) {
        return electroTypeService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение типа электроники по ИД", responses = {
            @ApiResponse(description = "Тип электроники")
    })
    public ElectroType getOne(@PathVariable Long id) {
        return electroTypeService.getOne(id);
    }

    @GetMapping("/by-ids")
    @Operation(summary = "Получение типов электроники по их ИД", responses = {
            @ApiResponse(description = "Список типов электроники")
    })
    public List<ElectroType> getMany(@RequestParam List<Long> ids) {
        return electroTypeService.getMany(ids);
    }

    @PostMapping
    @Operation(summary = "Добавление типа электроники", responses = {
            @ApiResponse(description = "Тип электроники")
    })
    public ElectroType create(@RequestBody ElectroType electroType) {
        return electroTypeService.create(electroType);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Частичное изменение типа электроники по ИД", responses = {
            @ApiResponse(description = "Тип электроники")
    })
    public ElectroType patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        return electroTypeService.patch(id, patchNode);
    }

    @PatchMapping
    @Operation(summary = "Частичное изменение типов электроники", responses = {
            @ApiResponse(description = "Список ИД типов электроники")
    })
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        return electroTypeService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление типа электроники по ИД", responses = {
            @ApiResponse(description = "Тип электроники")
    })
    public ElectroType delete(@PathVariable Long id) {
        return electroTypeService.delete(id);
    }

    @DeleteMapping
    @Operation(summary = "Удаление типов электроники по ИД")
    public void deleteMany(@RequestParam List<Long> ids) {
        electroTypeService.deleteMany(ids);
    }
}
