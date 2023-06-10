package net.noir1915.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.noir1915.converters.UserConverter;
import net.noir1915.dto.UserDto;
import net.noir1915.model.User;
import net.noir1915.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "User controller", description = "Operation with user for Admin")
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    @GetMapping
    @Operation(summary = "Get all users")
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers().stream()
                .map(userConverter::entityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{username}")
    @Operation(summary = "Получить пользователя по имени")
    public UserDto getUserByUsername(@PathVariable String username) {
        return userConverter.entityToDto(userService.getUserByUsername(username));
    }

    @PostMapping
    @Operation(summary = "Создать нового пользователя")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto dto) {
        System.out.println(dto);
        return userConverter.entityToDto(userService.createOrUpdate(userConverter.toEntity(dto)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить пользователя по id")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto dto) {
        User newUser = userConverter.toEntity(dto);
        return userConverter.entityToDto(userService.createOrUpdate(newUser));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить пользователя по id")
    public void deleteUser(@PathVariable Long id) {
        userService.remove(id);
    }
}
