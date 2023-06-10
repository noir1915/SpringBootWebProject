package net.noir1915.service;

import lombok.RequiredArgsConstructor;
import net.noir1915.converters.MaterialConverter;
import net.noir1915.dto.MaterialDto;
import net.noir1915.exception.ResourceNotFoundException;
import net.noir1915.model.Material;
import net.noir1915.repository.MaterialRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialConverter materialConverter;

    public Material create(MaterialDto materialDto) {
        return materialRepository.save(materialConverter.toEntity(materialDto));
    }

    public Material getById(Long id) {
        return materialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Material with id = %d not found", id)));
    }

    public Collection<MaterialDto> getAllMaterial(){
        return materialRepository.findAll().stream().map(materialConverter::toDto).collect(Collectors.toList());
    }

    @Transactional
    public void remove(Long id) {
        materialRepository.delete(materialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Material with id = %d not found", id))));
    }

    @Transactional
    public void update(MaterialDto materialDto) {
        Material material = materialRepository.findById(materialDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Material with id = %d not found", materialDto.getId())));
        material.setType(materialDto.getType());
    }

}
