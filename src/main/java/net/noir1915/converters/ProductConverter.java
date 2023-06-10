package net.noir1915.converters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.noir1915.dto.ProductDto;
import net.noir1915.model.Preparation;
import net.noir1915.model.Product;
import net.noir1915.model.ProductType;
import net.noir1915.service.MaterialService;
import net.noir1915.service.TechnologistService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductConverter {
    private final TechnologistService technologistService;
    private final MaterialService materialService;

    public ProductDto entityToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setProductTitle(product.getProductTitle());
        dto.setProductType(product.getProductType().name());
        dto.setProgramWritten(product.isProgramWritten());
        dto.setMaterialId(product.getMaterial().getId());
        dto.setEndDate(product.getEndDate());
        dto.setPreparation(product.getPreparation().name());

        if (Objects.nonNull(product.getTechnologist())) {
            dto.setTechnologistId(product.getTechnologist().getId());
        }

        return dto;
    }

    public Product toEntity(ProductDto dto) {
        Product product = new Product();
        product.setProductTitle(dto.getProductTitle());
        product.setProductType(ProductType.valueOf(dto.getProductType()));
        product.setProgramWritten(dto.isProgramWritten());
        product.setPreparation(Preparation.valueOf(dto.getPreparation()));
        product.setEndDate(dto.getEndDate());

        if (Objects.nonNull(dto.getMaterialId())) {
            product.setMaterial(materialService.getById(dto.getMaterialId()));
        }
        if (Objects.nonNull(dto.getTechnologistId())) {
            product.setTechnologist(technologistService.getById(dto.getTechnologistId()));
        }

        if (Objects.nonNull(dto.getId())) {
            product.setId(dto.getId());
        }

        return product;
    }
}
