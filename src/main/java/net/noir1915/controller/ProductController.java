package net.noir1915.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import net.noir1915.converters.ProductConverter;
import net.noir1915.dto.ProductDto;
import net.noir1915.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping
    @Operation(summary = "Получение списка продуктов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "401", description = "Авторизуйтесь для операции", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "403", description = "доступ к запрошенному ресурсу запрещен", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "500", description = "Сервер занят, напишите позже", content = @Content(mediaType = "")),
    })
    public ResponseEntity<Collection<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Удаление информации о продукте")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное удаление", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "401", description = "Авторизуйтесь для операции", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "403", description = "доступ к запрошенному ресурсу запрещен", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "500", description = "Сервер занят, напишите позже", content = @Content(mediaType = "")),
    })
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productConverter.entityToDto(productService.getById(id)));
    }

    @PostMapping
    @Operation(summary = "Добавление/обновление информации о продукте")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное удаление", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "401", description = "Авторизуйтесь для операции", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "403", description = "доступ к запрошенному ресурсу запрещен", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "500", description = "Сервер занят, напишите позже", content = @Content(mediaType = "")),
    })
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productConverter.entityToDto(productService.createOrUpdate(productDto)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление информации о продукте")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное удаление", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "401", description = "Авторизуйтесь для операции", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "403", description = "доступ к запрошенному ресурсу запрещен", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "500", description = "Сервер занят, напишите позже", content = @Content(mediaType = "")),
    })
    public void remove(@PathVariable Long id) {
        productService.remove(id);
    }

}
