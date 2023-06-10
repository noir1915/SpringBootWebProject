package net.noir1915.converters;

import net.noir1915.dto.UserDto;
import net.noir1915.model.User;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserConverter {

    public UserDto entityToDto(User user) {
        UserDto dto = new UserDto();

        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(null);
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles());

        return dto;
    }

    public User toEntity(UserDto dto) {
        User user = new User();

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        if (Objects.nonNull(dto.getId())) user.setId(dto.getId());
        if (Objects.nonNull(dto.getRoles())) user.setRoles(dto.getRoles());

        return user;
    }
}
