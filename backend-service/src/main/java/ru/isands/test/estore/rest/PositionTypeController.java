package ru.isands.test.estore.rest;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.isands.test.estore.dao.entity.PositionType;
import ru.isands.test.estore.service.PositionTypeService;

import java.io.IOException;
import java.util.List;

@RestController
@Tag(name = "PositionType", description = "Сервис для работы со справочником «Должности»")
@RequestMapping("/estore/api/positionType")
@RequiredArgsConstructor
public class PositionTypeController {

    private final PositionTypeService positionTypeService;

    @GetMapping
    @Operation(summary = "Получение всех должностей постранично", responses = {
            @ApiResponse(description = "Список должностей постранично")
    })
    public Page<PositionType> getAll(Pageable pageable) {
        return positionTypeService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение должности по ИД", responses = {
            @ApiResponse(description = "Должность")
    })
    public PositionType getOne(@PathVariable Long id) {
        return positionTypeService.getOne(id);
    }

    @GetMapping("/by-ids")
    @Operation(summary = "Получение должностей по их ИД", responses = {
            @ApiResponse(description = "Список должностей")
    })
    public List<PositionType> getMany(@RequestParam List<Long> ids) {
        return positionTypeService.getMany(ids);
    }

    @PostMapping
    @Operation(summary = "Добавление должности", responses = {
            @ApiResponse(description = "Должность")
    })
    public PositionType create(@RequestBody PositionType positionType) {
        return positionTypeService.create(positionType);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Частичное изменение должности по ИД", responses = {
            @ApiResponse(description = "Должность")
    })
    public PositionType patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        return positionTypeService.patch(id, patchNode);
    }

    @PatchMapping
    @Operation(summary = "Частичное изменение должностей", responses = {
            @ApiResponse(description = "Список ИД должностей")
    })
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        return positionTypeService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление должности по ИД", responses = {
            @ApiResponse(description = "Должность")
    })
    public PositionType delete(@PathVariable Long id) {
        return positionTypeService.delete(id);
    }

    @DeleteMapping
    @Operation(summary = "Удаление должностей по ИД")
    public void deleteMany(@RequestParam List<Long> ids) {
        positionTypeService.deleteMany(ids);
    }
}
