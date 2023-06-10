package net.noir1915.service;


import lombok.RequiredArgsConstructor;
import net.noir1915.converters.TechnologistConverter;
import net.noir1915.dto.TechnologistDto;
import net.noir1915.exception.ResourceNotFoundException;
import net.noir1915.model.Technologist;
import net.noir1915.repository.TechnologistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TechnologistService {

    private final TechnologistRepository technologistRepository;
    private final TechnologistConverter technologistConverter;

    public Technologist getById(Long id) {
        return technologistRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Technologist with id = %d not found", id)));

    }

    public TechnologistDto getByName(String name) {
        return technologistConverter.entityToDto(technologistRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException(String.format("Technologist with name = %s not found", name))));
    }

    public List<TechnologistDto> getAll() {
        List<TechnologistDto> collect = technologistRepository.findAll().stream().map(technologistConverter::entityToDto).collect(Collectors.toList());
        return collect;
    }

    public Technologist create(TechnologistDto dto) {
        return technologistRepository.save(technologistConverter.toEntity(dto));
    }

    @Transactional
    public void remove(Long id) {
        technologistRepository.delete(technologistRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Technologist with id = %d not found", id))));
    }

    @Transactional
    public void update(TechnologistDto dto) {
        technologistRepository.save(technologistConverter.toEntity(dto));
    }


}
