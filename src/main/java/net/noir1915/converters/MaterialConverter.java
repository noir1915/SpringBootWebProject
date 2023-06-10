package net.noir1915.converters;


import net.noir1915.dto.MaterialDto;
import net.noir1915.model.Material;
import org.springframework.stereotype.Component;


@Component
public class MaterialConverter {

    public Material toEntity(MaterialDto materialDto) {
        Material material = new Material();
        material.setId(materialDto.getId());
        material.setType(materialDto.getType());
        return material;
    }

    public MaterialDto toDto(Material material) {
        MaterialDto materialDto = new MaterialDto();
        materialDto.setId(material.getId());
        materialDto.setType(material.getType());
        return materialDto;
    }
}
