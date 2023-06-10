package net.noir1915.converters;

import net.noir1915.dto.TechnologistDto;
import net.noir1915.model.Technologist;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TechnologistConverter {

    public TechnologistDto entityToDto(Technologist technologist) {
        var dto = new TechnologistDto();
        dto.setId(technologist.getId());
        dto.setName(technologist.getName());
        dto.setEmail(technologist.getEmail());
        return dto;
    }

    public Technologist toEntity(TechnologistDto dto) {
        var technologist = new Technologist();

        if (Objects.nonNull(dto.getId())) technologist.setId(dto.getId());
        if (Objects.nonNull(dto.getEmail())) technologist.setEmail(dto.getEmail());

        technologist.setName(dto.getName());

        return technologist;
    }
}
