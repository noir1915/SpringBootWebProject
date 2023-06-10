package net.noir1915.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import net.noir1915.converters.MaterialConverter;
import net.noir1915.dto.MaterialDto;
import net.noir1915.service.MaterialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("api/v1/materials")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class MaterialController {

    private final MaterialService materialService;
    private final MaterialConverter materialConverter;

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации о материалах")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "401", description = "Авторизуйтесь для операции", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "403", description = "доступ к запрошенному ресурсу запрещен", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "500", description = "Сервер занят, напишите позже", content = @Content(mediaType = "")),
    })
    public ResponseEntity<MaterialDto> getMaterialById(@PathVariable Long id) {
        return ResponseEntity.ok(materialConverter.toDto(materialService.getById(id)));
    }

    @GetMapping
    @Operation(summary = "Получение списка материалов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "401", description = "Авторизуйтесь для операции", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "403", description = "доступ к запрошенному ресурсу запрещен", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "500", description = "Сервер занят, напишите позже", content = @Content(mediaType = "")),
    })
    public ResponseEntity<Collection<MaterialDto>> getAllMaterial() {
        return ResponseEntity.ok(materialService.getAllMaterial());
    }

    @PostMapping
    @Operation(summary = "Добавление/обновление информации о материалах")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное добавление/обновлние", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "401", description = "Авторизуйтесь для операции", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "403", description = "доступ к запрошенному ресурсу запрещен", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "500", description = "Сервер занят, напишите позже", content = @Content(mediaType = "")),
    })
    public ResponseEntity<?> createMaterial(@RequestBody MaterialDto materialDto) {
        materialService.create(materialDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление информации о материалах")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное удаление", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "401", description = "Авторизуйтесь для операции", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "403", description = "доступ к запрошенному ресурсу запрещен", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "500", description = "Сервер занят, напишите позже", content = @Content(mediaType = "")),
    })
    public void deleteMaterial(@PathVariable Long id) {
        materialService.remove(id);
    }


}
